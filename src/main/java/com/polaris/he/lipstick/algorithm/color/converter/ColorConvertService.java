package com.polaris.he.lipstick.algorithm.color.converter;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.polaris.he.lipstick.algorithm.color.data.Color;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Set;

/**
 * User: hexie
 * Date: 2018-12-23 22:14
 * Description:
 */
@Slf4j
@Service
public class ColorConvertService {

    private Table<Class, Class, ColorConverter> transform = HashBasedTable.create();
    private Table<Class, Class, ColorConverter> inverse = HashBasedTable.create();

    @PostConstruct
    public void init() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(ColorConverter.class));
        Set<BeanDefinition> beansDefinition = scanner.findCandidateComponents("com.polaris.he.lipstick.algorithm.color.converter");
        beansDefinition.forEach(l -> {
            try {
                Class<?> clazz = Class.forName(l.getBeanClassName());
                Arrays.stream(clazz.getGenericInterfaces()).
                        filter(i -> ((ParameterizedType) i).getRawType().equals(ColorConverter.class)).
                        findFirst().
                        ifPresent(type -> {
                            ParameterizedType parameterized = (ParameterizedType) type;
                            Class source = (Class) parameterized.getActualTypeArguments()[0];
                            Class target = (Class) parameterized.getActualTypeArguments()[1];
                            try {
                                ColorConverter instance = (ColorConverter) clazz.getDeclaredConstructor().newInstance();
                                transform.put(source, target, instance);
                                inverse.put(target, source, instance);
                            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            }

                        });
            } catch (ClassNotFoundException e) {
                log.error("扫描ColorConverter错误", e);
            }
        });
    }


}