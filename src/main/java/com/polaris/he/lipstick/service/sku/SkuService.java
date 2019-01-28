package com.polaris.he.lipstick.service.sku;

import com.polaris.he.lipstick.entity.sku.BaseSkuInfo;
import com.polaris.he.lipstick.entity.sku.Sku;
import com.polaris.he.lipstick.entity.sku.SkuAggregation;

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
    int save(Collection<Sku> collection);

    /**
     * @param sku
     * @return
     */
    Sku getBySkuInfo(BaseSkuInfo sku);

    /**
     * @param sku
     * @return
     */
    SkuAggregation getAggregationBySkuInfo(BaseSkuInfo sku);
}