package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.BrandDO;

import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-17 22:09
 * Description:
 */
public interface BrandDao extends Dao {

    List<BrandDO> getAll();
}