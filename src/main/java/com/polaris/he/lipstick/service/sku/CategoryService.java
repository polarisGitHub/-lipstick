package com.polaris.he.lipstick.service.sku;

import com.polaris.he.lipstick.entity.sku.Category;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 21:13
 * Description:
 */
public interface CategoryService {

    /**
     * @param type
     * @return
     */
    List<Category> getCategories(String type);


    /**
     * @param type
     * @param brandCode
     * @return
     */
    List<Category> getCategoriesByBrand(String type, String brandCode);

    /**
     * @param type
     * @param brandCodes
     * @return
     */
    List<Category> getCategoriesByBrands(String type, List<String> brandCodes);


    /**
     * @param type
     * @param goodsCode
     * @return
     */
    List<Category> getCategoriesByGoods(String type, String goodsCode);
}