package com.polaris.he.framework.dao.object;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-05 22:26
 * Description:
 */
@Getter
@Builder
@ToString
public class LipstickSearchDO {

    private Collection<String> brandCodes;

    private Collection<String> categoryCodes;

    private String colorNo;
}