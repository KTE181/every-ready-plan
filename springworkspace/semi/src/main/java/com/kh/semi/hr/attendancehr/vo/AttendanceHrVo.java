package com.kh.semi.hr.attendancehr.vo;

import lombok.Data;

@Data
public class AttendanceHrVo {
    private String no;
    private String empNo;
    private String name;
    private String deptName; // 소속 부서명
    private String position; // 직급
    private String ciTime;
    private String coTime;
    private String workTime; // 근무 시간
    private String attendanceDate;
    private String delYn;
}
