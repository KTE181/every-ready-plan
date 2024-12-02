package com.kh.semi.hr.overtime.mapper;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.overtime.vo.OverTimeVo;
import com.kh.semi.pb.vo.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OverTimeMapper {

    @Select("""
            SELECT
            A.NO  ,
            A.THISDATE  ,
            A.EMP_NO  ,
            B.NAME  NAME,
            C.NAME  DNAME,
            D.NAME  PNAME,
            A.TYPE,
            A.WORK_HOUR 
             FROM OVERTIME A
             JOIN EMPLOYEE B ON(A.EMP_NO = B.NO)
             JOIN DEPARTMENT C ON (B.DEPT_CODE = C.NO)
             JOIN POSITION D ON (B.POSITION_CODE = D.NO)
             WHERE A.DEL_YN='N'
             ORDER BY A.NO DESC
            OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
            """)
    List<OverTimeVo> selectAll(PageVo pvo);

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
    List<EmployeeVo> empVoList(PageVo pvo);
    @Insert("""
            INSERT INTO OVERTIME
            (
            NO,
            EMP_NO,
            THISDATE,
            TYPE,
            WORK_HOUR
            )
            VALUES
            (
            SEQ_OVERTIME.NEXTVAL,
            #{empNo},
            #{thisDate},
            #{type},
            TO_DATE(#{workHour}, 'HH24:MI')
            )
            """)
    int insert(OverTimeVo vo);

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
            WHERE E.NO=#{empNo}
            """)
    EmployeeVo selectvolist(String empNo);

    @Select("""
            SELECT
            A.NO  ,
            A.THISDATE  ,
            A.EMP_NO  ,
            B.NAME  NAME,
            C.NAME  DNAME,
            D.NAME  PNAME,
            A.TYPE,
            A.WORK_HOUR 
             FROM OVERTIME A
             JOIN EMPLOYEE B ON(A.EMP_NO = B.NO)
             JOIN DEPARTMENT C ON (B.DEPT_CODE = C.NO)
             JOIN POSITION D ON (B.POSITION_CODE = D.NO)
             WHERE A.NO =#{no} AND A.DEL_YN='N'
            """)
    OverTimeVo detail(String no);

    @Update("""
            UPDATE OVERTIME
            SET THISDATE = #{thisDate},
            TYPE=#{type},
            WORK_HOUR = TO_DATE(#{workHour}, 'HH24:MI')
            WHERE NO = #{no}
            """)
    int edit(OverTimeVo alldata);

    @Update("""
            UPDATE OVERTIME
            SET DEL_YN ='Y'
            WHERE NO = #{no}
            """)
    int delete(String no);

    int editAll(String[] dataArr);

    @Select("""
            SELECT COUNT(NO) FROM OVERTIME
            WHERE DEL_YN='N'
            """)
    int getOverTimeCnt();

    @Select("""
            SELECT COUNT(NO) FROM EMPLOYEE
            WHERE DEL_YN='N'
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
}
