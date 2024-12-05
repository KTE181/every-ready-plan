package com.kh.semi.hr.employeehr.mapper;

import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("""
        UPDATE EMPLOYEE
        SET DEL_YN = 'Y'
        WHERE NO IN (#{ids})
        """)
    void updateDelYnToYes(@Param("ids") String ids);


}
