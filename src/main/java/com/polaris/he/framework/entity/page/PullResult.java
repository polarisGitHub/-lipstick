package com.polaris.he.framework.entity.page;

import com.polaris.he.framework.annotation.Encryption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-02-02 21:43
 * Description:
 */
@Getter
@Setter
@ToString
public class PullResult<T> {

    private List<T> list;

    @Encryption
    private Long lastId;
}