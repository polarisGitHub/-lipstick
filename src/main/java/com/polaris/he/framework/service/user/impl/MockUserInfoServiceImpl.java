package com.polaris.he.framework.service.user.impl;

import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.service.user.UserInfoService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * User: hexie
 * Date: 2019-01-27 20:11
 * Description:
 */
@Service("mockUserInfoServiceImpl")
public class MockUserInfoServiceImpl implements UserInfoService {

    @Override
    public UserInfo getUserInfo(HttpServletRequest request) {
        UserInfo user = new UserInfo();
        user.setSource("weixin");
        user.setOpenId("test-user");
        user.setName("test");
        user.setAvatar("http://life.southmoney.com/tuwen/UploadFiles_6871/201803/20180329163419413.jpg");
        return user;
    }
}