package com.kh.semi.pv.service;

import com.kh.semi.attendance.vo.AttendanceVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.mapper.MyAttendanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyAttendanceService {
    private final MyAttendanceMapper mapper;

    public List<AttendanceVo> getAttendanceList(String empNo) {
        // 매퍼를 호출하여 empNo와 DEL_YN='N' 조건으로 데이터를 조회
        return mapper.selectAttendanceList(empNo);
    }
    public List<AttendanceVo> getAttendanceListByDate(String empNo, String searchDate) {
        return mapper.selectAttendanceListByDate(empNo, searchDate);
    }

    public List<AttendanceVo> getAttendanceListWithPaging(String empNo, String searchDate, PageVo pageVo) {
        return mapper.selectAttendanceListWithPaging(empNo, searchDate, pageVo.getBoardLimit(), pageVo.getOffset());
    }


    // 전체 데이터 개수 조회
    public int getAttendanceCount(String empNo, String searchDate) {
        return mapper.selectAttendanceCount(empNo, searchDate);
    }



}
