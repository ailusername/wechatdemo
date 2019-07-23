package com.ali.wechatdemo.service.impl;

import com.ali.wechatdemo.FastDFS.FastDFSClient;
import com.ali.wechatdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FastDFSClient fastDFSClient;

    @Override
    public String uploadFile(MultipartFile filedata) throws IOException {

            if (filedata != null && !filedata.isEmpty()) {

                    String path = fastDFSClient.uploadFile(filedata.getBytes(), filedata.getOriginalFilename());
                    System.out.println("文件的路径是"  + path);
                    return path;
            } else
            {
                return "有相似文件请注意格式";
            }
    }

}
