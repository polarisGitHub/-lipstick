package com.polaris.he.lipstick.entity.sku;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-10 22:26
 * Description:
 */
@Getter
@Setter
@ToString
public class Goods {

    private String brandCode;

    private String goodsCode;

    private String goodsName;

    private String illustration;

    private String url;
}