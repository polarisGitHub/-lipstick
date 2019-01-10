package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.GoodsDO;
import org.apache.ibatis.annotations.Param;

/**
 * User: hexie
 * Date: 2019-01-10 23:21
 * Description:
 */
public interface GoodsDao extends Dao {

    /**
     * @param type
     * @param code
     * @return
     */
    GoodsDO getByCode(@Param("type") String type, @Param("code") String code);
}