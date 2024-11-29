package com.kh.semi.defective.mapper;

import com.kh.semi.defective.vo.DefectiveCodeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface DefectiveCodeMapper {

    @Select("""
            SELECT
                NO,
                NAME
                FROM DEFECTIVE_CODE
                ORDER BY NO ASC
            """)
    List<DefectiveCodeVo> getDefectiveCode();

    @Select("""
            SELECT
                NO
                ,NAME
                FROM DEFECTIVE_CODE
                WHERE NO = #{no}
            """)
    List<DefectiveCodeVo> getdcVoList(String bno, Model model);



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
                WHERE NO IN (#{no})
            """)
    int delete(String bno);



    @Update("""
            UPDATE DEFECTIVE_CODE
                SET
                NAME = #{name}
                WHERE NO = #{no}
            """)
    int edit(DefectiveCodeVo vo);
}
