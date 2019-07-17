package com.ali.wechatdemo.service;

import com.ali.wechatdemo.dto.RecordDto;

public interface RecordService {

    RecordDto selectRecordList(String oppenid);
}
