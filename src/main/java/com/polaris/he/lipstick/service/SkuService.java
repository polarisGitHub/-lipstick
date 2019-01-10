package com.polaris.he.lipstick.service;

import com.polaris.he.lipstick.entity.Sku;
import com.polaris.he.lipstick.entity.SkuAggregation;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface SkuService {

    /**
     *
     * @param type
     * @param code
     * @return
     */
    Sku getByCode(String type,String code);

    /**
     *
     * @param type
     * @param code
     * @return
     */
    SkuAggregation getAggregationByCode(String type, String code);
}