package com.kh.semi.qa.faultcode.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface FaultcodeMapper {

    List<FaultcodeVo> getFaultCodeList(Model model, PageVo pvo, String searchType, String searchValue);

    int getFaultCodeListCnt(String searchType, String searchValue);

    @Select("""
            SELECT
                NO
                , NAME  AS FAULT_NAME
            FROM FAULT_CODE
            WHERE NO = #{no}
            AND DEL_YN = 'N'
            """)
    FaultcodeVo getFaultCodeDetail(String no);

    @Insert("""
            INSERT INTO FAULT_CODE
            (
                NO,
                NAME
            )
            VALUES
            (
                SEQ_FAULT_CODE.NEXTVAL
                , #{faultName}
            )
            """)
    int enroll(FaultcodeVo vo);

    @Update("""
            UPDATE FAULT_CODE
            SET NAME = #{faultName}
            WHERE NO = #{no}
            """)
    int edit(String no, String faultName);

    @Update("""
            UPDATE FAULT_CODE
            SET DEL_YN = 'Y'
            WHERE NO IN (${no})
            """)
    int delete(String no);

}
