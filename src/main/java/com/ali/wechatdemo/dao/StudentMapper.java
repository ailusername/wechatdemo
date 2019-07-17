package com.ali.wechatdemo.dao;

import com.ali.wechatdemo.po.Student;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Page<Student> search(@Param("name") String name, @Param("departmentId") Integer departmentId);
    //todo
    void batchdelete(List<Integer> studentIds);

    Student selectOppenid(@Param("oppenid") String oppenid);

    List<String> selectDepartmentIdList(@Param("departmentId") Integer departmentId);

    List<Student> selectAll();

}