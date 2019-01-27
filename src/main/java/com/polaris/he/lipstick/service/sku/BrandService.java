package com.polaris.he.lipstick.service.sku;

import com.polaris.he.lipstick.entity.Brand;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface BrandService {

    /**
     *
     * @param type
     * @return
     */
    List<Brand> getBrands(String type);

    /**
     *
     * @param type
     * @param code
     * @return
     */
    Brand getBrand(String type, String code);
}