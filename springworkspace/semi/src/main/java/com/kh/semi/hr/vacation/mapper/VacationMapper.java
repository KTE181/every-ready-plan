package com.kh.semi.hr.vacation.mapper;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.employee.vo.SearchVo;
import com.kh.semi.hr.vacation.vo.VacationVo;
import com.kh.semi.pb.vo.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface VacationMapper {




    List<VacationVo> list(PageVo pvo, SearchVo searchVo);

    @Insert("""
            INSERT INTO VACATION_LOG(
            NO,
            EMP_NO,
            CODE,
            THISDATE,
            REASON
            )VALUES(
            SEQ_VACATION_LOG.NEXTVAL,
            #{empNo},
            #{code},
            #{thisDate},
            #{reason}

            )
            """)
    int insert(VacationVo vo);


    @Select("""
            SELECT
                E.NO,
            PROFILE_IMAGE,
            PWD,
            E.NAME,
            BIRTH,
            GENDER,
            EMAIL,
            PHONE,
            EMERGENCY_PHONE,
            ADDRESS,
            DEPT_CODE,
            D.NAME AS DNAME,
            POSITION_CODE,
            P.NAME AS PNAME,
            SALARY,
            BANK_CODE,
            ACCOUNT_NO,
            TOTAL_VACATION_DAYS,
            STATUS_CODE,
            ES.NAME AS ESNAME,
            ENTER_DATE,
            OUT_DATE,
            ENROLL_DATE,
            MODIFY_DATE,
            DEL_YN
            FROM EMPLOYEE  E
            JOIN DEPARTMENT D ON(E.DEPT_CODE = D.NO)
            JOIN POSITION P ON(E.POSITION_CODE = P.NO)
            JOIN EMP_STATUS ES ON(E.STATUS_CODE =ES.NO)
            WHERE E.DEL_YN='N'
            ORDER BY E.NO DESC
            OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
            """)
    List<EmployeeVo> empVoList(PageVo pvo2);

    @Select("""
            SELECT
                E.NO,
            PROFILE_IMAGE,
            PWD,
            E.NAME,
            BIRTH,
            GENDER,
            EMAIL,
            PHONE,
            EMERGENCY_PHONE,
            ADDRESS,
            DEPT_CODE,
            D.NAME AS DNAME,
            POSITION_CODE,
            P.NAME AS PNAME,
            SALARY,
            BANK_CODE,
            ACCOUNT_NO,
            TOTAL_VACATION_DAYS,
            STATUS_CODE,
            ES.NAME AS ESNAME,
            ENTER_DATE,
            OUT_DATE,
            ENROLL_DATE,
            MODIFY_DATE,
            DEL_YN
            FROM EMPLOYEE  E
            JOIN DEPARTMENT D ON(E.DEPT_CODE = D.NO)
            JOIN POSITION P ON(E.POSITION_CODE = P.NO)
            JOIN EMP_STATUS ES ON(E.STATUS_CODE =ES.NO)
            WHERE E.NO=#{empNo} AND DEL_YN='N'
            """)
    EmployeeVo selectvolist(String empNo);

    @Select("""
            SELECT
                    A.NO ,
                    A.THISDATE,
                    A.EMP_NO,
                    B.NAME AS ENAME,
                    C.NAME  AS DNAME,
                    D.NAME AS PNAME,
                    E.NAME AS TYPE,
                    B.TOTAL_VACATION_DAYS AS ALL_VACATION,
                    (SELECT
                    SUM(CASE
                    WHEN CODE = 2 THEN 0.5
                    ELSE 1               
                    END) AS TOTAL_COUNT
                    FROM VACATION_LOG
                    WHERE EMP_NO = A.EMP_NO
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
                    WHERE A.NO = #{selectNo} AND A.DEL_YN ='N'
            """)
    VacationVo getVacationVo(String selectNo);

    @Update("""
               UPDATE VACATION_LOG SET
               EMP_NO = #{empNo},
               CODE = #{code},
               THISDATE =#{thisDate},
               REASON=#{reason},
               MODIFY_DATE= SYSDATE
               WHERE  NO=#{selectNo} AND DEL_YN ='N'
            """)
    int update(Map<String, String> alldata);

    @Update("""
            UPDATE VACATION_LOG
            SET
            DEL_YN='Y'
            WHERE NO=#{no}
            """)
    int delete(String no);

    int editAll(String[] dataArr);

    @Select("""
            SELECT COUNT(NO) FROM VACATION_LOG
            WHERE DEL_YN='N'
            """)
    int getVacationCnt();

    @Select("""
            SELECT COUNT(NO) FROM EMPLOYEE
            WHERE DEL_YN ='N'
            """)
    int getEmpCnt();

    @Select("""
             SELECT
                        E.NO,
                        PROFILE_IMAGE,
                        PWD,
                        E.NAME,
                        BIRTH,
                        GENDER,
                        EMAIL,
                        PHONE,
                        EMERGENCY_PHONE,
                        ADDRESS,
                        DEPT_CODE,
                        D.NAME AS DNAME,
                        POSITION_CODE,
                        P.NAME AS PNAME,
                        SALARY,
                        BANK_CODE,
                        ACCOUNT_NO,
                        TOTAL_VACATION_DAYS,
                        STATUS_CODE,
                        ES.NAME AS ESNAME,
                        ENTER_DATE,
                        OUT_DATE,
                        ENROLL_DATE,
                        MODIFY_DATE,
                        DEL_YN
                        FROM EMPLOYEE  E
                        JOIN DEPARTMENT D ON(E.DEPT_CODE = D.NO)
                        JOIN POSITION P ON(E.POSITION_CODE = P.NO)
                        JOIN EMP_STATUS ES ON(E.STATUS_CODE =ES.NO)
                        WHERE E.DEL_YN='N'
                        ORDER BY E.NO DESC
                        OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
            """)
    List<EmployeeVo> getEmplistdata(PageVo pvo);


    EmployeeVo selectEmpVo(String searchEmpNo, String searchEname);
}