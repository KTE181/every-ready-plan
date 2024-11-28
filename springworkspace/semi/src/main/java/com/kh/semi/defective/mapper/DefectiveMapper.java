package com.kh.semi.defective.mapper;

import com.kh.semi.defective.vo.DefectiveVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DefectiveMapper {

    @Select("""
            SELECT
                 D.NO
                ,P.ITEM_CODE
                ,P.NAME
                ,P.SERIAL_NUMBER
                ,D.DEFECTIVE_CODE
                ,P.RECEIVED_DATE
                ,P.FACTORY_LOCATION
                ,P.ENROLL_DATE
                FROM DEFECTIVE_PRODUCT D
                JOIN PRODUCT_REGISTRATION P ON(D.P_NO = P.NO)
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
                    WHERE P.SERIAL_NUMBER = 20241120001
            """)
    List<DefectiveVo> getDefectiveDetail();
}
