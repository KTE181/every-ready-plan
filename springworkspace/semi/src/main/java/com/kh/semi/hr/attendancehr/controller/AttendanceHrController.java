package com.kh.semi.hr.attendancehr.controller;

import com.kh.semi.hr.attendancehr.service.AttendanceHrService;
import com.kh.semi.hr.attendancehr.vo.AttendanceHrVo;
import com.kh.semi.util.page.PageVo;
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
                               Model model) {
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






}
