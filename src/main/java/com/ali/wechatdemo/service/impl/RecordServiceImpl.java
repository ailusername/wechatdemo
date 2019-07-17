package com.ali.wechatdemo.service.impl;

import com.ali.wechatdemo.dao.DepartmentMapper;
import com.ali.wechatdemo.dao.RecordMapper;
import com.ali.wechatdemo.dao.StudentMapper;
import com.ali.wechatdemo.dto.RecordDto;
import com.ali.wechatdemo.po.Department;
import com.ali.wechatdemo.po.Record;
import com.ali.wechatdemo.po.Student;
import com.ali.wechatdemo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public RecordDto selectRecordList(String oppenid) {

        RecordDto recordDto = new RecordDto();
        Student student = studentMapper.selectOpenid(oppenid);
        if (student != null){
            Department department = departmentMapper.selectByPrimaryKey(student.getDepartmentId());
            List<String> departmentIdList  = studentMapper.selectDepartmentIdList(student.getDepartmentId());
            String stuids = departmentIdList.toString();
            String substring = stuids.substring(1, stuids.length() - 1);
            //todo
            List<Record>  r  =  recordMapper.selectByStudent(stuids);
        }


        return null;
    }
}
