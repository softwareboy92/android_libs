package com.lv.libhttp.model;

/**
 * 作者：created by albert on 2019-07-18 13:33
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class BaseResponse<T> {

    public int status;
    public String messsage;
    public T data;

    public boolean isSuccess() {
        return status == 1;
    }
}
