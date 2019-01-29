package com.polaris.he.lipstick.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polaris.he.lipstick.entity.favorites.FavoriteItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * User: hexie
 * Date: 2019-01-05 22:20
 * Description:
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.
            json().
            serializationInclusion(JsonInclude.Include.NON_NULL).
            featuresToEnable(MapperFeature.PROPAGATE_TRANSIENT_MARKER).
            build();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static <T> T toJavaObject(String content, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(content, clazz);
        } catch (IOException e) {
            log.error("read json string error", e);
        }
        return null;
    }

    public static String toJsonString(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("write json string error", e);
        }
        return null;
    }
}