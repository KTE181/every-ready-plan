package com.kh.semi.pb.mapper;

import com.kh.semi.pb.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    // 검색 조건 포함 직원 수 조회
    @Select("""
            SELECT COUNT(*)
            FROM EMPLOYEE E
            JOIN DEPARTMENT D ON D.NO = E.DEPT_CODE
            JOIN POSITION P ON P.NO = E.POSITION_CODE
            WHERE E.DEL_YN = 'N'
            AND (#{department} IS NULL OR D.NAME = #{department})
            AND (#{position} IS NULL OR P.NAME = #{position})
            AND (#{name} IS NULL OR E.NAME LIKE '%' || #{name} || '%')
            """)
    int countEmployeesWithSearch(
            @Param("department") String department,
            @Param("position") String position,
            @Param("name") String name
    );

    // 검색 조건 + 페이징 처리된 직원 목록 조회
    @Select("""
            SELECT
                E.NO AS no,
                E.NAME AS name,
                D.NAME AS department,
                P.NAME AS position,
                E.PHONE AS phoneNumber,
                E.EMAIL AS email,
                E.BIRTH AS birth,
                E.GENDER AS gender
            FROM EMPLOYEE E
            JOIN DEPARTMENT D ON D.NO = E.DEPT_CODE
            JOIN POSITION P ON P.NO = E.POSITION_CODE
            WHERE E.DEL_YN = 'N'
            AND (#{department} IS NULL OR D.NAME = #{department})
            AND (#{position} IS NULL OR P.NAME = #{position})
            AND (#{name} IS NULL OR E.NAME LIKE '%' || #{name} || '%')
            ORDER BY E.NO
            OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
            """)
    List<EmployeeVo> searchEmployeesWithPaging(
            @Param("department") String department,
            @Param("position") String position,
            @Param("name") String name,
            @Param("offset") int offset,
            @Param("limit") int limit
    );


}
