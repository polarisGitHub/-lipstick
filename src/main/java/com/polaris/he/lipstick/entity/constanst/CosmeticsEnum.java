package com.polaris.he.lipstick.entity.constanst;

/**
 * User: hexie
 * Date: 2019-01-10 21:25
 * Description:
 */
public enum CosmeticsEnum {

    LIPSTICK("lipstick", "口红");

    private String code;

    private String name;

    CosmeticsEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }}