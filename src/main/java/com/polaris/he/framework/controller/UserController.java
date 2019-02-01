package com.polaris.he.framework.controller;

import com.polaris.he.framework.entity.user.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: hexie
 * Date: 2019-01-27 21:17
 * Description:
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/info")
    public UserInfo getUserInfo(UserInfo userInfo) {
        return userInfo;
    }
}