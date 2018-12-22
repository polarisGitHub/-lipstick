package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.BrandCategoryMappingDO;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-22 22:21
 * Description:
 */
public interface BrandCategoryMappingDao extends Dao {

    List<BrandCategoryMappingDO> getCategoryByBrands(Collection<String> brands);
}