package com.kh.semi.hr.attendancehr.vo;

import lombok.Data;

@Data
public class EmployeeVo {
    private String no;
    private String name;
    private String deptName; //DEPARTMENT테이블 NAME
    private String position; //POSITION테이블 NAME

}
