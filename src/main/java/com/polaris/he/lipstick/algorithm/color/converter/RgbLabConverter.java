package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.Lab;
import com.polaris.he.lipstick.algorithm.color.data.Rgb;
import com.polaris.he.lipstick.algorithm.color.data.Xyz;
import org.springframework.stereotype.Component;


/**
 * User: hexie
 * Date: 2018-12-23 17:22
 * Description:
 */
@Component
public class RgbLabConverter implements ColorConverter<Rgb, Lab> {

    private static final double FUNCTION_THRESHOLD = Math.pow(6.0 / 29, 3);

    private static final double XN = 0.950456;

    private static final double YN = 1.0;

    private static final double ZN = 1.088754;

    @Override
    public Lab transform(Rgb rgb) {
        Xyz xyz = ColorConvertUtils.convert(rgb, Xyz.class);

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
    public Rgb inverse(Lab color) {
        throw new UnsupportedOperationException();
    }

}