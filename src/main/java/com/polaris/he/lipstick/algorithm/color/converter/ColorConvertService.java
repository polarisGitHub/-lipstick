package com.polaris.he.lipstick.algorithm.color.converter;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.polaris.he.lipstick.algorithm.color.data.Color;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

/**
 * User: hexie
 * Date: 2018-12-23 22:14
 * Description:
 */
@Slf4j
@Service
class ColorConvertService implements ApplicationContextAware {

    private Table<Type, Type, ColorConverter> transform = HashBasedTable.create();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("开始扫描ColorConverter");
        Map<String, ColorConverter> beans = applicationContext.getBeansOfType(ColorConverter.class);
        beans.forEach((key, value) -> {
            Arrays.stream(value.getClass().getGenericInterfaces()).
                    filter(i -> ((ParameterizedType) i).getRawType().equals(ColorConverter.class)).
                    findFirst().
                    ifPresent(c -> {
                        Type[] actual = ((ParameterizedType) c).getActualTypeArguments();
                        transform.put(actual[0], actual[1], value);
                    });
        });

    }

    @SuppressWarnings("unchecked")
    public <T extends Color, R extends Color> T convert(R color, Class<T> targetClazz) {
        Class sourceClazz = color.getClass();
        if (transform.containsRow(sourceClazz) && transform.containsColumn(targetClazz)) {
            return (T) transform.get(sourceClazz, targetClazz).transform(color);
        } else if (transform.containsRow(targetClazz) && transform.containsColumn(sourceClazz)) {
            return (T) transform.get(targetClazz, sourceClazz).inverse(color);
        }
        log.error("不支持的转换方式，source={},target={}", sourceClazz, targetClazz);
        throw new UnsupportedOperationException();
    }
}