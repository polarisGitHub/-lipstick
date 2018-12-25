package com.polaris.he.lipstick.algorithm.color.utils;

import com.polaris.he.lipstick.algorithm.color.data.Srgb;
import org.apache.commons.lang3.StringUtils;

/**
 * User: hexie
 * Date: 2018-12-25 22:49
 * Description:
 */
public class HexColorUtils {

    public static Srgb hex2Rgb(String hex) {
        String r = StringUtils.substring(hex, 1, 3);
        String g = StringUtils.substring(hex, 3, 5);
        String b = StringUtils.substring(hex, 5, 7);
        return new Srgb(Integer.parseInt(r, 16), Integer.parseInt(g, 16), Integer.parseInt(b, 16));
    }

    public static String rgb2Hex(Srgb rgb) {
        return String.format("#%02x%02x%02x", rgb.getR(), rgb.getG(), rgb.getB());
    }
}