package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.SkuDO;
import org.apache.ibatis.annotations.Param;

/**
 * User: hexie
 * Date: 2019-01-10 22:37
 * Description:
 */
public interface SkuDao extends Dao {

    /**
     * @param type
     * @param code
     * @return
     */
    SkuDO getByCode(@Param("type") String type, @Param("code") String code);
}