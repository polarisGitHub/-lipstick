package com.polaris.he.framework.utils;

import com.polaris.he.framework.entity.sku.BaseSkuInfo;

/**
 * User: hexie
 * Date: 2019-01-28 21:55
 * Description:
 */
public class BaseSkuInfoUtils {

    public static BaseSkuInfo create(String brand, String type, String skuCode) {
        BaseSkuInfo sku = new BaseSkuInfo();
        sku.setBrandCode(brand);
        sku.setType(type);
        sku.setSkuCode(skuCode);
        return sku;
    }
}