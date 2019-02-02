package com.polaris.he.framework.annotation;

import com.polaris.he.framework.entity.constanst.CosmeticsEnum;

import java.lang.annotation.*;

/**
 * User: hexie
 * Date: 2019-02-02 22:43
 * Description:
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FavoritesConverter {


    /**
     * @return
     */
    CosmeticsEnum type();
}