package com.polaris.he.lipstick.algorithm.color.distance;

import com.google.common.collect.ImmutableMap;
import com.polaris.he.lipstick.algorithm.color.data.ColorDistanceAlgorithm;

import java.util.Optional;

/**
 * User: hexie
 * Date: 2018-12-23 17:07
 * Description:
 */
public class ColorDistanceComputerFactory {

    private static final ImmutableMap<ColorDistanceAlgorithm, ColorDistance> REPOSITORY = ImmutableMap.<ColorDistanceAlgorithm, ColorDistance>builder().
            put(ColorDistanceAlgorithm.EUCLIDEAN, new EuclideanColorDistance()).
            put(ColorDistanceAlgorithm.EUCLIDEAN_WEIGHTING, new EuclideanWeightingColorDistance()).
            put(ColorDistanceAlgorithm.CIE76, new CIE76StandardColorDistance()).
            put(ColorDistanceAlgorithm.CIEDE2000, new CIEDE2000StandardColorDistance()).
            build();

    public static ColorDistance getComputer(ColorDistanceAlgorithm algorithm) {
        return Optional.ofNullable(REPOSITORY.get(algorithm)).orElseThrow(() -> new UnsupportedOperationException("不支持算法"));
    }
}