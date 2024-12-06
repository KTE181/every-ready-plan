package com.kh.semi.hr.employeehr.mapper;

import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeHrMapper {

    // 직원 수 조회 (검색 조건 포함)
    @Select("""
            SELECT COUNT(*)
            FROM EMPLOYEE E
            JOIN DEPARTMENT D ON D.NO = E.DEPT_CODE
            JOIN POSITION P ON P.NO = E.POSITION_CODE
            WHERE E.DEL_YN = 'N'
            AND (#{no} IS NULL OR E.NO = #{no})
            AND (#{name} IS NULL OR E.NAME LIKE '%' || #{name} || '%')
            AND (#{dname} IS NULL OR D.NAME = #{dname})
            AND (#{pname} IS NULL OR P.NAME = #{pname})
            AND (#{esname} IS NULL OR E.STATUS_CODE = #{esname})
            """)
    int selectEmployeeCount(
            @Param("no") String no,
            @Param("name") String name,
            @Param("dname") String dname,
            @Param("pname") String pname,
            @Param("esname") String esname
    );

    // 직원 리스트 조회 (검색 조건 + 페이징)
    @Select("""
            SELECT 
                E.NO AS no,
                E.NAME AS name,
                E.BIRTH AS birth,
                E.GENDER AS gender,
                E.EMAIL AS email,
                E.PHONE AS phone,
                D.NAME AS dname,
                P.NAME AS pname,
                S.NAME AS esname,
                E.ENTER_DATE AS enterDate,
                E.OUT_DATE AS outDate
            FROM EMPLOYEE E
            JOIN DEPARTMENT D ON D.NO = E.DEPT_CODE
            JOIN POSITION P ON P.NO = E.POSITION_CODE
            JOIN EMP_STATUS S ON S.NO = E.STATUS_CODE
            WHERE E.DEL_YN = 'N'
            AND (#{no} IS NULL OR E.NO = #{no})
            AND (#{name} IS NULL OR E.NAME LIKE '%' || #{name} || '%')
            AND (#{dname} IS NULL OR D.NAME = #{dname})
            AND (#{pname} IS NULL OR P.NAME = #{pname})
            AND (#{esname} IS NULL OR E.STATUS_CODE = #{esname})
            ORDER BY E.NO
            OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
            """)
    List<EmployeeVo> selectEmployeeListWithSearch(
            @Param("no") String no,
            @Param("name") String name,
            @Param("dname") String dname,
            @Param("pname") String pname,
            @Param("esname") String esname,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    //삭제
    @Update("""
        UPDATE EMPLOYEE
        SET DEL_YN = 'Y'
        WHERE NO IN (#{ids})
        """)
    void updateDelYnToYes(@Param("ids") String ids
    );

    // 사원 등록
    @Insert("""
            INSERT INTO EMPLOYEE (
                NO, PROFILE_IMAGE,PWD, NAME, BIRTH, GENDER, EMAIL, PHONE, EMERGENCY_PHONE, ADDRESS,
                DEPT_CODE, POSITION_CODE, SALARY, BANK_CODE, ACCOUNT_NO, TOTAL_VACATION_DAYS,
                STATUS_CODE, ENTER_DATE, OUT_DATE, ENROLL_DATE, MODIFY_DATE, DEL_YN
            )
            VALUES (
                SEQ_EMPLOYEE.NEXTVAL, '/img/employee/default.png',1111, #{name}, #{birth}, #{gender}, #{email}, #{phone}, #{emergencyPhone}, #{address},
                #{deptCode}, #{positionCode}, #{salary}, #{bankCode}, #{accountNo}, #{totalVacationDays},
                #{statusCode}, #{enterDate}, #{outDate}, SYSDATE, SYSDATE, 'N'
            )
            """)
    void insertEmployee(EmployeeVo employeeVo);

    @Update("""
            UPDATE EMPLOYEE
            SET PROFILE_IMAGE = #{profileImagePath}
            WHERE NO = #{no}
            """)
    void updateProfileImage(@Param("no") String employeeId, @Param("profileImagePath") String profileImagePath);


    
    //상세조회
    @Select("""
        SELECT 
            E.NO AS no,
            E.NAME AS name,
            E.BIRTH AS birth,
            E.GENDER AS gender,
            E.EMAIL AS email,
            E.PHONE AS phone,
            E.EMERGENCY_PHONE AS emergencyPhone,
            E.ADDRESS AS address,
            E.DEPT_CODE AS deptCode,
            D.NAME AS dname,
            E.POSITION_CODE AS positionCode,
            P.NAME AS pname,
            E.SALARY AS salary,
            E.BANK_CODE AS bankCode,
            E.ACCOUNT_NO AS accountNo,
            E.TOTAL_VACATION_DAYS AS totalVacationDays,
            E.STATUS_CODE AS statusCode,
            S.NAME AS esname,
            E.ENTER_DATE AS enterDate,
            E.OUT_DATE AS outDate
        FROM EMPLOYEE E
        JOIN DEPARTMENT D ON D.NO = E.DEPT_CODE
        JOIN POSITION P ON P.NO = E.POSITION_CODE
        JOIN EMP_STATUS S ON S.NO = E.STATUS_CODE
        WHERE E.NO = #{no}
        """)
    EmployeeVo selectEmployeeByNo(@Param("no") String no);

    @Select("""
        SELECT 
            E.NO AS no,
            E.NAME AS name,
            E.BIRTH AS birth,
            E.GENDER AS gender,
            E.EMAIL AS email,
            E.PHONE AS phone,
            E.EMERGENCY_PHONE AS emergencyPhone,
            E.ADDRESS AS address,
            E.DEPT_CODE AS deptCode,
            D.NAME AS dname,
            E.POSITION_CODE AS positionCode,
            P.NAME AS pname,
            E.SALARY AS salary,
            E.BANK_CODE AS bankCode,
            E.ACCOUNT_NO AS accountNo,
            E.TOTAL_VACATION_DAYS AS totalVacationDays,
            E.STATUS_CODE AS statusCode,
            S.NAME AS esname,
            E.ENTER_DATE AS enterDate,
            E.OUT_DATE AS outDate
        FROM EMPLOYEE E
        JOIN DEPARTMENT D ON E.DEPT_CODE = D.NO
        JOIN POSITION P ON E.POSITION_CODE = P.NO
        JOIN EMP_STATUS S ON E.STATUS_CODE = S.NO
        WHERE E.NO = #{no} AND E.DEL_YN = 'N'
        """)
    EmployeeVo selectEmployeeDetail(@Param("no") String no);

    @Update("""
        UPDATE EMPLOYEE
        SET 
            NAME = #{name},
            BIRTH = #{birth},
            PHONE = #{phone},
            EMERGENCY_PHONE = #{emergencyPhone},
            EMAIL = #{email},
            ADDRESS = #{address},
            DEPT_CODE = #{deptCode},
            POSITION_CODE = #{positionCode},
            ENTER_DATE = #{enterDate},
            OUT_DATE = #{outDate},
            SALARY = #{salary},
            TOTAL_VACATION_DAYS = #{totalVacationDays},
            STATUS_CODE = #{statusCode},
            BANK_CODE = #{bankCode},
            ACCOUNT_NO = #{accountNo},
            GENDER = #{gender},
            PROFILE_IMAGE = #{profileImage}
        WHERE NO = #{no}
    """)
    void updateEmployee(EmployeeVo employeeVo);




}
