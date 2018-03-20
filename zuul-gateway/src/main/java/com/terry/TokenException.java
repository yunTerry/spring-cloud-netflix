package com.terry;

/***
 * *
 * 名称：     JWTVerificationException.
 * 作者：     Terry Tan
 * 创建时间：  on 2018/3/9.
 * 说明：     
 * *
 ***/

public class TokenException extends RuntimeException {

    public TokenException(String message) {
        this(message, null);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}

