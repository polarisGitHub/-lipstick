package com.polaris.he.lipstick.dao.objects;

import com.polaris.he.framework.dao.object.GoodsDO;
import com.polaris.he.framework.dao.object.SkuDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-05 23:17
 * Description:
 */
@Getter
@Setter
@ToString
public class LipstickAggregationDO {

    private String brandCode;

    private String categoryCode;

    private GoodsDO goods;

    private SkuDO sku;
}