package com.kh.semi.login.mapper;

import com.kh.semi.login.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("""
            SELECT
                NO
                ,PROFILE_IMAGE
                ,PWD
                ,NAME
                ,BIRTH
                ,GENDER
                ,EMAIL
                ,PHONE
                ,EMERGENCY_PHONE
                ,ADDRESS
                ,DEPT_CODE
                ,POSITION_CODE
                ,SALARY
                ,BANK_CODE
                ,ACCOUNT_NO
                ,TOTAL_VACATION_DAYS
                ,STATUS_CODE
                ,ENTER_DATE
                ,OUT_DATE
                ,ENROLL_DATE
                ,MODIFY_DATE
                ,DEL_YN
            FROM EMPLOYEE
            WHERE EMAIL = #{email}
            AND PWD = #{pwd}
            AND DEL_YN = 'N'
            """)
    LoginVo login(LoginVo vo);
}
