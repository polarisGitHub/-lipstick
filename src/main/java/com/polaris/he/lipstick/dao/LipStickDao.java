package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.LipStickDO;

import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-08 15:02
 * Description:
 */
public interface LipStickDao extends Dao {

    int bulkInsert(List<LipStickDO> inserts);
}