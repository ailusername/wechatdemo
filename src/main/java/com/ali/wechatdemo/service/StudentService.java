package com.ali.wechatdemo.service;

import com.ali.wechatdemo.po.Student;
import com.github.pagehelper.Page;

import java.util.List;

public interface StudentService {


    Page<Student> search(String name, Integer departmentId);

    int add(Student student);

    void update(Student student);

    void delete(String studentId);

    //todo
    void batchdelete(List<Integer> studentIds);


}
