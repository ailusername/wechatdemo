package com.ali.wechatdemo.dao;

import com.ali.wechatdemo.po.Teacher;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(String teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectAll();
}