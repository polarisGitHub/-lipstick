package com.polaris.he.lipstick.dao.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsCategoryMappingDO extends BaseDO {

    private String type;

    private String categoryCode;

    private String goodsCode;

}
