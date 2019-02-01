package com.polaris.he.lipstick.entity.biz.lipstick;

import com.polaris.he.lipstick.annotation.Encryption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-01 23:09
 * Description:
 */
@Getter
@Setter
@ToString
public class LipstickFavoriteItem {

    @Encryption
    private Long id;

    private String brandName;

    private String goodsName;

    private String skuCode;

    private String skuName;

    private String color;

    private String colorNo;
}