package com.lzumetal.springsecurity.core;


/**
 * @author liaosi
 * @date 2020-08-09
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }


    public ServiceException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }


    public ServiceException(ResponseCode responseCode, Throwable cause) {
        super(responseCode.getMessage(), cause);
        this.code = responseCode.getCode();
    }

    public int getCode() {
        return code;
    }


    public static void main(String[] args) {

    }


}
