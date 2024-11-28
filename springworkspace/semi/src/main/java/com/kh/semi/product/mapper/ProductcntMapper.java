package com.kh.semi.product.mapper;

import com.kh.semi.product.vo.ProductcntVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductcntMapper {


    @Select("""
            SELECT ITEM_CODE,NAME,PRICE,COUNT(ITEM_CODE)AS COUNT,(COUNT(ITEM_CODE)-(SELECT COUNT(B.P_NO)
            FROM PRODUCT_REGISTRATION A JOIN DEFECTIVE_PRODUCT B ON (A.NO = B.P_NO)
            WHERE A.NO =1
            GROUP BY ITEM_CODE,NAME,PRICE)) AS nondefectivecount
            FROM PRODUCT_REGISTRATION
            WHERE DEL_YN='N'
            GROUP BY ITEM_CODE,NAME,PRICE
            ORDER BY ITEM_CODE
            """)
    List<ProductcntVo> getproductCnt();
}
