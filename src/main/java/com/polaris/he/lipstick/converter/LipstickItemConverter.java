package com.polaris.he.lipstick.converter;

import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.annotation.FrameworkBizConverter;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.entity.sku.SkuAggregation;
import com.polaris.he.lipstick.entity.LipstickExtension;
import com.polaris.he.lipstick.entity.LipstickListItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * User: hexie
 * Date: 2019-02-07 00:18
 * Description:
 */
@Component
@FrameworkBizConverter(type = CosmeticsEnum.LIPSTICK, biz = "product")
public class LipstickItemConverter implements Converter<SkuAggregation, LipstickListItem> {

    @Override
    public LipstickListItem convert(SkuAggregation source) {
        LipstickListItem ret = new LipstickListItem();
        ret.setBrandCode(source.getBrand().getCode());
        ret.setBrandName(source.getBrand().getName());
        // TODO
//        ret.setCategoryCode(skuInfo.getCategories().stream().map(Category::getCode).collect(Collectors.joining(",")));
//        ret.setCategoryName(skuInfo.getCategories().stream().map(Category::getName).collect(Collectors.joining(",")));
        ret.setGoodsCode(source.getGoods().getGoodsCode());
        ret.setGoodsName(source.getGoods().getGoodsName());
        ret.setSkuCode(source.getSku().getSkuCode());
        ret.setSkuName(source.getSku().getSkuName());
        LipstickExtension extension = JsonUtils.toJavaObject(source.getSku().getExtension(), LipstickExtension.class);
        ret.setColorNo(Optional.ofNullable(extension).map(LipstickExtension::getColorNo).orElse(""));
        ret.setColor(Optional.ofNullable(extension).map(LipstickExtension::getColor).orElse(""));
        ret.setColor1(Optional.ofNullable(extension).map(LipstickExtension::getColor1).orElse(null));
        return ret;
    }
}