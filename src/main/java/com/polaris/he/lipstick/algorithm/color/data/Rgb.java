package com.polaris.he.lipstick.algorithm.color.data;

import lombok.*;

/**
 * r,g,b [0,255]
 * https://zh.wikipedia.org/zh-hans/%E4%B8%89%E5%8E%9F%E8%89%B2%E5%85%89%E6%A8%A1%E5%BC%8F
 * User: hexie
 * Date: 2018-12-23 16:49
 * Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rgb implements Color {

    private int r;
    private int g;
    private int b;

    @Override
    public ColorSystemEnum colorSystem() {
        return ColorSystemEnum.RGB;
    }
}