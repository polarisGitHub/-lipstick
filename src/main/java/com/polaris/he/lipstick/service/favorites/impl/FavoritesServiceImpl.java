package com.polaris.he.lipstick.service.favorites.impl;

import com.polaris.he.lipstick.common.constant.ExceptionCodeEnum;
import com.polaris.he.lipstick.common.exception.BizException;
import com.polaris.he.lipstick.dao.FavoritesDao;
import com.polaris.he.lipstick.dao.object.FavoritesDO;
import com.polaris.he.lipstick.entity.favorites.FavoriteItem;
import com.polaris.he.lipstick.entity.sku.BaseSkuInfo;
import com.polaris.he.lipstick.entity.sku.SkuAggregation;
import com.polaris.he.lipstick.entity.user.UserInfo;
import com.polaris.he.lipstick.service.favorites.FavoritesService;
import com.polaris.he.lipstick.service.sku.SkuService;
import com.polaris.he.lipstick.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

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
        return favoritesDao.delete(id, user);
    }

    @Override
    public List<FavoriteItem> query(String type, UserInfo user) {
        return null;
    }
}