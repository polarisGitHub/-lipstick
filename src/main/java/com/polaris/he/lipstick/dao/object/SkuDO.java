package com.polaris.he.lipstick.dao.object;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-05 22:32
 * Description:
 */
@Getter
@Setter
@ToString
public class SkuDO extends BaseDO {

    private String type;

    private String brandCode;

    private String goodsCode;

    private String skuCode;

    private String skuName;

    private String url;

    private JsonNode extension;
}