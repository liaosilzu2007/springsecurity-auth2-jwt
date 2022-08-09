package com.lzumetal.springsecurity.handler;

import com.lzumetal.springsecurity.common.ResponseData;
import com.lzumetal.springsecurity.common.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author liaosi
 * @date 2022-08-07
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseData handleServiceException(ServiceException exception) {
        return new ResponseData(exception.getCode(), exception.getMessage());
    }


}
