package com.polaris.he.lipstick.algorithm.color.distance;

import com.polaris.he.lipstick.algorithm.color.data.ColorSpace;
import com.polaris.he.lipstick.algorithm.color.utils.HexColorUtils;

/**
 * https://zh.wikipedia.org/wiki/%E9%A2%9C%E8%89%B2%E5%B7%AE%E5%BC%82#CIE76
 * User: hexie
 * Date: 2018-12-23 17:04
 * Description:
 */
public class CIE76StandardColorDistance implements ColorDistance {

    @Override
    public double compute(ColorSpace source, ColorSpace target) {
        double Eab = Math.pow(source.getLab().getL() - target.getLab().getL(), 2) +
                Math.pow(source.getLab().getA() - target.getLab().getA(), 2) +
                Math.pow(source.getLab().getB() - target.getLab().getB(), 2);
        return Math.sqrt(Eab);// 一般认为2.3为最小可觉差
    }
}