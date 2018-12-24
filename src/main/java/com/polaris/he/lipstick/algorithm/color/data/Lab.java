package com.polaris.he.lipstick.algorithm.color.data;

import lombok.*;

/**
 * l [0,100]
 * a,b [-128,127]
 * https://zh.wikipedia.org/zh-hans/Lab%E8%89%B2%E5%BD%A9%E7%A9%BA%E9%97%B4
 * User: hexie
 * Date: 2018-12-23 16:50
 * Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Lab implements Color {

    private double l;
    private double a;
    private double b;

    @Override
    public ColorSystemEnum colorSystem() {
        return ColorSystemEnum.LAB;
    }
}