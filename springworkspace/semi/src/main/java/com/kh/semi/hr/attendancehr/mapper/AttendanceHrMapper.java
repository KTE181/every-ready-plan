package com.kh.semi.hr.attendancehr.mapper;

import com.kh.semi.hr.attendancehr.vo.AttendanceHrVo;
import com.kh.semi.util.page.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AttendanceHrMapper {

        @Select("""
                    SELECT
                        A.NO AS no,
                        A.EMP_NO AS empNo,
                        E.NAME AS name,
                        TO_CHAR(A."DATE", 'YYYY-MM-DD') AS attendanceDate,
                        TO_CHAR(A.CI_TIME, 'HH24:MI') AS ciTime,
                        TO_CHAR(A.CO_TIME, 'HH24:MI') AS coTime,
                        CASE
                            WHEN A.CO_TIME IS NOT NULL THEN
                                FLOOR(EXTRACT(HOUR FROM (CAST(A.CO_TIME AS TIMESTAMP) - CAST(A.CI_TIME AS TIMESTAMP)))) || '시간 ' ||
                                MOD(FLOOR(EXTRACT(MINUTE FROM (CAST(A.CO_TIME AS TIMESTAMP) - CAST(A.CI_TIME AS TIMESTAMP)))), 60) || '분'
                            ELSE
                                '0시간 00분'
                        END AS workTime,
                        D.NAME AS deptName,
                        P.NAME AS position
                    FROM ATTENDANCE A
                    JOIN EMPLOYEE E ON A.EMP_NO = E.NO
                    JOIN DEPARTMENT D ON E.DEPT_CODE = D.NO
                    JOIN POSITION P ON E.POSITION_CODE = P.NO
                    WHERE A.DEL_YN = 'N'
                        AND (#{deptCode} IS NULL OR E.DEPT_CODE = #{deptCode})
                        AND (#{name} IS NULL OR E.NAME LIKE '%' || #{name} || '%')
                        AND (#{empNo} IS NULL OR A.EMP_NO = #{empNo})
                    ORDER BY A."DATE" DESC, A.CI_TIME ASC
                    OFFSET #{pageVo.offset} ROWS FETCH NEXT #{pageVo.boardLimit} ROWS ONLY
                """)
        List<AttendanceHrVo> selectAttendanceListWithPaging(
                @Param("deptCode") String deptCode,
                @Param("name") String name,
                @Param("empNo") String empNo,
                @Param("pageVo") PageVo pageVo
        );


        @Select("""
            SELECT COUNT(*)
            FROM ATTENDANCE A
            JOIN EMPLOYEE E ON A.EMP_NO = E.NO
            WHERE A.DEL_YN = 'N'
                AND (#{deptCode} IS NULL OR E.DEPT_CODE = #{deptCode})
                AND (#{name} IS NULL OR E.NAME LIKE '%' || #{name} || '%')
                AND (#{empNo} IS NULL OR A.EMP_NO = #{empNo})
            """)
        int countAttendanceList(@Param("deptCode") String deptCode,
                                @Param("name") String name,
                                @Param("empNo") String empNo);

        @Update("""
                    UPDATE ATTENDANCE
                    SET DEL_YN = 'Y'
                    WHERE NO IN (${ids})
                """)
        void deleteEmployees(@Param("ids") String ids);







}


