package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.Srgb;
import com.polaris.he.lipstick.algorithm.color.data.Xyz;
import org.springframework.stereotype.Component;

/**
 * User: hexie
 * Date: 2018-12-23 22:05
 * Description:
 */
@Component
public class RgbXyzConverter implements ColorConverter<Srgb, Xyz> {
    @Override
    public Xyz transform(Srgb sRgb) {
        double r = gamma(sRgb.getR() / 255.0);
        double g = gamma(sRgb.getG() / 255.0);
        double b = gamma(sRgb.getB() / 255.0);
        double x = 0.412453 * r + 0.357580 * g + 0.180423 * b;
        double y = 0.212671 * r + 0.715160 * g + 0.072169 * b;
        double z = 0.019334 * r + 0.119193 * g + 0.950227 * b;
        return new Xyz(x * 100.0, y * 100.0, z * 100.0);
    }

    private double gamma(double x) {
        if (x > 0.04045) {
            return Math.pow((x + 0.055) / 1.055, 2.4);
        } else {
            return x / 12.92;
        }
    }

    @Override
    public Srgb inverse(Xyz xyz) {
        throw new UnsupportedOperationException();
//        double r = 3.240479 * xyz.getX() - 1.537150 * xyz.getY() - 0.498535 * xyz.getZ();
//        double g = -0.969256 * xyz.getX() + 1.875992 * xyz.getY() + 0.041556 * xyz.getZ();
//        double b = 0.055648 * xyz.getX() - 0.204043 * xyz.getY() + 1.057311 * xyz.getZ();
//        return new Srgb(
//                (int) Math.min(Math.max(0, r), 255),
//                (int) Math.min(Math.max(0, g), 255),
//                (int) Math.min(Math.max(0, b), 255)
//        );
    }
}