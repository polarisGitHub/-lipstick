package com.polaris.he.lipstick.entity.sku;

import com.fasterxml.jackson.databind.JsonNode;
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
public class Sku {

    private String brandCode;

    private String goodsCode;

    private String skuCode;

    private String skuName;

    private String skuByName;

    private String url;

    private JsonNode extension;
}