package com.ali.wechatdemo.service;

import com.ali.wechatdemo.po.Department;

public interface DepartmentService {
    Integer selectByDepartmentName(String departmentName);

    Department selectByPrimaryKey(Integer departmentId);
}
