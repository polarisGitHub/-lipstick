package com.polaris.he.lipstick.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.polaris.he.lipstick.common.constant.EncryptionEnum;
import com.polaris.he.lipstick.common.json.JacksonEncryptionDeserializer;
import com.polaris.he.lipstick.common.json.JacksonEncryptionSerializer;

import java.lang.annotation.*;

/**
 * User: hexie
 * Date: 2019-01-28 22:59
 * Description:
 */
@Documented
@JacksonAnnotationsInside
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = JacksonEncryptionSerializer.class)
@JsonDeserialize(using = JacksonEncryptionDeserializer.class)
public @interface Encryption {

    /**
     * @return
     */
    EncryptionEnum method() default EncryptionEnum.AES;
}