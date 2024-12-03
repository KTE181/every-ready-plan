package com.kh.semi.attendance.vo;

import lombok.Data;

@Data
public class AttendanceVo {
    private String no; //순번 seq를 받아옴
    private String empNo; //사번
    private String ciTime; //출근시간
    private String coTime; //퇴근시간
    private String delYn; //삭제여부
    private String date; //일자
}
