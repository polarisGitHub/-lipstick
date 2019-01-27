package com.polaris.he.lipstick.entity.sku;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-27 22:16
 * Description:
 */
@Getter
@Setter
@ToString
public class BaseSkuInfo {

    private String brandCode;

    @JsonIgnore
    private String type; // 具体type具体业务关心，不在接口返回

    private String skuCode;
}