package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.Hsl;
import com.polaris.he.lipstick.algorithm.color.data.Rgb;
import org.springframework.stereotype.Component;

/**
 * User: hexie
 * Date: 2018-12-23 17:20
 * Description:
 */
@Component
public class RgbHslConverter implements ColorConverter<Rgb, Hsl> {

    @Override
    public Hsl transform(Rgb color) {
        return null;
    }

    @Override
    public Rgb inverse(Hsl color) {
        return null;
    }
}