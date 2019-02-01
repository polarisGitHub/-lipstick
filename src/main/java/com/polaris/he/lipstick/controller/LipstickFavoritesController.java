package com.polaris.he.lipstick.controller;

import com.polaris.he.lipstick.entity.LipstickFavoriteItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-02-01 23:15
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/lipstick/favorites")
public class LipstickFavoritesController {

    @PostMapping("/query")
    public List<LipstickFavoriteItem> queryFavorites() {
        return null;
    }
}