package com.polaris.he.lipstick.algorithm.color.distance;

import com.polaris.he.lipstick.algorithm.color.data.ColorSpace;

/**
 * @url https://www.compuphase.com/cmetric.htm
 * User: hexie
 * Date: 2018-12-23 17:02
 * Description:
 */
public class EuclideanWeightingColorDistance implements ColorDistance {

    @Override
    public double compute(ColorSpace source, ColorSpace target) {
        double r = (source.getSRgb().getR() + target.getSRgb().getR()) / 2.0;
        double deltaR = source.getSRgb().getR() - target.getSRgb().getR();
        double deltaG = source.getSRgb().getG() - target.getSRgb().getG();
        double deltaB = source.getSRgb().getB() - target.getSRgb().getB();
        return Math.sqrt((2 + r / 256.0) * deltaR * deltaR + 4 * deltaG * deltaG + (2 + (255 - r) / 256) * deltaB * deltaB);
    }
}