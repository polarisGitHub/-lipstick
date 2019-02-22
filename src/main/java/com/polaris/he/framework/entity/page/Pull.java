package com.polaris.he.framework.entity.page;

import com.polaris.he.framework.annotation.Encryption;
import lombok.*;

/**
 * User: hexie
 * Date: 2019-02-02 21:41
 * Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pull {

    @Encryption
    private Long nextId;

    private Integer pageSize;
}