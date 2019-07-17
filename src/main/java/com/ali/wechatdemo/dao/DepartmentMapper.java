package com.ali.wechatdemo.dao;

import com.ali.wechatdemo.dto.DepartmentList;
import com.ali.wechatdemo.po.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer departmentId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<DepartmentList> getParentTree(@Param("departmentId") Integer departmentId);

    Integer selectByDepartmentName(@Param("departmentName") String departmentName);

}