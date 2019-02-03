package com.polaris.he.framework.entity.favorites;

import com.polaris.he.framework.annotation.Encryption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-03 23:12
 * Description:
 */
@Getter
@Setter
@ToString
public class FavoritesSaveEntity {

    private String brandCode;

    @Encryption
    private String skuCode;
}