package com.polaris.he.lipstick.algorithm.color.converter;


import com.polaris.he.lipstick.algorithm.color.data.ColorSystemEnum;

/**
 * User: hexie
 * Date: 2018-12-23 17:20
 * Description:
 */
public enum ColorConverterEnum {

    RGB_HSL(ColorSystemEnum.RGB, ColorSystemEnum.HSL, new RgbHslConverter()),
    RGB_LAB(ColorSystemEnum.RGB, ColorSystemEnum.LAB, new RgbLabConverter()),
    ;

    private ColorSystemEnum source;

    private ColorSystemEnum target;

    private ColorConverter converter;

    ColorConverterEnum(ColorSystemEnum source, ColorSystemEnum target, ColorConverter converter) {
        this.source = source;
        this.target = target;
        this.converter = converter;
    }}