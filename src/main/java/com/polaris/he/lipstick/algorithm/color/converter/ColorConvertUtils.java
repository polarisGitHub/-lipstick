package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.Color;
import com.polaris.he.lipstick.utils.SpringContextUtils;

/**
 * User: hexie
 * Date: 2018-12-24 20:57
 * Description:
 */
public class ColorConvertUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Color, R extends Color> T convert(R color, Class<T> targetClazz) {
        return SpringContextUtils.getBean(ColorConvertService.class).convert(color, targetClazz);
    }
}