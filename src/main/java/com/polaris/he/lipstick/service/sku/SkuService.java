package com.polaris.he.lipstick.service.sku;

import com.polaris.he.lipstick.entity.Sku;
import com.polaris.he.lipstick.entity.SkuAggregation;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface SkuService {

    /**
     * @param collection
     * @return
     */
    int save(String type, Collection<Sku> collection);

    /**
     * @param type
     * @param code
     * @return
     */
    Sku getByCode(String type, String code);

    /**
     * @param type
     * @param code
     * @return
     */
    SkuAggregation getAggregationByCode(String type, String code);
}