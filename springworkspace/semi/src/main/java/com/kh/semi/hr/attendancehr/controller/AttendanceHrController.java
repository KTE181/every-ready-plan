package com.kh.semi.hr.attendancehr.controller;

import com.kh.semi.hr.attendancehr.service.AttendanceHrService;
import com.kh.semi.hr.attendancehr.vo.AttendanceHrVo;
import com.kh.semi.hr.attendancehr.vo.EmployeeVo;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AttendanceHrController {
    private final AttendanceHrService service;

    @GetMapping("/attendancehr")
    public String attendancehr(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "deptCode", required = false) String deptCode,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "empNo", required = false) String empNo,
                               Model model, HttpSession session) {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        log.info("검색 조건 - deptCode: {}, name: {}, empNo: {}", deptCode, name, empNo);

        int listCount = service.getAttendanceListCount(deptCode, name, empNo);
        PageVo pageVo = new PageVo(listCount, page, 5, 15);

        List<AttendanceHrVo> attendanceList = service.getAttendanceList(deptCode, name, empNo, pageVo);

        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("deptCode", deptCode);
        model.addAttribute("name", name);
        model.addAttribute("empNo", empNo);

        return "hr/attendancehr/attendancehr";
    }


    @PostMapping("/attendancehr/delete")
    public ResponseEntity<String> deleteAttendances(@RequestBody List<String> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                return ResponseEntity.badRequest().body("삭제할 항목이 없습니다.");
            }

            // 쉼표로 연결된 문자열로 변환
            String idString = String.join(",", ids);

            // 서비스 호출
            service.deleteEmployees(idString);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            log.error("삭제 처리 중 오류 발생", e);
            return ResponseEntity.status(500).body("삭제 실패");
        }
    }

    // 사번으로 사원 정보 조회
    @GetMapping("/employee/search")
    @ResponseBody
    public EmployeeVo searchEmployeeByEmpNo(@RequestParam("empNo") String empNo) {
        log.info("사원 검색 요청 - empNo: {}", empNo);
        EmployeeVo employee = service.getEmployeeByEmpNo(empNo);
        log.info("검색 결과: {}", employee); // 반환된 결과 확인
        return employee;
    }


    // 근태 등록
    @PostMapping("/attendancehr/register")
    @ResponseBody
    public ResponseEntity<String> registerAttendance(@RequestBody AttendanceHrVo attendance) {
        try {
            log.info("근태 등록 요청 - {}", attendance);
            service.registerAttendance(attendance);
            return ResponseEntity.ok("등록 성공");
        } catch (Exception e) {
            log.error("근태 등록 중 오류 발생", e);
            return ResponseEntity.status(500).body("등록 실패");
        }
    }














}
