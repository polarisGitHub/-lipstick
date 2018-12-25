package com.polaris.he.lipstick.algorithm.color.converter;

import com.polaris.he.lipstick.algorithm.color.data.ColorDistanceAlgorithm;
import com.polaris.he.lipstick.algorithm.color.data.ColorSpace;
import com.polaris.he.lipstick.algorithm.color.distance.ColorDistanceComputerFactory;
import com.polaris.he.lipstick.algorithm.color.utils.HexColorUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * User: hexie
 * Date: 2018-12-25 23:00
 * Description:
 */
@SpringBootTest
public class ColorDistanceTestCase extends AbstractTestNGSpringContextTests {

    @Test
    public void main() {
        ColorSpace source = new ColorSpace(HexColorUtils.hex2Rgb("#133337"));
        ColorSpace target = new ColorSpace(HexColorUtils.hex2Rgb("#103346"));

        System.out.println(ColorDistanceComputerFactory.getComputer(ColorDistanceAlgorithm.EUCLIDEAN).compute(source, target));
        System.out.println(ColorDistanceComputerFactory.getComputer(ColorDistanceAlgorithm.EUCLIDEAN_WEIGHTING).compute(source, target));
        System.out.println(ColorDistanceComputerFactory.getComputer(ColorDistanceAlgorithm.CIE76).compute(source, target));
    }
}