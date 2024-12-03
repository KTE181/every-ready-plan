package com.kh.semi.attendance.service;

import com.kh.semi.attendance.mapper.AttendanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AttendanceService {

    private final AttendanceMapper attendanceMapper;

    public void clockIn(String empNo) {
        try {
            int existingRecords = attendanceMapper.checkActiveAttendance(empNo);
            if (existingRecords > 0) {
                throw new IllegalStateException("이미 출근 등록이 완료되었습니다.");
            }

            Long empNoAsLong = Long.parseLong(empNo); // String을 Long으로 변환
            attendanceMapper.insertAttendance(empNo);
        } catch (NumberFormatException e) {
            log.error("사번 형식 오류: {}", empNo, e);
            throw new IllegalArgumentException("사번은 숫자 형식이어야 합니다.");
        }
    }
    public void clockOut(String empNo) {
        try {
            // 출근 기록 확인
            int existingRecords = attendanceMapper.checkAllAttendance(empNo);
            if (existingRecords == 0) {
                throw new IllegalStateException("출근 기록이 없습니다.");
            }

            // 퇴근 기록 확인 및 업데이트
            int updatedRecords = attendanceMapper.updateAttendanceOut(empNo);
            if (updatedRecords == 0) {
                throw new IllegalStateException("퇴근 등록 실패: 이미 퇴근이 등록되었습니다.");
            }
        } catch (IllegalStateException e) {
            // 명확한 예외 메시지를 그대로 전달
            log.error("퇴근 등록 중 예외: {}", e.getMessage());
            throw e; // 예외를 다시 던짐
        } catch (Exception e) {
            // 기타 예외 처리
            log.error("퇴근 등록 중 예상치 못한 오류: {}", empNo, e);
            throw new IllegalStateException("퇴근 등록 중 오류가 발생했습니다.");
        }
    }
    public Map<String, Object> getAttendanceStatus(String empNo) {
        try {
            boolean hasClockIn = attendanceMapper.checkAllAttendance(empNo) > 0;
            boolean isWorking = attendanceMapper.checkActiveAttendance(empNo) > 0;

            Map<String, Object> status = new HashMap<>();
            status.put("hasClockIn", hasClockIn);
            status.put("isWorking", isWorking);

            return status;
        } catch (Exception e) {
            log.error("출근/퇴근 상태 확인 중 오류: {}", e.getMessage());
            throw new RuntimeException("출근/퇴근 상태 확인 중 오류가 발생했습니다.");
        }
    }



}
