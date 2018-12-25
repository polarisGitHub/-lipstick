package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.Lab;
import com.polaris.he.lipstick.algorithm.color.data.Srgb;
import com.polaris.he.lipstick.algorithm.color.data.Xyz;
import org.springframework.stereotype.Component;


/**
 * User: hexie
 * Date: 2018-12-23 17:22
 * Description:
 */
@Component
public class RgbLabConverter implements ColorConverter<Srgb, Lab> {

    private static final double FUNCTION_THRESHOLD = Math.pow(6.0 / 29, 3);

    private static final double XN = 95.0456;

    private static final double YN = 100.0;

    private static final double ZN = 108.8754;

    @Override
    public Lab transform(Srgb sRgb) {
        Xyz xyz = ColorConvertUtils.convert(sRgb, Xyz.class);

        double normalX = f(xyz.getX() / XN);
        double normalY = f(xyz.getY() / YN);
        double normalZ = f(xyz.getZ() / ZN);
        double l = 116 * normalY - 16;
        double a = 500 * (normalX - normalY);
        double b = 200 * (normalY - normalZ);
        return new Lab(l, a, b);
    }

    private double f(double t) {
        if (t > FUNCTION_THRESHOLD) {
            return Math.pow(t, 1.0 / 3);
        } else {
            return t * 841.0 / 108 + 4.0 / 29;
        }
    }

    @Override
    public Srgb inverse(Lab color) {
        throw new UnsupportedOperationException();
    }

}