package com.polaris.he.lipstick.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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
            build();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
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