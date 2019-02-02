package com.polaris.he.framework.entity.page;

import com.polaris.he.framework.annotation.Encryption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-02-02 21:41
 * Description:
 */
@Getter
@Setter
@ToString
public class Pull {

    @Encryption
    private Long startId;

    private Integer size;
}