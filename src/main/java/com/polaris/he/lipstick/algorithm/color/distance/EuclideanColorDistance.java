package com.polaris.he.lipstick.algorithm.color.distance;

import com.polaris.he.lipstick.algorithm.color.data.ColorSpace;

/**
 * @url https://zh.wikipedia.org/wiki/%E9%A2%9C%E8%89%B2%E5%B7%AE%E5%BC%82#%E6%AC%A7%E6%B0%8F%E8%B7%9D%E7%A6%BB
 * User: hexie
 * Date: 2018-12-23 17:00
 * Description:
 */
public class EuclideanColorDistance implements ColorDistance {
    @Override
    public double compute(ColorSpace source, ColorSpace target) {
        double deltaR = source.getSRgb().getR() - target.getSRgb().getR();
        double deltaG = source.getSRgb().getG() - target.getSRgb().getG();
        double deltaB = source.getSRgb().getB() - target.getSRgb().getB();
        return Math.sqrt(deltaR * deltaR + deltaB * deltaB + deltaG * deltaG);
    }
}