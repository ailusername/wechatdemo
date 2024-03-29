package com.ali.wechatdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientException extends Exception{

    private String openid;

    private String errCode;

    public ClientException(String openid, String errCode, String errMsg){
        super(errMsg);
        this.openid = openid;
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getOpenid() {
        return openid;
    }

}
