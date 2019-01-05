package com.polaris.he.lipstick.dao.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-05 22:26
 * Description:
 */
@Getter
@Setter
@ToString
public class LipstickSearchDO {

    private Collection<String> brandCodes;

    private Collection<String> categoryCodes;

    private String colorNo;
}