package com.kh.semi.attendance.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AttendanceMapper {
    // 출근 기록 확인 (퇴근 시간이 NULL이거나 출근 기록만 있는 경우)
    @Select("""
        SELECT COUNT(*)
        FROM ATTENDANCE
        WHERE EMP_NO = #{empNo}
          AND "DATE" = TRUNC(SYSDATE)
          AND CO_TIME IS NULL
          AND DEL_YN = 'N'
    """)
    int checkActiveAttendance(String empNo);

    // 모든 출근 기록 확인 (퇴근 시간과 관계없이)
    @Select("""
        SELECT COUNT(*)
        FROM ATTENDANCE
        WHERE EMP_NO = #{empNo}
          AND "DATE" = TRUNC(SYSDATE)
          AND DEL_YN = 'N'
    """)
    int checkAllAttendance(String empNo);
    @Insert("""
        INSERT INTO ATTENDANCE (NO, EMP_NO, "DATE", CI_TIME) 
        VALUES (
            SEQ_ATTENDANCE.NEXTVAL, 
            #{empNo}, -- 문자로 처리
            TRUNC(SYSTIMESTAMP), 
            SYSTIMESTAMP
        )
        """)
    void insertAttendance(String empNo);
    @Update("""
    UPDATE ATTENDANCE
    SET CO_TIME = SYSTIMESTAMP
    WHERE EMP_NO = #{empNo}
      AND "DATE" = TRUNC(SYSDATE)
      AND CO_TIME IS NULL
      AND DEL_YN = 'N'
    """)
    int updateAttendanceOut(String empNo);




}
