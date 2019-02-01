package com.polaris.he.framework.dao.object;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * User: hexie
 * Date: 2019-01-30 20:52
 * Description:
 */
@Getter
@Setter
@ToString
public class FavoritesDO {

    private Long id;

    private String openId;

    private String source;

    private String type;

    private String brandCode;

    private String brandName;

    private String goodsCode;

    private String goodsName;

    private String goodsIllustration;

    private String goodsUrl;

    private String skuCode;

    private String skuName;

    private String skuByName;

    private String skuUrl;

    private JsonNode skuExtension;
}