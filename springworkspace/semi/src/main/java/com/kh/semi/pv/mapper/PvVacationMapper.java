package com.kh.semi.pv.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.vo.PvVacationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PvVacationMapper {

    List<PvVacationVo> getVacationList(String no, String date, PageVo pvo);

    @Select("""
                   SELECT *
            FROM (
                SELECT
                        A.NO ,
                        A.THISDATE,
                        A.EMP_NO,
                        B.NAME AS ENAME,
                        C.NAME  AS DNAME,
                        B.DEPT_CODE,
                        D.NAME AS PNAME,
                        E.NAME AS TYPE,
                        B.TOTAL_VACATION_DAYS AS ALL_VACATION,
                        (SELECT
                        SUM(CASE
                        WHEN CODE = 2 THEN 0.5
                        ELSE 1               \s
                        END) AS TOTAL_COUNT
                        FROM VACATION_LOG
                        WHERE EMP_NO = A.EMP_NO
                        AND DEL_YN='N'
                        AND CODE NOT IN (3)
                        GROUP BY EMP_NO) AS USE_VACATION,
                        B.TOTAL_VACATION_DAYS - COALESCE(
                            (SELECT
                                SUM(
                                    CASE
                                        WHEN CODE = 2 THEN 0.5
                                        ELSE 1
                                    END
                                ) AS TOTAL_COUNT
                            FROM VACATION_LOG
                            WHERE EMP_NO = A.EMP_NO
                                AND DEL_YN='N'
                              AND CODE NOT IN (3)
                            GROUP BY EMP_NO),
                                0
                            ) AS VACATION,
                        A.REASON ,
                        A.ENROLL_DATE,
                        A.MODIFY_DATE,
                        A.DEL_YN FROM VACATION_LOG A
                        JOIN EMPLOYEE B ON(A.EMP_NO = B.NO)
                        JOIN DEPARTMENT C ON (B.DEPT_CODE = C.NO)
                        JOIN POSITION D ON (B.POSITION_CODE = D.NO)
                        JOIN VACATION_TYPE E ON(A.CODE =E.NO)
                        WHERE A.DEL_YN ='N'
                ORDER BY A.ENROLL_DATE DESC
            )
            WHERE ROWNUM = 1
            """)
    PvVacationVo getVacationVo(String no);

    @Select("""
            SELECT COUNT(NO) FROM VACATION_LOG
                        WHERE EMP_NO=#{no} AND DEL_YN='N'
            """)
    int gerVacationCnt(String no);
}
