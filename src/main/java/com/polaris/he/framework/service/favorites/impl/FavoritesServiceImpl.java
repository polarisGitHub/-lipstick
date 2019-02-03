package com.polaris.he.framework.service.favorites.impl;

import com.polaris.he.application.entity.constant.ExceptionCodeEnum;
import com.polaris.he.application.exception.BizException;
import com.polaris.he.framework.annotation.FavoritesConverter;
import com.polaris.he.framework.dao.FavoritesDao;
import com.polaris.he.framework.dao.object.FavoritesDO;
import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.sku.SkuAggregation;
import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.service.favorites.FavoritesService;
import com.polaris.he.framework.service.sku.SkuService;
import com.polaris.he.framework.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-27 22:05
 * Description:
 */
@Slf4j
@Service
public class FavoritesServiceImpl implements FavoritesService, ApplicationContextAware {

    private static final Map<String, Converter<FavoritesDO, ?>> CONVERTER_MAP = new HashMap<>();

    @Resource
    private FavoritesDao favoritesDao;

    @Resource
    private SkuService skuService;

    @Override
    public boolean checkFavorite(BaseSkuInfo sku, UserInfo user) {
        FavoritesDO query = BeanCopyUtils.copyObject(sku, new FavoritesDO());
        query = BeanCopyUtils.copyObject(user, query);
        return favoritesDao.checkFavorite(query);
    }

    @Override
    public int save(BaseSkuInfo sku, UserInfo user) {
        log.info("用户保存到收藏夹,user={},sku={}", user, sku);
        Assert.notNull(sku, "sku不能为空");
        Assert.notNull(user, "用户信息不能为空");
        SkuAggregation skuInfo = skuService.getAggregationBySkuInfo(sku);

        if (skuInfo == null) {
            throw new BizException(String.format("找不到sku:%s", sku), ExceptionCodeEnum.E00002, null);
        }

        // check
        if (checkFavorite(sku, user)) {
            throw new BizException(String.format("重复收藏", user, sku), ExceptionCodeEnum.E00003, null);
        }

        FavoritesDO insert = new FavoritesDO();
        insert.setSource(user.getSource());
        insert.setOpenId(user.getOpenId());
        insert.setType(sku.getType());
        insert.setBrandCode(skuInfo.getBrand().getCode());
        insert.setBrandName(skuInfo.getBrand().getName());
        insert.setGoodsCode(skuInfo.getGoods().getGoodsCode());
        insert.setGoodsName(skuInfo.getGoods().getGoodsName());
        insert.setGoodsUrl(skuInfo.getGoods().getUrl());
        insert.setGoodsIllustration(skuInfo.getGoods().getIllustration());
        insert.setSkuCode(skuInfo.getSku().getSkuCode());
        insert.setSkuName(skuInfo.getSku().getSkuName());
        insert.setSkuByName(skuInfo.getSku().getSkuByName());
        insert.setSkuUrl(skuInfo.getSku().getUrl());
        insert.setSkuExtension(skuInfo.getSku().getExtension());
        return favoritesDao.insert(insert);
    }

    @Override
    public int delete(Long id, UserInfo user) {
        log.info("用户删除收藏夹，user={},id={}", user, id);
        return favoritesDao.delete(id, user.getSource(), user.getOpenId());
    }

    @Override
    public List<?> queryUserFavorite(UserInfo user, String type) {
        List<FavoritesDO> list = favoritesDao.queryFavorites(user.getSource(), user.getOpenId(), type);
        Converter converter = CONVERTER_MAP.get(type);
        Assert.notNull(converter, "convert为空");
        return list.stream().map((Function<FavoritesDO, ?>) converter::convert).collect(Collectors.toList());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Converter> beans = applicationContext.getBeansOfType(Converter.class);
        if (MapUtils.isNotEmpty(beans)) {
            beans.forEach((beanName, bean) -> {
                Class<? extends Converter> clazz = bean.getClass();
                Type[] types = clazz.getGenericInterfaces();
                for (Type type : types) {
                    Type source = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (((ParameterizedType) type).getRawType().equals(Converter.class) && source.equals(FavoritesDO.class)) {
                        FavoritesConverter ann = clazz.getAnnotation(FavoritesConverter.class);
                        if (ann != null) {
                            CONVERTER_MAP.put(ann.type().getCode(), bean);
                        }
                    }
                }
            });
        }
    }
}