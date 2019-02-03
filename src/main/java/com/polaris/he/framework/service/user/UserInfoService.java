package com.polaris.he.framework.service.user;

import com.polaris.he.framework.entity.user.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * User: hexie
 * Date: 2019-01-27 20:06
 * Description:
 */
public interface UserInfoService {

    UserInfo getUserInfo(HttpServletRequest request);
}