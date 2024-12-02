package com.kh.semi.asemp.mapper;

import com.kh.semi.asemp.vo.AsempVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface AsempMapper {

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
            WHERE A.DEL_YN='N'
            ORDER BY NO DESC
            """)
    List<AsempVo> getAsempList(Model model);

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
}
