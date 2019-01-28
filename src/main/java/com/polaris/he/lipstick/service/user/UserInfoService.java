package com.polaris.he.lipstick.service.user;

import com.polaris.he.lipstick.entity.user.UserInfo;

import javax.servlet.ServletRequest;

/**
 * User: hexie
 * Date: 2019-01-27 20:06
 * Description:
 */
public interface UserInfoService {

    UserInfo getUserInfo(ServletRequest request);
}