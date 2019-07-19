package com.ali.wechatdemo.controller;
import com.ali.wechatdemo.constant.ErrConstant;
import com.ali.wechatdemo.dao.CompanyMapper;
import com.ali.wechatdemo.dao.DepartmentMapper;
import com.ali.wechatdemo.dao.RecordMapper;
import com.ali.wechatdemo.dto.DepartmentList;
import com.ali.wechatdemo.po.Company;
import com.ali.wechatdemo.po.Department;
import com.ali.wechatdemo.po.Record;
import com.ali.wechatdemo.po.Student;
import com.ali.wechatdemo.service.StudentService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/upload")
public class UpLoadController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private RecordMapper recordMapper;

    @PostMapping("/audioupload")
    public String upload(MultipartFile uploadFile, HttpServletRequest request, String openid,Integer company_id,Byte status) throws IOException {

        Student students = studentService.selectByOpenid(openid);
        String studentId = students.getStudentId();
        String studentName = students.getStudentName();
        Integer departmentId = students.getDepartmentId();
        List<DepartmentList> parentTree = departmentMapper.getParentTree(departmentId);
        Company companyName = companyMapper.selectByPrimaryKey(company_id);

        if (openid != null){
            String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
            File dir = new File("G://upload");
            if (!dir.isDirectory()) {
                //文件目录不存在，就创建一个
                dir.mkdirs();
            }
            //        try {
            String filename = uploadFile.getOriginalFilename();
            //获取到后缀名
            String suffixName = filename.substring(filename.lastIndexOf("."));
            //班级+姓名+公司
            //String finalName =parentTree + studentName + companyName +suffixName;
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
            //2，实现上传
            uploadFile.transferTo(fileServer);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort()
                    + "/audioupload/" + filename;
            //添加到record表中
            Record record = new Record();
            record.setRecordName(filename);
            record.setCompanyId(company_id);
            record.setStudentId(studentId);
            record.setRecordUrl(filePath);
            record.setStatus((byte)status);
            recordMapper.insert(record);
            //3，返回可供访问的网络路径
            return filePath;
        }
        //        } catch (IOException e) {
        //            throw new Exception("异常");
        //        }
         return "upload fail......";

    }
}
