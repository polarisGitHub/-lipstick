package com.polaris.he.lipstick.dao.objects;

import com.polaris.he.framework.dao.object.PullDO;
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
@ToString(callSuper = true)
public class LipstickSearchDO extends PullDO {

    private Collection<String> brandCodes;

    private Collection<String> categoryCodes;

    private String colorNo;
}