package com.polaris.he.lipstick.algorithm.color.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-23 16:50
 * Description:
 */
@Getter
@Setter
@ToString
public class Lab implements Color {

    @Override
    public ColorSystemEnum colorSystem() {
        return ColorSystemEnum.LAB;
    }
}