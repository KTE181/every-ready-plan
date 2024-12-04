package com.kh.semi.qa.inspection.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.inspection.vo.InspectionTypeVo;
import com.kh.semi.qa.inspection.vo.InspectionVo;
import com.kh.semi.qa.inspection.vo.InspectionStatusVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface InspectionMapper {


    List<InspectionVo> getInspectionList(Model model, PageVo pvo, String inspectionType, String status, String passYn, String searchType, String searchValue);

    int getInspectionListCnt(String inspectionType, String status, String passYn, String searchType, String searchValue);

    @Insert("""
            INSERT INTO INSPECTION_HISTORY
            (
                NO
                , PRODUCT_NO
                , INSPECTION_CODE
                , SUCCESS_YN
                , STATUS_CODE
                , INSPECTION_DATE
            )
            VALUES
            (
                SEQ_INSPECTION_HISTORY.NEXTVAL
                , #{productNo}
                , #{inspectionCode}
                , #{successYn}
                , #{statusCode}
                , #{inspectionDate}
            )
            """)
    int write(InspectionVo vo);

    @Select("""
            SELECT
                I.NO
                , I.PRODUCT_NO
                , P.NAME    AS PRODUCT_NAME
                , P.SERIAL_NUMBER
                , I.INSPECTION_CODE
                , C.NAME    AS INSPECTION_NAME
                , TO_DATE(I.INSPECTION_DATE, 'YYYY-MM-DD') AS INSPECTION_DATE
                , CASE  WHEN (I.SUCCESS_YN = 'P') THEN 'PASS'
                    WHEN (I.SUCCESS_YN = 'F') THEN 'FAIL'
                    END AS SUCCESS_YN
                , I.STATUS_CODE
                , S.NAME    AS STATUS_NAME
                , I.ENROLL_DATE
                , I.MODIFY_DATE
                , I.DEL_YN
            FROM INSPECTION_HISTORY I
            JOIN PRODUCT_REGISTRATION P ON (I.PRODUCT_NO = P.NO)
            JOIN INSPECTION_TYPE C ON (I.INSPECTION_CODE = C.NO)
            JOIN INSPECTION_STATUS S ON (I.STATUS_CODE = S.NO)
            WHERE I.NO = #{no}
            """)
    InspectionVo getinspectionDetail(String no, Model model);

    @Update("""
            UPDATE INSPECTION_HISTORY
            SET     
                DEL_YN = 'Y'
                , MODIFY_DATE = SYSDATE
            WHERE NO IN (${no})
            """)
    int delete(String no);

    @Update("""
            UPDATE INSPECTION_HISTORY
            SET    
                PRODUCT_NO = #{productNo}
                , INSPECTION_CODE = #{inspectionCode}
                , SUCCESS_YN = #{successYn}
                , STATUS_CODE = #{statusCode}
                , MODIFY_DATE = SYSDATE
                , INSPECTION_DATE = #{inspectionDate}
            WHERE NO = #{no}
            """)
    int edit(InspectionVo vo);

    @Select("""
            SELECT 
                NO
                , NAME
            FROM INSPECTION_STATUS
            """)
    List<InspectionStatusVo> getStatusList(Model model);

    @Select("""
            SELECT
                NO
                , NAME
            FROM INSPECTION_TYPE
            """)
    List<InspectionTypeVo> getInspectionTypeList(Model model);


}
