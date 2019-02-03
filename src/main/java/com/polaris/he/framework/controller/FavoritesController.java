package com.polaris.he.framework.controller;

import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.entity.favorites.FavoritesDeleteEntity;
import com.polaris.he.framework.entity.favorites.FavoritesSaveEntity;
import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.service.favorites.FavoritesService;
import com.polaris.he.framework.utils.BaseSkuInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User: hexie
 * Date: 2019-01-27 11:49
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/{type}/favorites")
public class FavoritesController {

    @Resource
    private FavoritesService favoritesService;

    @PostMapping("/save")
    public String saveFavorite(@PathVariable CosmeticsEnum type, @RequestBody FavoritesSaveEntity save, UserInfo user) {
        log.info("添加收藏物品，user={},type={},body={}", user, type, save);
        BaseSkuInfo sku = BaseSkuInfoUtils.create(save.getBrandCode(), type.getCode(), save.getSkuCode());
        favoritesService.save(sku, user);
        return "ok";
    }

    @PostMapping("/delete")
    public String deleteFavorite(@PathVariable CosmeticsEnum type, @RequestBody FavoritesDeleteEntity delete, UserInfo user) {
        log.info("删除收藏物品，user={},type={},body={}", user, type, delete);
        Assert.notNull(delete, "参数不能为空");
        favoritesService.delete(delete.getId(), user);
        return "ok";
    }

    @GetMapping("/query")
    public Object queryFavorites(@PathVariable CosmeticsEnum type, UserInfo user) {
        log.info("用户{}查询{}收藏情况", user, type);
        return favoritesService.queryUserFavorite(user, type.getCode());
    }
}