package com.polaris.he.framework.service.user.impl;

import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.service.user.UserInfoService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;

/**
 * User: hexie
 * Date: 2019-01-27 20:11
 * Description:
 */
@Service("mockUserInfoServiceImpl")
public class MockUserInfoServiceImpl implements UserInfoService {

    @Override
    public UserInfo getUserInfo(ServletRequest request) {
        return new UserInfo("weixin", "test-user");
    }
}