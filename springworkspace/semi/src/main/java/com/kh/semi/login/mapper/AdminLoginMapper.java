package com.kh.semi.login.mapper;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminLoginMapper {

    @Select("""
            SELECT *
            FROM ADMIN
            WHERE EMAIL = #{email}
            AND PWD =#{pwd}
            """)
    AdminLoginVo adminlogin(AdminLoginVo vo);
}
