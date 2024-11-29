package com.kh.semi.defective.mapper;

import com.kh.semi.defective.vo.DefectiveVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
                    WHERE P.SERIAL_NUMBER = #{serialNumber}
            """)
    List<DefectiveVo> getDefectiveDetail(String bno, Model model);


    @Insert("""
            INSERT INTO DEFECTIVE_PRODUCT
                (
                 NO
                ,P_NO
                ,DEFECTIVE_CODE
                ,DESCRIPTION
                )
                VALUES
                (
                SEQ_DEFECTIVE_PRODUCT.NEXTVAL
                ,#{no}
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
}
