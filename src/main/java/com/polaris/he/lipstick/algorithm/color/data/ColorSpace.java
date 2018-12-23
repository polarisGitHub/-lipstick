package com.polaris.he.lipstick.algorithm.color.data;

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
    }

    public ColorSpace(Lab lab) {
        this.lab = lab;
    }

    public ColorSpace(Hsl hsl) {
        this.hsl = hsl;
    }
}