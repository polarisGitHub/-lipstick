package com.polaris.he.application.mvc.filter;

import com.polaris.he.application.entity.RestResponse;
import com.polaris.he.application.entity.constant.ExceptionCodeEnum;
import com.polaris.he.application.entity.constant.ResponseCodeEnum;
import com.polaris.he.application.utils.EncryptionUtils;
import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.entity.user.JavaWebToken;
import com.polaris.he.framework.entity.user.UserInfo;
import com.polaris.he.framework.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * User: hexie
 * Date: 2019-01-11 22:00
 * Description:
 */
@Slf4j
public class JwtFilter implements Filter {

    private static final String ERROR_JWT_STRING;

    private static final String ERROR_USER_INFO_STRING;

    private static final String LOGIN_TIMEOUT_STRING;

    static {
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setErrorCode(ExceptionCodeEnum.E00004);
        response.setMessage("无权限");
        ERROR_JWT_STRING = JsonUtils.toJsonString(response);
    }

    static {
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setErrorCode(ExceptionCodeEnum.E00005);
        response.setMessage("服务异常");
        ERROR_USER_INFO_STRING = JsonUtils.toJsonString(response);
    }

    static {
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setErrorCode(ExceptionCodeEnum.E00006);
        response.setMessage("登录超时");
        LOGIN_TIMEOUT_STRING = JsonUtils.toJsonString(response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            UserInfo userInfo = new UserInfo();
            String jwt = ((HttpServletRequest) request).getHeader("jwt");
            if (StringUtils.isNotEmpty(jwt)) {
                JavaWebToken javaWebToken = JsonUtils.toJavaObject(EncryptionUtils.AESDecode(jwt, SpringContextUtils.getProperty("encryption.aes.password.jwt")), JavaWebToken.class);
                if (javaWebToken == null) {// 不能解码
                    writeResponse(response, ERROR_JWT_STRING);
                    return;
                }
                if (LocalDateTime.now().isBefore(javaWebToken.getExpireTime())) {// token未过期
                    BeanUtils.copyProperties(javaWebToken, userInfo);
                } else {// token过期
                    writeResponse(response, LOGIN_TIMEOUT_STRING);
                }
            } else {// 没有jwt
                writeResponse(response, ERROR_JWT_STRING);
                return;
            }
            request.setAttribute("userInfo", userInfo);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("jwt错误", e);
            writeResponse(response, ERROR_USER_INFO_STRING);
        }
    }

    private void writeResponse(ServletResponse response, String content) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.getWriter().write(content);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    public void destroy() {

    }
}