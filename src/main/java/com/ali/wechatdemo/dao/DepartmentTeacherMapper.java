package com.ali.wechatdemo.dao;

import com.ali.wechatdemo.po.DepartmentTeacherKey;

public interface DepartmentTeacherMapper {
    int deleteByPrimaryKey(DepartmentTeacherKey key);
    int insert(DepartmentTeacherKey record);
    int insertSelective(DepartmentTeacherKey record);
}