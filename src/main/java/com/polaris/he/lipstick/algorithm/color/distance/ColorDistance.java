package com.polaris.he.lipstick.algorithm.color.distance;

import com.polaris.he.lipstick.algorithm.color.data.ColorSpace;

/**
 * User: hexie
 * Date: 2018-12-23 16:58
 * Description:
 */
public interface ColorDistance {

    double compute(ColorSpace source, ColorSpace target);
}