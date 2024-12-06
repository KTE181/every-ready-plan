package com.kh.semi.hr;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HrMapper {


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
                    WHERE DEL_YN='N'
            """)
    List<EmployeeVo> getList();
}
