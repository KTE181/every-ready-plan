package com.kh.semi.attendance.controller;

import com.kh.semi.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attendance") // 공통 URL
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/clockIn")
    @ResponseBody // JSON 응답
    public ResponseEntity<?> clockIn(@RequestBody Map<String, String> requestData) {
        try {
            String empNo = requestData.get("empNo"); // JSON에서 empNo 추출
            attendanceService.clockIn(empNo);
            log.info("출근 등록 성공: {}", empNo);

            // JSON 응답 반환
            return ResponseEntity.ok(Map.of("success", true, "message", "출근 등록 완료", "empNo", empNo));
        } catch (IllegalStateException e) {
            // 중복 출근 처리 예외
            log.warn("출근 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            // 기타 예외
            log.error("출근 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "출근 등록 중 오류가 발생했습니다."));
        }
    }
    @PostMapping("/clockOut")
    @ResponseBody
    public ResponseEntity<?> clockOut(@RequestBody Map<String, String> requestData) {
        try {
            String empNo = requestData.get("empNo"); // JSON에서 empNo 추출
            attendanceService.clockOut(empNo); // 서비스 호출
            log.info("퇴근 등록 성공: {}", empNo);

            // JSON 응답 반환
            return ResponseEntity.ok(Map.of("success", true, "message", "퇴근 등록 완료", "empNo", empNo));
        } catch (IllegalStateException e) {
            // 중복 퇴근 처리 예외
            log.warn("퇴근 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            // 기타 예외
            log.error("퇴근 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "퇴근 등록 중 오류가 발생했습니다."));
        }

    }
    @GetMapping("/status")
    @ResponseBody
    public ResponseEntity<?> getAttendanceStatus(@RequestParam String empNo) {
        try {
            Map<String, Object> status = attendanceService.getAttendanceStatus(empNo);
            return ResponseEntity.ok(Map.of("success", true, "data", status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }







}