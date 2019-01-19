package com.polaris.he.lipstick.dao;


import com.polaris.he.lipstick.dao.object.GoodsCategoryMappingDO;

import java.util.Collection;

public interface GoodsCategoryMappingDao extends Dao {


    /**
     * @param type
     * @param goodsCodes
     * @return
     */
    int deleteByGoodsCode(String type, Collection<String> goodsCodes);

    /**
     * @param inserts
     * @return
     */
    int insert(Collection<GoodsCategoryMappingDO> inserts);
}
