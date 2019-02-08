package com.polaris.he.framework.service.favorites.impl;

import com.polaris.he.application.entity.constant.ExceptionCodeEnum;
import com.polaris.he.application.exception.BizException;
import com.polaris.he.framework.dao.FavoritesDao;
import com.polaris.he.framework.dao.object.FavoritesDO;
import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.sku.SkuAggregation;
import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.repository.FrameworkBizConverterRepository;
import com.polaris.he.framework.service.favorites.FavoritesService;
import com.polaris.he.framework.service.sku.SkuService;
import com.polaris.he.framework.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-27 22:05
 * Description:
 */
@Slf4j
@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Resource
    private FavoritesDao favoritesDao;

    @Resource
    private SkuService skuService;

    @Resource
    private FrameworkBizConverterRepository frameworkBizConverterRepository;

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
            throw new BizException(String.format("找不到sku:%s", sku), ExceptionCodeEnum.E00002);
        }

        // check
        if (checkFavorite(sku, user)) {
            throw new BizException(String.format("重复收藏", user, sku), ExceptionCodeEnum.E00003);
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
        Converter<FavoritesDO, ?> converter = frameworkBizConverterRepository.getConverter(type, FavoritesDO.class, "favorites");
        return list.stream().map(converter::convert).collect(Collectors.toList());
    }
}