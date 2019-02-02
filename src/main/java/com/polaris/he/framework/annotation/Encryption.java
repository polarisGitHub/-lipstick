package com.polaris.he.framework.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.polaris.he.application.entity.constant.EncryptionEnum;
import com.polaris.he.application.json.JacksonEncryptionDeserializer;
import com.polaris.he.application.json.JacksonEncryptionSerializer;

import java.lang.annotation.*;

/**
 * User: hexie
 * Date: 2019-01-28 22:59
 * Description:
 */
@Documented
@JacksonAnnotationsInside
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = JacksonEncryptionSerializer.class)
@JsonDeserialize(using = JacksonEncryptionDeserializer.class)
public @interface Encryption {

    /**
     * @return
     */
    EncryptionEnum method() default EncryptionEnum.AES;
}