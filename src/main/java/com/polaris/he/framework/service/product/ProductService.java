package com.polaris.he.framework.service.product;

import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.sku.Brand;
import com.polaris.he.framework.entity.sku.Category;

import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-16 20:42
 * Description:
 */
public interface ProductService {

    /**
     * @return
     */
    List<Brand> getBrands(String type);

    /**
     * @param code
     * @return
     */
    Brand getBrand(String type, String code);

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

    /**
     * @param sku
     * @return
     */
    Object getBySkuCode(BaseSkuInfo sku);
}