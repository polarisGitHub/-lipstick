package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.LipstickAggregationDO;
import com.polaris.he.lipstick.dao.object.LipstickSearchDO;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-07 23:26
 * Description:
 */
public interface LipstickSearchDao extends Dao {

    /**
     * @param search
     */
    List<LipstickAggregationDO> search(LipstickSearchDO search);
}