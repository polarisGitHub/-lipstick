package com.polaris.he.lipstick.common.data;

import com.polaris.he.lipstick.common.constant.ExceptionCodeEnum;
import com.polaris.he.lipstick.common.constant.ResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2018-12-16 21:03
 * Description:
 */
@Getter
@Setter
@ToString
public class RestResponse {

    private Object data;

    private String message;

    private ResponseCodeEnum code;

    private ExceptionCodeEnum errorCode;
}