package com.polaris.he.application.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.polaris.he.application.utils.EncryptionUtils;
import com.polaris.he.framework.utils.SpringContextUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: hexie
 * Date: 2019-01-29 19:51
 * Description:
 */
public class JacksonEncryptionDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

    private Class clazz;

    private Map<Class, JsonDeserializer<?>> map = new ConcurrentHashMap<>();

    public JacksonEncryptionDeserializer() {
    }

    public JacksonEncryptionDeserializer(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = p.getText();
        if (text == null) {
            return null;
        }
        String decode = EncryptionUtils.AESDecode(text, SpringContextUtils.getProperty("encryption.aes.password.data"));
        if (Long.class.equals(clazz)) {
            return Long.valueOf(decode);
        } else if (String.class.equals(clazz)) {
            return decode;
        } else if (Integer.class.equals(clazz)) {
            return Integer.valueOf(decode);
        } else if (Double.class.equals(clazz)) {
            return Double.valueOf(decode);
        } else if (Float.class.equals(clazz)) {
            return Float.valueOf(decode);
        } else if (BigDecimal.class.equals(clazz)) {
            return BigDecimal.valueOf(Double.valueOf(decode));
        }
        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        return getDeserializer(property.getType().getRawClass());
    }

    private JsonDeserializer<?> getDeserializer(Class clazz) {
        JsonDeserializer<?> deserializer = map.get(clazz);
        if (deserializer == null) {
            deserializer = new JacksonEncryptionDeserializer(clazz);
            map.put(clazz, deserializer);
        }
        return deserializer;
    }
}