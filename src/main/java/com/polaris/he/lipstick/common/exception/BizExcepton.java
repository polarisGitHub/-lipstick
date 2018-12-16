package com.polaris.he.lipstick.common.exception;

import com.polaris.he.lipstick.common.constant.ExceptionCodeEnum;
import lombok.Getter;

/**
 * User: hexie
 * Date: 2018-12-16 21:23
 * Description:
 */
@Getter
public class BizExcepton extends RuntimeException {

    private ExceptionCodeEnum exceptionCode;

    public BizExcepton(String message, ExceptionCodeEnum exceptionCode, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }
}