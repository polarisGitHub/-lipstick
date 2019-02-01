package com.polaris.he.lipstick.entity.favorites;

import com.polaris.he.lipstick.entity.sku.Brand;
import com.polaris.he.lipstick.entity.sku.Category;
import com.polaris.he.lipstick.entity.sku.Goods;
import com.polaris.he.lipstick.entity.sku.Sku;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-27 21:55
 * Description:
 */
@Getter
@Setter
@ToString
public class FavoriteItem {

    private Long id;

    private String type;

    private List<Category> categories;

    private Goods goods;

    private Brand brand;

    private Sku sku;
}