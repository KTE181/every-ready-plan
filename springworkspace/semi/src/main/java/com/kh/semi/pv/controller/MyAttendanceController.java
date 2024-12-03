package com.kh.semi.pv.controller;

import com.kh.semi.attendance.vo.AttendanceVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pv.service.MyAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyAttendanceController {
    private final MyAttendanceService service;

    @GetMapping("/myattendance")
    public String myAttendance(Model model, HttpSession session) {
        // 세션에서 로그인 정보 가져오기
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");

        // 로그인 여부 확인
        if (loginEmployeeVo == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // 사번 추출 및 출근 내역 조회
        String empNo = loginEmployeeVo.getNo();
        List<AttendanceVo> attendanceList = service.getAttendanceList(empNo);

        // 조회한 데이터를 모델에 추가
        model.addAttribute("attendanceList", attendanceList);
        return "pv/myattendance/myattendance"; // JSP 경로 반환
    }
}
