package com.kh.semi.pb.mapper;

import com.kh.semi.pb.vo.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

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
            JOIN DEPARTMENT D ON (D.NO = E.DEPT_CODE)
            JOIN POSITION P ON (P.NO = E.POSITION_CODE)
            WHERE E.DEL_YN = 'N'
            """)
    List<EmployeeVo> employeeVoList();
}
