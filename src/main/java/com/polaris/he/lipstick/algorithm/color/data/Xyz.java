package com.polaris.he.lipstick.algorithm.color.data;

import lombok.*;

/**
 * User: hexie
 * Date: 2018-12-23 21:32
 * Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Xyz implements Color {

    private double x;
    private double y;
    private double z;


    @Override
    public ColorSystemEnum colorSystem() {
        return ColorSystemEnum.XYZ;
    }
}