package com.kh.semi.defective.mapper;

import com.kh.semi.defective.vo.DefectiveCodeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
                WHERE NO = 1
            """)
    List<DefectiveCodeVo> getdcVoList();
}
