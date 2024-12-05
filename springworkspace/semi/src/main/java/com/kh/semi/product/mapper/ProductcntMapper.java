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
                COUNT(PR.ITEM_CODE) AS TOTAL_COUNT,
                COUNT(CASE WHEN DP.P_NO IS NULL THEN PR.ITEM_CODE END) AS NON_DEFECTIVE_COUNT,
                COUNT(CASE WHEN DP.P_NO IS NOT NULL THEN PR.ITEM_CODE END) AS DEFECTIVE_COUNT,
                ROUND((COUNT(CASE WHEN DP.P_NO IS NOT NULL THEN PR.ITEM_CODE END) * 1.0 / COUNT(PR.ITEM_CODE)),2) AS DEFECT_RATE
            FROM
                PRODUCT_REGISTRATION PR
            LEFT JOIN
                DEFECTIVE_PRODUCT DP
            ON
                PR.NO = DP.P_NO
            WHERE
                PR.DEL_YN = 'N'
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
}
