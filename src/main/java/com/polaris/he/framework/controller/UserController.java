package com.polaris.he.framework.controller;

import com.polaris.he.application.utils.EncryptionUtils;
import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.entity.user.JavaWebToken;
import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.service.user.UserInfoService;
import com.polaris.he.framework.utils.SpringContextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * User: hexie
 * Date: 2019-01-27 21:17
 * Description:
 */
@RestController
@RequestMapping("/jwt/user")
public class UserController {

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("/info")
    public JavaWebToken getUserInfo(HttpServletRequest request) {
        UserInfoService service = applicationContext.getBean(applicationContext.getEnvironment().getProperty("user.info.service"), UserInfoService.class);
        UserInfo user = service.getUserInfo(request);

        JavaWebToken jwt = new JavaWebToken();
        BeanUtils.copyProperties(user, jwt);
        jwt.setExpireTime(LocalDateTime.now().plusHours(2));
        jwt.setToken(EncryptionUtils.AESEncode(JsonUtils.toJsonString(jwt), SpringContextUtils.getProperty("encryption.aes.password.jwt")));

        jwt.setSource(null);
        jwt.setOpenId(null);
        jwt.setExpireTime(null);
        return jwt;
    }
}