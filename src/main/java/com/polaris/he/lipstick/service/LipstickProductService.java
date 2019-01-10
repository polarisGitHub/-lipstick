package com.polaris.he.lipstick.service;

import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.entity.ColorNo;
import com.polaris.he.lipstick.entity.LipstickItem;

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
     * @param code
     * @return
     */
    Brand getBrand(String code);

    /**
     * @param code
     * @return
     */
    Category getCategory(String code);

    /**
     * @param brandCodes
     * @return
     */
    List<Category> getCategories(List<String> brandCodes);

    /**
     *
     * @param skuCode
     * @return
     */
    LipstickItem getBySkuCode(String skuCode);
}