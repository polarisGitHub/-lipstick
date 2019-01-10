package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.CategoryDO;
import org.apache.ibatis.annotations.Param;

/**
 * User: hexie
 * Date: 2018-12-22 22:20
 * Description:
 */
public interface CategoryDao extends Dao {

    /**
     * @param code
     * @return
     */
    CategoryDO getByCode(@Param("type") String type, @Param("code") String code);
}