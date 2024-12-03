package com.kh.semi.pv.vo;

import lombok.Data;

@Data
public class MyPageVo {
    private String no;
    private String profileImage;
    private String pwd;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
    private String emergencyPhone;
    private String address;
    private String deptCode;
    private String deptName; // 추가: 부서명
    private String positionCode;
    private String positionName; // 추가: 직급명
    private String salary;
    private String bankCode;
    private String bankName;
    private String accountNo;
    private String totalVacationDays;
    private String statusCode;
    private String statusName; // 추가: 재직 상태명
    private String enterDate;
    private String outDate;
    private String enrollDate;
    private String modifyDate;
    private String delYn;

}
