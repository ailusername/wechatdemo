package com.ali.wechatdemo.controller;

import com.ali.wechatdemo.dao.RecordMapper;
import com.ali.wechatdemo.dto.UpLoadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/record")
@EnableAutoConfiguration
public class RecordController {
    @Autowired
    private RecordMapper recordMapper;
    @PostMapping("/load")
    public void load(@RequestParam String path,
                                @RequestParam String companyName,
                                @RequestParam String studentName
    ){
         recordMapper.getInsertByRecord(path,companyName,studentName);
    }

}
