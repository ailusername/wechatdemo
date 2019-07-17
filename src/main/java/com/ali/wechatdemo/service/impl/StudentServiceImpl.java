package com.ali.wechatdemo.service.impl;

import com.ali.wechatdemo.dao.StudentMapper;
import com.ali.wechatdemo.po.Student;
import com.ali.wechatdemo.service.StudentService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Page<Student> search(String name, Integer departmentId) {
        return studentMapper.search(name,departmentId);
    }

    @Override
    public int add(Student student) {
        int i = studentMapper.insert(student);
        return i;
    }

    @Override
    public void update(Student student) {
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void delete(String studentId) {
        studentMapper.deleteByPrimaryKey(studentId);
    }


    //todo
    @Override
    public void batchdelete(List<Integer> studentIds) {
        studentMapper.batchdelete(studentIds);
    }
}
