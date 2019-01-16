package com.polaris.he.lipstick.importer.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polaris.he.lipstick.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-16 21:14
 * Description:
 */
@Slf4j
@Component
public class JsonSourceConverter implements UploadByteConverter {

    @Override
    public UploadExtensionExtensionEnum extension() {
        return UploadExtensionExtensionEnum.json;
    }

    @Override
    public <T> List<T> convert(byte[] data, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = JsonUtils.getObjectMapper();
            return objectMapper.readValue(data, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("byte转换为list错误", e);
        }
        return null;
    }
}