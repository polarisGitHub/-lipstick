package com.polaris.he.lipstick.entity.favorites;

import com.polaris.he.lipstick.annotation.Encryption;
import com.polaris.he.lipstick.entity.sku.Sku;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-27 21:55
 * Description:
 */
@Getter
@Setter
@ToString
public class FavoriteItem {

    @Encryption
    private Long id;

    private Sku sku;
}