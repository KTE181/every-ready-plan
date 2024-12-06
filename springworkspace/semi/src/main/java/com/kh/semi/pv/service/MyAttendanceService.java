package com.kh.semi.pv.service;

import com.kh.semi.attendance.vo.AttendanceVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.mapper.MyAttendanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MyAttendanceService {
    private final MyAttendanceMapper mapper;

    // 전체 데이터 수 조회
    public int getAttendanceCountWithSearch(String empNo, String searchDate) {
        return mapper.countAttendanceWithSearch(empNo, searchDate);
    }

    // 검색 조건 + 페이징 처리된 출근 내역 조회
    public List<AttendanceVo> searchAttendanceWithPaging(String empNo, String searchDate, PageVo pageVo) {
        return mapper.searchAttendanceWithPaging(
                empNo,
                searchDate,
                pageVo.getOffset(),
                pageVo.getBoardLimit()
        );
    }





}
