package com.polaris.he.lipstick.entity;

import com.polaris.he.framework.annotation.Encryption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-21 21:28
 * Description:
 */
@Getter
@Setter
@ToString
public class LipstickItem {

    private String brandCode;

    private String brandName;

    private String categoryCode;

    private String categoryName;

    @Encryption
    private String goodsCode;

    private String goodsName;

    @Encryption
    private String skuCode;

    private String skuName;

    private String colorNo;

    private String color;

    private String color1;
}