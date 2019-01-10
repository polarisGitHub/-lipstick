package com.polaris.he.lipstick.service;

import com.polaris.he.lipstick.entity.Goods;

/**
 * User: hexie
 * Date: 2019-01-10 21:12
 * Description:
 */
public interface GoodsService {

    /**
     * @param type
     * @param code
     * @return
     */
    Goods getByCode(String type, String code);
}