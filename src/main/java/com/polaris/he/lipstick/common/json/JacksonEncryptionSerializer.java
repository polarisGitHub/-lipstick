package com.polaris.he.lipstick.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.polaris.he.lipstick.annotation.Encryption;
import com.polaris.he.lipstick.common.utils.EncryptionUtils;

import java.io.IOException;

/**
 * User: hexie
 * Date: 2019-01-28 23:09
 * Description:
 */
public class JacksonEncryptionSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private boolean encryption;

    public JacksonEncryptionSerializer() {
    }

    public JacksonEncryptionSerializer(boolean encryption) {
        this.encryption = encryption;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (encryption) {
            gen.writeString(EncryptionUtils.AESEncode(String.valueOf(value), "12345"));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return new JacksonEncryptionSerializer(property.getAnnotation(Encryption.class) != null);
    }
}