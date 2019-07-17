package com.ali.wechatdemo.controller;

import com.ali.wechatdemo.enumeration.Gender;
import com.ali.wechatdemo.po.Student;
import com.ali.wechatdemo.service.DepartmentService;
import com.ali.wechatdemo.service.StudentService;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/importXls")
    public void importXls(@RequestBody MultipartFile file) throws Exception {
        
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("sheet1");
        int rows = sheet.getLastRowNum();// 一共有多少行
        
        if(rows==0){
            throw new Exception("请填写数据");
        }
        
        for (Row r : sheet) {
            if(r.getRowNum()<1){
                //表中如果没有数据
                continue;
            }

            for (Cell cell : r) {
                cell.setCellType(CellType.STRING);

            }

            Student student = new Student();
            if(r.getCell(0)!=null){
                String studentid = r.getCell(0).getStringCellValue();
                if(studentid!=null){
                    student.setStudentId(studentid);
                }
            }
            
            if(r.getCell(1)!=null){
                String name = r.getCell(1).getStringCellValue();
                if(name!=null){
                    student.setStudentName(r.getCell(1).getStringCellValue());
                }
            }
            if(r.getCell(2)!=null){
                String department = r.getCell(2).getStringCellValue();
                String classes = department.substring(department.length() - 5, department.length());
                Integer departmentid = departmentService.selectByDepartmentName(classes);
                if(departmentid!=null){
                    student.setDepartmentId(departmentid);
                }
            }
            if(r.getCell(3)!=null){
                String phone = r.getCell(3).getStringCellValue();
                if(phone.length()==11){
                 student.setPhone(phone);
                }
            }
            if(r.getCell(4)!=null){
                String gender = r.getCell(4).getStringCellValue();

                if("男".equals(gender)){
                    student.setGender((byte) 1);
                }else if("女".equals(gender)){
                    student.setGender((byte) 2);
                }else{
                    student.setGender((byte) 3);
                }
            }


            studentService.add(student);

        }

        inputStream.close();
    }


}
