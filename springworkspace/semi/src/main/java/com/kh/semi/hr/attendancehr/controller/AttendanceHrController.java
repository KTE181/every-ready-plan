package com.kh.semi.hr.attendancehr.controller;

import com.kh.semi.hr.attendancehr.service.AttendanceHrService;
import com.kh.semi.hr.attendancehr.vo.AttendanceHrVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AttendanceHrController {
    private final AttendanceHrService service;

    @GetMapping("attendancehr")
    public String attendancehr(Model model) {
        List<AttendanceHrVo> attendanceList = service.getAttendanceList();
        model.addAttribute("attendanceList", attendanceList);
        return "hr/attendancehr/attendancehr"; // JSP 파일 경로
    }

}
