package com.polaris.he.lipstick.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-05 21:33
 * Description:
 */
@Setter
@Getter
@ToString(callSuper = true)
public class LipstickListItem extends LipstickItem{

    private String figure;
}