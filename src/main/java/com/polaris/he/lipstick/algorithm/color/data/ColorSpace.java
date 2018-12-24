package com.polaris.he.lipstick.algorithm.color.data;

import com.polaris.he.lipstick.algorithm.color.converter.ColorConvertUtils;
import lombok.Getter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-23 16:49
 * Description:
 */
@Getter
@ToString
public class ColorSpace {

    private Rgb rgb;

    private Lab lab;

    private Hsl hsl;

    public ColorSpace(Rgb rgb) {
        this.rgb = rgb;
        this.lab = ColorConvertUtils.convert(rgb, Lab.class);
    }

    public ColorSpace(Lab lab) {
        this.rgb = ColorConvertUtils.convert(lab, Rgb.class);
        this.lab = lab;
    }

    public ColorSpace(Hsl hsl) {
        this.hsl = hsl;
    }
}