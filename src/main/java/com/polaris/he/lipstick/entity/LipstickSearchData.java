package com.polaris.he.lipstick.entity;

import com.polaris.he.framework.annotation.Encryption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-22 22:32
 * Description:
 */
@Getter
@Setter
@ToString
public class LipstickSearchData {

    private String brands;

    private String categories;

    private String colorNo;

    @Encryption
    private String nextId;// TODO

    private Integer pageSize;
}