package com.art.xuptbbs.exception;

public class BusinessException extends BaseException{
    public BusinessException(String message) {
        super(-2, message);
    }
}
