package com.polaris.he.framework.entity.page;

import com.polaris.he.framework.annotation.Encryption;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PullResult<T> {

    private List<T> data;

    @Encryption
    private Long nextId;
}