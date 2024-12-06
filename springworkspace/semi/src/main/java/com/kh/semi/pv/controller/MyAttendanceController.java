package com.kh.semi.pv.controller;

import com.kh.semi.attendance.vo.AttendanceVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.service.MyAttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyAttendanceController {
    private final MyAttendanceService service;

    @GetMapping("myattendance")
    public String searchAttendance(
            @RequestParam(value = "searchDate", required = false) String searchDate,
            @RequestParam(value = "page", defaultValue = "1", required = false) int currentPage,
            Model model, HttpSession session) {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        if(loginEmployeeVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }


        String empNo = loginEmployeeVo.getNo();

        // 전체 데이터 수 조회 (검색 조건 포함)
        int listCount = service.getAttendanceCountWithSearch(empNo, searchDate);

        // 페이징 정보 계산
        int pageLimit = 5;  // 하단 페이지 번호 개수
        int boardLimit = 12; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // 검색 조건 + 페이징 처리된 출근 내역 조회
        List<AttendanceVo> attendanceList = service.searchAttendanceWithPaging(empNo, searchDate, pageVo);

        // 모델에 데이터 전달
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("searchDate", searchDate);

        return "pv/myattendance/myattendance";
    }





}

