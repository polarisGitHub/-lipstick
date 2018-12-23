package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.Color;

/**
 * 颜色空间变换
 * User: hexie
 * Date: 2018-12-23 16:51
 * Description:
 */
public interface ColorConverter<S extends Color, T extends Color> {

    /**
     * @param color
     * @return
     */
    T transform(S color);

    /**
     * @param color
     * @return
     */
    S inverse(T color);
}