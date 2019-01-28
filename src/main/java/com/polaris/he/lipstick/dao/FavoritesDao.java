package com.polaris.he.lipstick.dao;

import com.polaris.he.lipstick.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * User: hexie
 * Date: 2019-01-28 20:53
 * Description:
 */
public interface FavoritesDao extends Dao {


    int save(@Param("user") UserInfo user);

    /**
     * @param id
     * @param user
     * @return
     */
    int delete(@Param("id") Long id, @Param("user") UserInfo user);

    /**
     *
     * @param user
     * @return
     */
    int queryByUser(@Param("user") UserInfo user);
}