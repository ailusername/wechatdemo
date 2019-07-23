package com.ali.wechatdemo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String uploadFile(MultipartFile filedata) throws IOException;
}
