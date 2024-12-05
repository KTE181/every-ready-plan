package com.kh.semi.defective.mapper;

import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface DefectiveCodeMapper {

    @Insert("""
            INSERT INTO DEFECTIVE_CODE
                (
                NO
                ,NAME
                )
                VALUES
                (
                SEQ_DEFECTIVE_CODE.NEXTVAL
                ,#{name}
                )
            """)
    int write(DefectiveCodeVo vo);


    @Update("""
            UPDATE DEFECTIVE_CODE
                SET
                DEL_YN = 'Y'
                WHERE NO IN (${x})
            """)
    int delete(String x);



    @Update("""
            UPDATE DEFECTIVE_CODE
                SET
                NAME = #{name}
                WHERE NO = #{no}
            """)
    int edit(DefectiveCodeVo vo);


    @Select("""
            SELECT
                NO
                ,NAME
                FROM DEFECTIVE_CODE
                WHERE NO = #{no}
            """)
    DefectiveCodeVo findByNo(String defectiveCodeNo);


    @Select("""
            SELECT
                NO,
                NAME
                FROM DEFECTIVE_CODE
                WHERE DEL_YN = 'N'
                ${str}
                ORDER BY NO ASC
                OFFSET #{pageVo.offset} ROWS FETCH NEXT #{pageVo.boardLimit} ROWS ONLY
            """)
    List<DefectiveCodeVo> getDefectiveCodeList(String str, PageVo pageVo);



    @Select("""
            SELECT
                NO
                ,NAME
                FROM DEFECTIVE_CODE
                WHERE NO = #{defectiveCodeNo}
            """)
    DefectiveCodeVo getDefectiveCodeDetail(String defectiveCodeNo);


    @Select("""
            SELECT COUNT(NO)
            FROM DEFECTIVE_CODE
            WHERE DEL_YN = 'N'
            """)
    int getDefectiveCodeCnt();
}
