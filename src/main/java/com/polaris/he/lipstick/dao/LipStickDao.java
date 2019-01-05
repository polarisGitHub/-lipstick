package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.LipStickDO;
import com.polaris.he.lipstick.dao.object.LipstickAggregationDO;
import com.polaris.he.lipstick.dao.object.LipstickSearchDO;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-08 15:02
 * Description:
 */
public interface LipStickDao extends Dao {

    /**
     * @param inserts
     * @return
     */
    int bulkInsert(List<LipStickDO> inserts);


    /**
     * @param search
     */
    LipstickAggregationDO search(LipstickSearchDO search);
}