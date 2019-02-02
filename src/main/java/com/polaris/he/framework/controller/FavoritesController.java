package com.polaris.he.framework.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.service.favorites.FavoritesService;
import com.polaris.he.framework.utils.BaseSkuInfoUtils;
import com.polaris.he.lipstick.entity.LipstickFavoriteItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
    public String saveFavorite(@PathVariable CosmeticsEnum type, @RequestBody JsonNode body, UserInfo user) {
        log.info("添加收藏物品，user={},type={},body={}", user, type, body);
        String brandCode = Optional.ofNullable(body).map(l -> l.get("brandCode")).map(JsonNode::asText).orElseThrow(() -> new IllegalArgumentException("参数错误"));
        String skuCode = Optional.ofNullable(body).map(l -> l.get("skuCode")).map(JsonNode::asText).orElseThrow(() -> new IllegalArgumentException("参数错误"));
        BaseSkuInfo sku = BaseSkuInfoUtils.create(brandCode, type.getCode(), skuCode);
        favoritesService.save(sku, user);
        return "ok";
    }

    @PostMapping("/delete")
    public String deleteFavorite(@PathVariable CosmeticsEnum type, @RequestBody JsonNode body, UserInfo user) {
        log.info("删除收藏物品，user={},type={},body={}", user, type, body);
        return "ok";
    }

    @PostMapping("/query")
    public Object queryFavorites(@PathVariable CosmeticsEnum type, UserInfo user) {
        log.info("用户{}查询{}收藏情况", user, type);
        return favoritesService.queryUserFavorite(user, type.getCode());
    }
}