package com.ali.wechatdemo.dto;

import java.util.List;

public class DepartmentList {

    private Integer departmentId;

    private String departmentName;

    private List<DepartmentList> subDeps;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<DepartmentList> getSubDeps() {
        return subDeps;
    }

    public void setSubDeps(List<DepartmentList> subDeps) {
        this.subDeps = subDeps;
    }
}
