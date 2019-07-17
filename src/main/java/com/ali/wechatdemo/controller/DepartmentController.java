package com.ali.wechatdemo.controller;


import com.ali.wechatdemo.dao.DepartmentMapper;
import com.ali.wechatdemo.dto.DepartmentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@EnableAutoConfiguration
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/getDepartments")
    public List<DepartmentList> getDepartments(@RequestParam Integer departmentId){
        List<DepartmentList> deptmentNodes= departmentMapper.getParentTree(departmentId);
        return deptmentNodes;
    }
}
