package com.polaris.he.lipstick.service;

import com.polaris.he.lipstick.entity.Category;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 21:13
 * Description:
 */
public interface CategoryService {

    /**
     * @param code
     * @return
     */
    Category getCategory(String type, String code);

    /**
     * @param brandCodes
     * @return
     */
    List<Category> getCategories(String type, List<String> brandCodes);
}