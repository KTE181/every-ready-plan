package com.kh.semi.qa.asemp.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asemp.vo.AsempVo;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.qa.asemp.vo.DeptVo;

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

    @Delete("""
            UPDATE AS_ENGINEERS
            SET DEL_YN = 'N'
            WHERE NO = #{no}
            """)
    int update(AsempVo vo);

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
    AsempVo getAsempDetail(String no);

    @Update("""
            UPDATE AS_ENGINEERS
            SET
                NO = #{no}
                , AREA = #{area}
            WHERE NO = #{no}
            """)
    int edit(AsempVo vo);

    @Delete("""
            UPDATE AS_ENGINEERS
            SET DEL_YN = 'Y'
            WHERE NO IN (${no})
            """)
    int delete(String no);

    List<EmployeeVo> getEmpList(PageVo pvo, String dept, String empSearchType, String empSearchValue);

    int getEmpCnt(String dept, String empSearchType, String empSearchValue);

    @Select("""
            SELECT
                NO
                , NAME
            FROM DEPARTMENT
            """)
    List<DeptVo> getDeptList(Model model);

    @Select("""
            SELECT COUNT(NO)
            FROM AS_ENGINEERS
            WHERE NO = #{no}
            """)
    int checkAsemp(AsempVo vo);
}
