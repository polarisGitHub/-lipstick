package com.polaris.he.lipstick.common.constant;

/**
 * User: hexie
 * Date: 2018-12-16 21:24
 * Description:
 */
public enum ExceptionCodeEnum {
    test("test");

    private String message;

    ExceptionCodeEnum(String message) {
        this.message = message;
    }
}