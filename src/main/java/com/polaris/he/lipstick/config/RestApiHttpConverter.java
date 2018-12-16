package com.polaris.he.lipstick.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polaris.he.lipstick.common.constant.ResponseCodeEnum;
import com.polaris.he.lipstick.common.data.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * User: hexie
 * Date: 2018-12-16 21:34
 * Description:
 */
@Slf4j
public class RestApiHttpConverter extends AbstractJackson2HttpMessageConverter {

    protected RestApiHttpConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    protected RestApiHttpConverter(ObjectMapper objectMapper, MediaType supportedMediaType) {
        super(objectMapper, supportedMediaType);
    }

    protected RestApiHttpConverter(ObjectMapper objectMapper, MediaType... supportedMediaTypes) {
        super(objectMapper, supportedMediaTypes);
    }

    @Override
    protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage) throws IOException {
        if (object instanceof RestResponse) {
            log.info("rest api返回消息，restResponse={}", object);
            super.writeInternal(object, type, outputMessage);
        } else {
            RestResponse restResponse = new RestResponse();
            restResponse.setCode(ResponseCodeEnum.success);
            restResponse.setData(object);
            log.info("rest api返回消息，restResponse={}", restResponse);
            super.writeInternal(restResponse, type, outputMessage);
        }
    }
}