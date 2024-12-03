package com.kh.semi.pv.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MyPageMapper {

    @Select("""
        SELECT PROFILE_IMAGE 
        FROM EMPLOYEE 
        WHERE NO = #{employeeId}
    """)
    String getProfileImage(String employeeId);

    @Update("""
        UPDATE EMPLOYEE 
        SET PROFILE_IMAGE = #{newProfilePath} 
        WHERE NO = #{employeeId}
    """)
    void updateProfileImage(@Param("employeeId") String employeeId, @Param("newProfilePath") String newProfilePath);

    @Select("""
        SELECT PWD
        FROM EMPLOYEE
        WHERE NO = #{employeeId}
    """)
    String getCurrentPassword(String employeeId);

    @Update("""
        UPDATE EMPLOYEE
        SET PWD = #{newPassword}
        WHERE NO = #{employeeId}
    """)
    void updatePassword(@Param("employeeId") String employeeId, @Param("newPassword") String newPassword);
}


