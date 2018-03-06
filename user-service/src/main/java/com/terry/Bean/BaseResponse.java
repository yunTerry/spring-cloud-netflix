package com.terry.Bean;

/***
 * *
 * 名称：     BaseResponse.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/9/8.
 * 说明：     
 * *
 ***/

public class BaseResponse<T> {

    public int code;
    public String msg;
    public T data;

}
