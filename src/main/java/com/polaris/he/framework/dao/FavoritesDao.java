package com.polaris.he.framework.dao;

import com.polaris.he.framework.dao.object.FavoritesDO;
import com.polaris.he.framework.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-28 20:53
 * Description:
 */
public interface FavoritesDao extends Dao {


    /**
     * @param favorite
     * @return
     */
    int insert(FavoritesDO favorite);

    /**
     * @param id
     * @param user
     * @return
     */
    int delete(@Param("id") Long id, @Param("user") UserInfo user);

    /**
     * @param favorite
     * @return
     */
    boolean checkFavorite(FavoritesDO favorite);

    /**
     * @param source
     * @param openId
     * @return
     */
    List<FavoritesDO> queryFavorites(@Param("source") String source, @Param("openId") String openId, @Param("type") String type);
}