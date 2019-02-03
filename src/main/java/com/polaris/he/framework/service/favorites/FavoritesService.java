package com.polaris.he.framework.service.favorites;

import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.user.UserInfo;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-27 22:01
 * Description:
 */
public interface FavoritesService {

    /**
     * @param sku
     * @param user
     * @return
     */
    boolean checkFavorite(BaseSkuInfo sku, UserInfo user);

    /**
     * @param sku
     * @param user
     */
    int save(BaseSkuInfo sku, UserInfo user);

    /**
     * @param id
     * @param user
     */
    int delete(Long id, UserInfo user);

    /**
     * @param user
     * @param type
     * @return
     */
    List<?> queryUserFavorite(UserInfo user, String type);
}