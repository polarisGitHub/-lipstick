package com.polaris.he.lipstick.dao;

import com.polaris.he.framework.dao.Dao;
import com.polaris.he.lipstick.dao.objects.LipstickSearchDO;
import com.polaris.he.lipstick.dao.objects.LipstickAggregationDO;

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