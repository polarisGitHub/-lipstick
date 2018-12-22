package com.polaris.he.lipstick.dao.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-08 15:03
 * Description:
 */
@Setter
@Getter
@ToString(callSuper = true)
public class LipStickDO extends BaseDO {

    /**
     * 品牌
     */
    private String brand;

    /**
     * 品牌内唯一标示
     */
    private String item;

    /**
     * 分类
     */
    private String catalog;

    /**
     * 产品线
     */
    private String product;

    /**
     * 口红名称
     */
    private String name;


    /**
     * 官网链接
     */
    private String productUrl;

    /**
     * 色号
     */
    private String colorNo;

    /**
     * 色卡链接
     */
    private String colorCardUrl;

    /**
     * 颜色编号
     */
    private String color;


}