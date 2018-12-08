package com.polaris.he.lipstick.common.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-08 15:23
 * Description:
 */
public enum LipStickBrandEnum {

    YSL("ysl", "圣罗兰", "ysl|杨树林");

    private String brand;

    private String name;

    private String nickName;


    LipStickBrandEnum(String brand, String name, String nickName) {
        this.brand = brand;
        this.name = name;
        this.nickName = nickName;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public List<String> getNickName() {
        return Arrays.asList(nickName.split("|"));
    }

    public static LipStickBrandEnum getByBrand(String brand) {
        for (LipStickBrandEnum value : LipStickBrandEnum.values()) {
            if (value.getBrand().equals(brand)) {
                return value;
            }
        }
        return null;
    }

    public static LipStickBrandEnum searchByNickName(String nickName) {
        for (LipStickBrandEnum value : LipStickBrandEnum.values()) {
            if (value.getNickName().stream().anyMatch(l -> StringUtils.equals(l, nickName))) {
                return value;
            }
        }
        return null;
    }
}