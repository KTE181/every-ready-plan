package com.kh.semi.login.mapper;

import com.kh.semi.login.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("""
SELECT
            e.NO
            ,e.PROFILE_IMAGE
            ,e.PWD
            ,e.NAME
            ,e.BIRTH
            ,e.GENDER
            ,e.EMAIL
            ,e.PHONE
            ,e.EMERGENCY_PHONE
            ,e.ADDRESS
            ,e.DEPT_CODE
            ,d.NAME AS DEPT_NAME
            ,e.POSITION_CODE
            ,p.NAME AS POSITION_NAME
            ,e.SALARY
            ,e.BANK_CODE
            ,b.NAME AS BANK_NAME
            ,e.ACCOUNT_NO
            ,e.TOTAL_VACATION_DAYS
            ,e.STATUS_CODE
            ,s.NAME AS STATUS_NAME
            ,e.ENTER_DATE
            ,e.OUT_DATE
            ,e.ENROLL_DATE
            ,e.MODIFY_DATE
            ,e.DEL_YN
        FROM EMPLOYEE e
        LEFT JOIN DEPARTMENT d ON e.DEPT_CODE = d.NO
        LEFT JOIN POSITION p ON e.POSITION_CODE = p.NO
        LEFT JOIN EMP_STATUS s ON e.STATUS_CODE = s.NO
        LEFT JOIN BANK b ON e.BANK_CODE = b.NO
        WHERE e.EMAIL = #{email}
        AND e.PWD = #{pwd}
        AND e.DEL_YN = 'N'
        """)
    LoginVo login(LoginVo vo);

}
