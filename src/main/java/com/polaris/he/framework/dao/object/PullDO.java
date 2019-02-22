package com.polaris.he.framework.dao.object;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-22 23:20
 * Description:
 */
@Getter
@Setter
@ToString
public class PullDO {

    private Long nextId;

    @NonNull
    private Integer pageSize;
}