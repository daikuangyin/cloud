package com.sun.cloud.framework.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String SYS_UNKNOW_ERROR = "系统未知异常！";
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = UnauthenticatedException.class)
    public String handleException(UnauthenticatedException e) {
        logger.error("find exception:e={}", e);
        return "403";
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AuthorizationException.class)
    public String handleException2(AuthorizationException e) {
        logger.error("find exception:e={}", e);
        logger.debug(e.getMessage());
        return "403";
    }


}
