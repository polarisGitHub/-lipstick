package com.polaris.he.lipstick.entity;

import com.polaris.he.framework.annotation.Encryption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-05 21:33
 * Description:
 */
@Setter
@Getter
@ToString
public class LipstickListItem {

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

    private String figure;
}