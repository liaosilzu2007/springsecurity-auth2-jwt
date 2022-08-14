package com.lzumetal.springsecurity.jwt.handler;

import com.lzumetal.springsecurity.jwt.common.ResponseData;
import com.lzumetal.springsecurity.jwt.common.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


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
