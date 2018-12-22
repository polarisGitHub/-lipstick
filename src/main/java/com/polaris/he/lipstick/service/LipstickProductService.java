package com.polaris.he.lipstick.service;

import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.entity.ColorCard;

import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-16 20:42
 * Description:
 */
public interface LipstickProductService {

    /**
     * @return
     */
    List<Brand> getBrands();

    /**
     * @param id
     * @return
     */
    Brand getBrand(String id);

    /**
     * @param brandCodes
     * @return
     */
    List<Category> getCategories(List<String> brandCodes);

    /**
     * @param categoryId
     * @return
     */
    List<ColorCard> getColorCards(String categoryId);

    /**
     * @param colorCardId
     * @return
     */
    ColorCard getColorCard(String colorCardId);
}