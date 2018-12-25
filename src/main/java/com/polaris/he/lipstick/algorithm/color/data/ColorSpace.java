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

    private Srgb sRgb;

    private Lab lab;

    private Hsl hsl;

    public ColorSpace(Srgb sRgb) {
        this.sRgb = sRgb;
        this.lab = ColorConvertUtils.convert(sRgb, Lab.class);
    }

    public ColorSpace(Lab lab) {
        this.sRgb = ColorConvertUtils.convert(lab, Srgb.class);
        this.lab = lab;
    }

    public ColorSpace(Hsl hsl) {
        this.hsl = hsl;
    }
}