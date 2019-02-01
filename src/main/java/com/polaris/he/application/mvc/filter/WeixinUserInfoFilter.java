package com.polaris.he.application.mvc.filter;

import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.service.user.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: hexie
 * Date: 2019-01-11 22:00
 * Description:
 */
@Slf4j
public class WeixinUserInfoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            String userInfoService = context.getEnvironment().getProperty("user.info.service");
            UserInfoService userInfoServiceBean = context.getBean(userInfoService, UserInfoService.class);
            UserInfo userInfo = userInfoServiceBean.getUserInfo(request);
            request.setAttribute("userInfo", userInfo);
        } catch (Exception e) {
            log.error("获取微信用户信息出错", e);
        } finally {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}