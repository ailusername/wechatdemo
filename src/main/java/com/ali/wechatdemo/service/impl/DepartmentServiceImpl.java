package com.ali.wechatdemo.service.impl;

import com.ali.wechatdemo.dao.DepartmentMapper;
import com.ali.wechatdemo.po.Department;
import com.ali.wechatdemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl  implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Integer selectByDepartmentName(String departmentName) {
        Integer departmentid = departmentMapper.selectByDepartmentName(departmentName);
        return departmentid;
    }

    @Override
    public Department selectByPrimaryKey(Integer departmentId) {
        Department department = departmentMapper.selectByPrimaryKey(departmentId);
        return department;
    }
}
