package com.polaris.he.lipstick.entity;

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
public class LipstickItem {

    private String brandCode;

    private String brandName;

    private String categoryCode;

    private String categoryName;

    private String colorNo;

    private String color;
}