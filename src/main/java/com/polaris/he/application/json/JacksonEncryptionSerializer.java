package com.polaris.he.application.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.polaris.he.application.utils.EncryptionUtils;
import com.polaris.he.framework.utils.SpringContextUtils;

import java.io.IOException;

/**
 * User: hexie
 * Date: 2019-01-28 23:09
 * Description:
 */
public class JacksonEncryptionSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            gen.writeNull();
        }
        gen.writeString(EncryptionUtils.AESEncode(String.valueOf(value), SpringContextUtils.getProperty("encryption.aes.password")));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return this;
    }
}