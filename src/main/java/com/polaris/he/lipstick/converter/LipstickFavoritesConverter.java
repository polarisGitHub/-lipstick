package com.polaris.he.lipstick.converter;

import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.annotation.FavoritesConverter;
import com.polaris.he.framework.dao.object.FavoritesDO;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.entity.LipstickExtension;
import com.polaris.he.lipstick.entity.LipstickFavoriteItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: hexie
 * Date: 2019-02-02 22:58
 * Description:
 */
@Service
@FavoritesConverter(type = CosmeticsEnum.LIPSTICK)
public class LipstickFavoritesConverter implements Converter<FavoritesDO, LipstickFavoriteItem> {

    @Override
    public LipstickFavoriteItem convert(FavoritesDO source) {
        LipstickFavoriteItem data = new LipstickFavoriteItem();
        data.setId(source.getId());
        data.setBrandName(source.getBrandName());
        data.setGoodsName(source.getGoodsName());
        data.setSkuCode(source.getSkuCode());
        data.setSkuName(source.getSkuName());
        LipstickExtension extension = JsonUtils.toJavaObject(source.getSkuExtension(), LipstickExtension.class);
        data.setColorNo(Optional.ofNullable(extension).map(LipstickExtension::getColorNo).orElse(""));
        data.setColor(Optional.ofNullable(extension).map(LipstickExtension::getColor).orElse(""));
        return data;
    }
}