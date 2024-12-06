package com.kh.semi.hr.attendancehr.mapper;

import com.kh.semi.hr.attendancehr.vo.AttendanceHrVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendanceHrMapper {

        @Select("""
                SELECT
                    A.NO AS no,
                    A.EMP_NO AS empNo,
                    E.NAME AS name,
                    A."DATE" AS attendanceDate,
                    TO_CHAR(A.CI_TIME, 'HH24:MI') AS ciTime,
                    TO_CHAR(A.CO_TIME, 'HH24:MI') AS coTime,
                    CASE
                        WHEN A.CO_TIME IS NOT NULL THEN
                            FLOOR(EXTRACT(HOUR FROM (CAST(A.CO_TIME AS TIMESTAMP) - CAST(A.CI_TIME AS TIMESTAMP)))) || '시간 ' ||
                            MOD(FLOOR(EXTRACT(MINUTE FROM (CAST(A.CO_TIME AS TIMESTAMP) - CAST(A.CI_TIME AS TIMESTAMP)))), 60) || '분'
                        ELSE
                            '0시간 00분'
                    END AS workTime
                FROM ATTENDANCE A
                JOIN EMPLOYEE E ON A.EMP_NO = E.NO
                WHERE A.DEL_YN = 'N'
                ORDER BY A."DATE" DESC, A.CI_TIME ASC

                """)
        List<AttendanceHrVo> selectAttendanceList();


}
