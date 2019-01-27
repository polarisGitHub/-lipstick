package com.polaris.he.lipstick.service.sku;

import com.polaris.he.lipstick.entity.Goods;
import com.polaris.he.lipstick.entity.GoodsCategoryMapping;

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
     * @param type
     * @param collection
     * @return
     */
    int save(String type, Collection<Goods> collection);

    /**
     *
     *
     * @param type
     * @param collection
     * @return
     */
    int saveGoodsCategoriesMapping(String type, Collection<GoodsCategoryMapping> collection);

    /**
     * @param type
     * @param code
     * @return
     */
    Goods getByCode(String type, String code);
}