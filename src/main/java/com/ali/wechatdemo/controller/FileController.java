package com.ali.wechatdemo.controller;



import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ali.wechatdemo.FastDFS.FastDFSClient;
import com.ali.wechatdemo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件的上传下载的最终版
 */
@RestController
@RequestMapping("/file")
public class FileController
{

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * 上传文件
     * @param filedata
     * @return
     */

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public Map<String, Object> uploadFile(@RequestParam MultipartFile filedata)
    {

        Map<String, Object> m = new HashMap<String, Object>();

        if (filedata != null && !filedata.isEmpty())
        {
            try
            {
                String path = fastDFSClient.uploadFile(filedata.getBytes(), filedata.getOriginalFilename());
                m.put("code", 200);
                m.put("url", path);
                m.put("msg", "上传成功");
                System.out.println(path);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                m.put("code", 400);
                m.put("msg", "上传失败");
            }
        }
        else
        {
            m.put("code", 501);
            m.put("msg", "参数丢失");
        }
        return m;

    }

    /**
     * 下载文件
     * @param response  响应
     * @param path      下载路径
     * @param sname
     * @param cname
     * @param recordingTime
     * @param dname
     * @param isAdopt
     */
    @RequestMapping(value = "getFileByPath", method = RequestMethod.GET)
    public void getFileByPath(HttpServletResponse response, @RequestParam("path") String path,
                              @RequestParam("sname") String sname,
                              @RequestParam("cname") String cname,
                              @RequestParam("recordingTime") String recordingTime,
                              @RequestParam("dname") String dname,
                              @RequestParam("isAdopt") Integer isAdopt) {
        String adopt;
        if(isAdopt==1){
            adopt="成功";
        }else if(isAdopt==0){
            adopt="失败";
        }else{
            adopt="暂定";
        }
        String filename= FileUtil.getOriginalFilename(path);
        String subFilename=filename.substring(filename.indexOf("."),filename.length());
        String filenames =dname+sname+cname+recordingTime+adopt+subFilename;

        try
        {
            // 判断文件是否存在
            if (fastDFSClient.getFileInfo(path) != null)
            {
                byte[] buffer = fastDFSClient.downloadFile(path);
                // 清空response
                filenames=URLEncoder.encode(filenames, "UTF-8");
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + filenames);
                response.addHeader("Content-Length", "" + buffer.length);
                // 通过文件流的形式写到客户端
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                // 写完以后关闭文件流
                toClient.flush();
                toClient.close();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

