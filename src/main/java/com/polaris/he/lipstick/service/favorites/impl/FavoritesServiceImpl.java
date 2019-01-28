package com.polaris.he.lipstick.service.favorites.impl;

import com.polaris.he.lipstick.dao.FavoritesDao;
import com.polaris.he.lipstick.entity.favorites.FavoriteItem;
import com.polaris.he.lipstick.entity.sku.BaseSkuInfo;
import com.polaris.he.lipstick.entity.user.UserInfo;
import com.polaris.he.lipstick.service.favorites.FavoritesService;
import com.polaris.he.lipstick.service.sku.SkuService;
import lombok.extern.slf4j.Slf4j;
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
    public int save(BaseSkuInfo sku, UserInfo user) {
        log.info("用户保存到收藏夹,user={},sku={}", user, sku);
        Assert.notNull(sku, "sku不能为空");
        Assert.notNull(user, "用户信息不能为空");
        skuService.getAggregationByCode(sku);
        return 0;
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