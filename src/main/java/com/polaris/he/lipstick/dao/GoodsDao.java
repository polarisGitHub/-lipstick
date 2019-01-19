package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.GoodsDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 23:21
 * Description:
 */
public interface GoodsDao extends Dao {

    /**
     * @param inserts
     * @return
     */
    int insert(Collection<GoodsDO> inserts);

    /**
     * @param update
     * @return
     */
    int update(GoodsDO update);

    /**
     * @param type
     * @param code
     * @return
     */
    GoodsDO getByCode(@Param("type") String type, @Param("code") String code);

    /**
     * @param type
     * @param goodsCodeSet
     * @return
     */
    List<GoodsDO> getByCodeList(@Param("type") String type, @Param("collection") Collection<String> goodsCodeSet);
}