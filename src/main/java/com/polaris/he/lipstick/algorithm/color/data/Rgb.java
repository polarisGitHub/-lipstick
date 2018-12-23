package com.polaris.he.lipstick.algorithm.color.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-23 16:49
 * Description:
 */
@Getter
@Setter
@ToString
public class Rgb implements Color {

    private Integer r;
    private Integer g;
    private Integer b;

    @Override
    public ColorSystemEnum colorSystem() {
        return ColorSystemEnum.RGB;
    }
}