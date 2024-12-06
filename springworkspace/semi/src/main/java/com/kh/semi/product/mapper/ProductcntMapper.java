package com.kh.semi.product.mapper;

import com.kh.semi.defective.vo.DefectiveVo;
import com.kh.semi.product.vo.ProductcntVo;
import com.kh.semi.util.page.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductcntMapper {

    @Select("""
            SELECT
                PR.ITEM_CODE,
                PR.NAME,
                PR.PRICE,
                (SELECT COUNT(*)
                 FROM PRODUCT_REGISTRATION
                 WHERE ITEM_CODE = PR.ITEM_CODE
                 AND DEL_YN = 'N') AS TOTAL_COUNT,
                (SELECT COUNT(*)
                 FROM DEFECTIVE_PRODUCT
                 WHERE ITEM_CODE = PR.ITEM_CODE) AS DEFECTIVE_COUNT,
                (CASE
                    WHEN (SELECT COUNT(*)
                          FROM PRODUCT_REGISTRATION
                          WHERE ITEM_CODE = PR.ITEM_CODE
                          AND DEL_YN = 'N') = 0 THEN 0
                    ELSE
                        (SELECT COUNT(*)
                         FROM DEFECTIVE_PRODUCT
                         WHERE ITEM_CODE = PR.ITEM_CODE) * 1.0 /
                        (SELECT COUNT(*)
                         FROM PRODUCT_REGISTRATION
                         WHERE ITEM_CODE = PR.ITEM_CODE
                         AND DEL_YN = 'N')
                 END) AS DEFECT_RATE
            FROM
                PRODUCT_REGISTRATION PR
            WHERE
                DEL_YN = 'N'
                ${str}
            GROUP BY
                PR.ITEM_CODE, PR.NAME, PR.PRICE
            ORDER BY
                PR.ITEM_CODE
            
            OFFSET #{pageVo.offset} ROWS FETCH NEXT #{pageVo.boardLimit} ROWS ONLY
            """)
    List<ProductcntVo> getProductList(String str, PageVo pageVo);

    @Select("""
            SELECT COUNT(DISTINCT(ITEM_CODE))
                        FROM PRODUCT_REGISTRATION
                        WHERE DEL_YN = 'N'
            """)
    int getProductPageCnt();


    @Select("""
            SELECT *
            FROM DEFECTIVE_PRODUCT DP
            JOIN PRODUCT_REGISTRATION PR ON(DP.P_NO = PR.NO)
            WHERE DP.DEL_YN = 'N'
            """)
    List<ProductcntVo> getVoList();
}
