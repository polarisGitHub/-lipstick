package com.polaris.he.lipstick.config.mvc.advice;

import com.polaris.he.lipstick.common.constant.ResponseCodeEnum;
import com.polaris.he.lipstick.common.data.RestResponse;
import com.polaris.he.lipstick.common.exception.BizExcepton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * User: hexie
 * Date: 2018-12-16 21:12
 * Description:
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class, basePackages = "com.polaris.he.lipstick.controller")
public class ExceptionHandlerAdvice {

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BizExcepton.class)
    @ResponseStatus(code = HttpStatus.OK)
    public RestResponse bizExceptionHandler(BizExcepton e) {
        log.error("[业务异常{}],message={}", e.getMessage(), e.getExceptionCode(), e);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setErrorCode(e.getExceptionCode());
        response.setMessage(e.getMessage());
        return response;
    }

    /**
     * 通用异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse defaultExceptionHandler(Exception e) {
        log.error("[系统异常],message={}", e.getMessage(), e);
        RestResponse response = new RestResponse();
        response.setCode(ResponseCodeEnum.error);
        response.setMessage(e.getMessage());
        return response;
    }


}