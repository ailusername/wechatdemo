package com.ali.wechatdemo.service;

public interface TracherService {
    boolean approve(String code, String phone, String openId,String nickname,String sex);
}
