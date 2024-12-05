package com.kh.semi.pv.controller;

import com.kh.semi.attendance.vo.AttendanceVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.service.MyAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyAttendanceController {
    private final MyAttendanceService service;

    @GetMapping("/myattendance")
    public String myAttendance(
            @RequestParam(value = "searchDate", required = false) String searchDate,
            Model model, HttpSession session) {
        // 세션에서 로그인 정보 가져오기
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");

        // 로그인 여부 확인
        if (loginEmployeeVo == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // 사번 추출 및 출근 내역 조회
        String empNo = loginEmployeeVo.getNo();
        List<AttendanceVo> attendanceList;

        if (searchDate != null && !searchDate.isEmpty()) {
            // 검색 조건이 있을 경우
            attendanceList = service.getAttendanceListByDate(empNo, searchDate);
        } else {
            // 검색 조건이 없을 경우 전체 조회
            attendanceList = service.getAttendanceList(empNo);
        }

        // 조회한 데이터를 모델에 추가
        model.addAttribute("attendanceList", attendanceList);
        return "pv/myattendance/myattendance"; // JSP 경로 반환
    }
    @GetMapping("/myattendance/search")
    public String myAttendance(
            @RequestParam(value = "searchDate", required = false) String searchDate,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model, HttpSession session) {

        // 페이지 번호가 1보다 작은 경우 기본값으로 설정
        if (page < 1) {
            page = 1;
        }

        // 세션에서 로그인 정보 가져오기
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");

        if (loginEmployeeVo == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        String empNo = loginEmployeeVo.getNo();
        int listCount = service.getAttendanceCount(empNo, searchDate);

        int pageLimit = 5;
        int boardLimit = 12;
        PageVo pageVo = new PageVo(listCount, page, pageLimit, boardLimit);

        List<AttendanceVo> attendanceList = service.getAttendanceListWithPaging(empNo, searchDate, pageVo);

        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("searchDate", searchDate);

        return "pv/myattendance/myattendance/search";
    }




}

