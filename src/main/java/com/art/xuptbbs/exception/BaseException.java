package com.art.xuptbbs.exception;

public abstract class BaseException extends RuntimeException{
    protected int code;

    public BaseException(int code, String message){
        super(message);
        this.code = code;
    }
}
