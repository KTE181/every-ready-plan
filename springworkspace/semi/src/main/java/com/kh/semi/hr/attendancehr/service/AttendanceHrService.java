package com.kh.semi.hr.attendancehr.service;

import com.kh.semi.hr.attendancehr.mapper.AttendanceHrMapper;
import com.kh.semi.hr.attendancehr.vo.AttendanceHrVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AttendanceHrService {
    private final AttendanceHrMapper mapper;

    public List<AttendanceHrVo> getAttendanceList() {
        return mapper.selectAttendanceList();
    }

}
