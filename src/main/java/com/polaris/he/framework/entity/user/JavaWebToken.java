package com.polaris.he.framework.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * User: hexie
 * Date: 2019-02-03 23:52
 * Description:
 */
@Getter
@Setter
@ToString(callSuper = true)
public class JavaWebToken extends UserInfo {

    private LocalDateTime expireTime;

    private String token;
}