package com.ali.wechatdemo.dao;

import com.ali.wechatdemo.dto.UpLoadDTO;
import com.ali.wechatdemo.po.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> selectByStudent(@Param("stuids") String stuids);

    void getInsertByRecord(@Param("path") String path, @Param("companyName")String companyName, @Param("studentName")String studentName);



}