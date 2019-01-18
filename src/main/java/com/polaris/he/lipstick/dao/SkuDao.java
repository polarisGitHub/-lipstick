package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.dao.object.SkuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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

    /**
     * @param type
     * @param skuCodes
     * @return
     */
    List<SkuDO> getByCodes(String type, Set<String> skuCodes);
}