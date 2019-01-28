package com.polaris.he.lipstick.service.sku;

import com.polaris.he.lipstick.entity.sku.Goods;
import com.polaris.he.lipstick.entity.sku.GoodsCategoryMapping;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface GoodsService {

    /**
     *
     *
     * @param collection
     * @return
     */
    int save(Collection<Goods> collection);

    /**
     *
     * @param collection
     * @return
     */
    int saveGoodsCategoriesMapping(Collection<GoodsCategoryMapping> collection);

    /**
     * @param type
     * @param code
     * @return
     */
    Goods getByCode(String type, String code);
}