package com.ali.wechatdemo.controller;

import com.ali.wechatdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private StudentService studentService;


}
