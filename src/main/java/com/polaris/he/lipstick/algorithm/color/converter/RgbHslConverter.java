package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.Hsl;
import com.polaris.he.lipstick.algorithm.color.data.Srgb;
import org.springframework.stereotype.Component;

/**
 * User: hexie
 * Date: 2018-12-23 17:20
 * Description:
 */
@Component
public class RgbHslConverter implements ColorConverter<Srgb, Hsl> {

    @Override
    public Hsl transform(Srgb color) {
        return null;
    }

    @Override
    public Srgb inverse(Hsl color) {
        return null;
    }
}