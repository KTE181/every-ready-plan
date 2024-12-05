package com.kh.semi.defective.mapper;

import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.defective.vo.DefectiveVo;
import com.kh.semi.product.vo.ProductVo;
import com.kh.semi.util.page.PageVo;
import org.apache.ibatis.annotations.*;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface DefectiveMapper {

    @Select("""
            SELECT
                 D.NO
                ,P.ITEM_CODE
                ,P.NAME AS PRODUCT_NAME
                ,P.SERIAL_NUMBER
                ,D.DEFECTIVE_CODE
                ,P.RECEIVED_DATE
                ,P.FACTORY_LOCATION
                ,P.ENROLL_DATE
                FROM DEFECTIVE_PRODUCT D
                JOIN PRODUCT_REGISTRATION P ON(D.P_NO = P.NO)
                WHERE D.DEL_YN = 'N'
                ORDER BY ENROLL_DATE DESC
            """)
    List<DefectiveVo> getDefective();





    @Insert("""
            INSERT INTO DEFECTIVE_PRODUCT
                (
                 NO
                ,P_NO
                ,DEFECTIVE_NO
                ,DEFECTIVE_CODE
                ,DESCRIPTION
                )
                VALUES
                (
                SEQ_DEFECTIVE_PRODUCT.NEXTVAL
                ,#{no}
                ,#{defectiveNo}
                ,#{defectiveCode}
                ,#{description}
                )
            """)
    int write(DefectiveVo vo);


    @Update("""
            UPDATE DEFECTIVE_PRODUCT
                SET
                DEL_YN = 'Y'
                WHERE NO IN(${x})
            """)
    int delete(String x);



    @Update("""
            UPDATE DEFECTIVE_PRODUCT
                SET
                DEFECTIVE_CODE = #{defectiveCode}
               ,DESCRIPTION = #{description}
                WHERE NO = #{no}
                AND DEL_YN = 'N'
            """)
    int edit(DefectiveVo vo);


    @Select("""
            SELECT
                     DP.NO
                    ,P.PRICE
                    ,P.NAME AS PRODUCT_NAME
                    ,DP.DEFECTIVE_CODE
                    ,P.SERIAL_NUMBER
                    ,DC.NAME AS DEFECTIVE_NAME
                    ,DP.DESCRIPTION
                    FROM PRODUCT_REGISTRATION P
                    JOIN DEFECTIVE_PRODUCT DP ON (P.NO = DP.P_NO)
                    JOIN DEFECTIVE_CODE DC ON (DP.DEFECTIVE_CODE = DC.NO)
                    WHERE DP.NO = #{no}
            """)
    DefectiveVo getDefectiveByNo(String defectiveNo);


    @Select("""
            SELECT
                 D.NO
                ,P.ITEM_CODE
                ,P.NAME AS PRODUCT_NAME
                ,P.SERIAL_NUMBER
                ,D.DEFECTIVE_CODE
                ,P.RECEIVED_DATE
                ,P.FACTORY_LOCATION
                ,P.ENROLL_DATE
                FROM DEFECTIVE_PRODUCT D
                JOIN PRODUCT_REGISTRATION P ON(D.P_NO = P.NO)
                WHERE D.DEL_YN = 'N'
                ${str}
                ORDER BY ENROLL_DATE DESC
                OFFSET #{pageVo.offset} ROWS FETCH NEXT #{pageVo.boardLimit} ROWS ONLY
            """)
    List<DefectiveVo> getProductList(String str, PageVo pageVo);



    @Select("""
            SELECT
                     DP.NO
                    ,P.PRICE
                    ,P.NAME AS PRODUCT_NAME
                    ,DP.DEFECTIVE_CODE
                    ,P.SERIAL_NUMBER
                    ,DC.NAME AS DEFECTIVE_NAME
                    ,DP.DESCRIPTION
                    FROM PRODUCT_REGISTRATION P
                    JOIN DEFECTIVE_PRODUCT DP ON (P.NO = DP.P_NO)
                    JOIN DEFECTIVE_CODE DC ON (DP.DEFECTIVE_CODE = DC.NO)
                    WHERE DP.NO = #{defectiveNo}
            """)
    DefectiveVo getDefectiveDetail(String defectiveNo);

    @Select("""
            SELECT *
            FROM DEFECTIVE_CODE
            """)
    List<DefectiveCodeVo> getdefectiveCodeVoList();



    @Select("""
            SELECT *
            FROM DEFECTIVE_CODE
            WHERE NO = #{defectiveCode}
            """)
    DefectiveCodeVo getDefectiveName(String defectiveCode);


    @Select("""
            SELECT COUNT(NO)
            FROM DEFECTIVE_PRODUCT
            WHERE DEL_YN = 'N'
            """)
    int getDefectiveCnt();
}
