package com.kh.semi.qa.asemp.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asemp.vo.AsempVo;
import com.kh.semi.hr.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.*;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface AsempMapper {

    List<AsempVo> getAsempList(Model model, PageVo pvo, String area, String searchType, String searchValue);

    int getAsempListCnt(String area, String searchType, String searchValue);

    @Insert("""
            INSERT INTO AS_ENGINEERS
            (
            	NO
            	, AREA
            )
            VALUES
            (
            	#{no}
            	, #{area}
            )
            """)
    int enroll(AsempVo vo);

    @Select("""
            SELECT
                A.NO
                , E.EMP_NAME
                , A.AREA
                , E.PHONE
                , E.DEPT_NAME
                , E.POSITION_NAME
                , A.DEL_YN
            FROM AS_ENGINEERS A
            JOIN EMPLOYEE_INFO E ON (A.NO = E.NO)
            WHERE A.NO = #{no}
            AND A.DEL_YN='N'
            """)
    AsempVo getAsempDetail(String no, Model model);

    @Update("""
            UPDATE AS_ENGINEERS
            SET
                NO = #{no}
                , AREA = #{area}
            WHERE NO = #{no}
            """)
    int edit(AsempVo vo);

    @Delete("""
            DELETE FROM AS_ENGINEERS
            WHERE NO IN (${no})
            """)
    int delete(String no);

    @Select("""
            SELECT
                NO
                , EMP_NAME  AS NAME
                , PHONE
                , DEPT_NAME AS DNAME
                , POSITION_NAME AS PNAME
            FROM EMPLOYEE_INFO
            WHERE NO NOT IN (
                SELECT NO
                FROM AS_ENGINEERS
            )
            ORDER BY NO DESC
            """)
    List<EmployeeVo> getEmpList();

}
