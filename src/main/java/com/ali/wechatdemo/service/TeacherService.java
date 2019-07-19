package com.ali.wechatdemo.service;

import com.ali.wechatdemo.po.Teacher;

import java.util.List;

public interface TeacherService {
    boolean approve(String code, String phone, String openId,String nickname,String sex);

    List<Teacher> selectAll();

    int insert(Teacher record);
}
