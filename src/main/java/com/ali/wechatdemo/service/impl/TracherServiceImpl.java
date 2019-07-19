package com.ali.wechatdemo.service.impl;

import com.ali.wechatdemo.dao.TeacherMapper;
import com.ali.wechatdemo.po.Teacher;
import com.ali.wechatdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration
public class TracherServiceImpl implements TeacherService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     *
     * @param code
     * @param phone
     * @param openId
     * @param nickname
     * @param sex
     * @return
     * approve  批准
     */
    // todo
    @Override
    public boolean approve(String code, String phone, String openId, String nickname, String sex) {
        String yzcode = (String)redisTemplate.opsForValue().get("code" + phone);
        if (yzcode != null && yzcode.equals(code)){

        }
        return false;
    }

    @Override
    public List<Teacher> selectAll() {
        return teacherMapper.selectAll();
    }

    @Override
    public int insert(Teacher record) {
        return teacherMapper.insert(record);
    }
}
