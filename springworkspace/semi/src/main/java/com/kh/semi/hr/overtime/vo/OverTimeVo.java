package com.kh.semi.hr.overtime.vo;

import lombok.Data;

@Data
public class OverTimeVo {
    private String no; // 순번
    private String thisDate; //일자
    private String empNo; // 사번 
    private String name; // 이름 
    private String dname; // 부서이름
    private String deptCode;//부서코드
    private String pname;// 직급
    private String type; // 초과근무 유형
    private String workHour; // 초과근무 시간
    private String hour; // 시간
    private String minute; // 분


}
