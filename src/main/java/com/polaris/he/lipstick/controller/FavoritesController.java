package com.polaris.he.lipstick.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.polaris.he.lipstick.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.entity.favorites.FavoriteItem;
import com.polaris.he.lipstick.entity.user.UserInfo;
import com.polaris.he.lipstick.service.favorites.FavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
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
@RequestMapping("/api/favorites")
public class FavoritesController {

    @Resource
    private FavoritesService favoritesService;

    @GetMapping("/{type}/query")
    public List<FavoriteItem> favorites(@PathVariable CosmeticsEnum type, UserInfo user) {
        log.info("查询收藏夹，type={}，user={}", type, user);
        return favoritesService.query(type.getCode(), user);
    }

    @PostMapping("/{type}/save")
    public String saveFavorite(@PathVariable CosmeticsEnum type, @RequestBody JsonNode body, UserInfo user) {
        log.info("添加收藏物品，user={},type={},body={}", user, type, body);
        String brand = Optional.ofNullable(body).map(l -> l.get("brand")).map(JsonNode::asText).orElseThrow(() -> new IllegalArgumentException("参数错误"));
        String skuCode = Optional.ofNullable(body).map(l -> l.get("skuCode")).map(JsonNode::asText).orElseThrow(() -> new IllegalArgumentException("参数错误"));
        return "";
    }

    @PostMapping("/{type}/delete")
    public String deleteFavorite(@PathVariable CosmeticsEnum type, @RequestBody JsonNode body, UserInfo user) {
        log.info("删除收藏物品，user={},type={},body={}", user, type, body);
        return "";
    }
}