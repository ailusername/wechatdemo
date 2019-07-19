package com.ali.wechatdemo.controller;


import com.ali.wechatdemo.enumeration.Gender;
import com.ali.wechatdemo.po.Department;
import com.ali.wechatdemo.po.Student;
import com.ali.wechatdemo.po.Teacher;
import com.ali.wechatdemo.service.DepartmentService;
import com.ali.wechatdemo.service.StudentService;
import com.ali.wechatdemo.service.TeacherService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * @param file
     * @throws Exception
     * 导入学生Excel
     */
    @PostMapping("/student/importXls")
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

    /**
     * @param response
     * @return
     * @throws IllegalAccessException
     * @throws IOException
     * 导出学生Excel
     */
    @GetMapping(value = "/student/exportXls", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] exportXls(HttpServletResponse response) throws IllegalAccessException, IOException {
        List<Student> students = studentService.selectAll();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("students");

        Field[] declaredFields;
        Row row;

        row = sheet.createRow(0);
        declaredFields= Student.class.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            String name = field.getName();
            Cell cell = row.createCell(i);
            cell.setCellValue(name);
        }

        for (int i = 0; i <students.size() ; i++) {
            Student student = students.get(i);
            row = sheet.createRow(i + 1);
            declaredFields=student.getClass().getDeclaredFields();

            for (int j = 0; j < declaredFields.length; j++){
                Field field = declaredFields[j];
                Cell cell = row.createCell(j);

                field.setAccessible(true);
                Object value = field.get(student);
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                }
                if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                }
                if (value instanceof Short) {
                    cell.setCellValue((Short) value);
                }
                if (value instanceof Byte) {
                    cell.setCellValue((Byte) value);
                }
                if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                }
                if (value instanceof Float) {
                    cell.setCellValue((Float) value);
                }
                if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                }
                if (value instanceof Date) {
                    Date date = (Date) value;
                    cell.setCellValue(date.toString());
                }

                String name = field.getName();
                if(name.equals("gender")){
                    byte numericCellValue = (byte) cell.getNumericCellValue();
                    cell.setCellValue(String.valueOf(Gender.values()[numericCellValue]));
                }

                if(name.equals("departmentId")){
                    int numericCellValue = (int) cell.getNumericCellValue();
                    Department department = departmentService.selectByPrimaryKey(numericCellValue);
                   cell.setCellValue(department.getDepartmentName());
                }
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();
        byte[] data = baos.toByteArray();
        baos.close();

        String filename = UUID.randomUUID().toString() + ".xls";
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        return data;
    }

    @PostMapping("/teacher/importXls")
    public void importTeacherXls(@RequestBody MultipartFile file) throws Exception {
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
            Teacher teacher = new Teacher();

            if( r.getCell(0)!=null){
                String teacherid = r.getCell(0).getStringCellValue();
                if(teacherid!=null){
                    teacher.setTeacherId(teacherid);
                }
            }

            if(r.getCell(1)!=null){
                String teachername = r.getCell(1).getStringCellValue();
                if(teachername!=null){
                    teacher.setTeacherName(teachername);
                }
            }

            if(r.getCell(2)!=null){
                String department = r.getCell(2).getStringCellValue();
                String classes = department.substring(department.length() - 3);
                Integer departmentid = departmentService.selectByDepartmentName(classes);
                if(departmentid!=null){
                 teacher.setDepartmentId(departmentid);
                }
            }

            if(r.getCell(3)!=null){
                String phone = r.getCell(3).getStringCellValue();
                if(phone.length()==11){
                    teacher.setPhone(phone);
                }
            }

            if(r.getCell(4)!=null){
                String gender = r.getCell(4).getStringCellValue();

                if("男".equals(gender)){
                    teacher.setGender((byte) 1);
                }else if("女".equals(gender)){
                    teacher.setGender((byte) 2);
                }else{
                    teacher.setGender((byte) 3);
                }
            }
            teacherService.insert(teacher);
        }

        //关流
        inputStream.close();

    }



}
