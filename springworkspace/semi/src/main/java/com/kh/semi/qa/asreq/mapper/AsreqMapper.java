package com.kh.semi.qa.asreq.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.product.vo.ProductVo;
import com.kh.semi.qa.inspection.vo.ItemProductVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface AsreqMapper {

    @Insert("""
            INSERT INTO AS_REQUEST
            (
                NO
                , P_NO
                , CUSTOMER_NAME
                , CUSTOMER_AREA
                , CUSTOMER_ADRESS
                , CUSTOMER_PHONE
                , PURCHASE_DATE
                , WARRANTY_YN
                , ISSUE_TITLE
                , ISSUE_DESCRIPTION
                , PREFERRED_SERVICE_DATE
            )
            VALUES
            (
                SEQ_AS_REQUEST.NEXTVAL
                , #{productNo}
                , #{customerName}
                , #{customerArea}
                , #{customerAdress}
                , #{customerPhone}
                , #{purchaseDate}
                , #{warrantyYn}
                , #{issueTitle}
                , #{issueDescription}
                , #{preferredServiceDate}
            )
            """)
    int write(AsreqVo vo);

    List<AsreqVo> getAsreqList(Model model, PageVo pvo, String area, String searchType, String searchValue);

    int getAsreqListCnt(String area, String searchType, String searchValue);

    @Select("""
            SELECT
                A.NO
                , I.NO   AS ITEM_CODE
                , I.ITEM_NAME
                , A.P_NO AS PRODUCT_NO
                , P.SERIAL_NUMBER
                , P.NAME    AS PRODUCT_NAME
                , A.CUSTOMER_NAME
                , A.CUSTOMER_AREA
                , A.CUSTOMER_ADRESS
                , A.CUSTOMER_PHONE
                , A.PURCHASE_DATE
                , A.WARRANTY_YN
                , A.ISSUE_TITLE
                , A.ISSUE_DESCRIPTION
                , A.PREFERRED_SERVICE_DATE
                , A.STATUS_CODE
                , A.ENROLL_DATE
                , A.MODIFY_DATE
                , A.DEL_YN
            FROM AS_REQUEST A
            JOIN PRODUCT_REGISTRATION P ON (A.P_NO = P.NO)
            JOIN PRODUCT_INQUIRY I ON (P.ITEM_CODE = I.NO)
            WHERE A.NO = #{asreqNo}
            AND A.DEL_YN = 'N'
            """)
    AsreqVo getAsreqDetail(String asreqNo);

    @Update("""
            UPDATE AS_REQUEST
            SET
                P_NO = #{productNo}
                , CUSTOMER_NAME = #{customerName}
                , CUSTOMER_AREA = #{customerArea}
                , CUSTOMER_ADRESS = #{customerAdress}
                , CUSTOMER_PHONE = #{customerPhone}
                , PURCHASE_DATE = #{purchaseDate}
                , WARRANTY_YN = #{warrantyYn}
                , ISSUE_TITLE = #{issueTitle}
                , ISSUE_DESCRIPTION = #{issueDescription}
                , PREFERRED_SERVICE_DATE = #{preferredServiceDate}
                , MODIFY_DATE = SYSDATE
            WHERE NO = #{no}
            """)
    int edit(AsreqVo vo);

    @Update("""
            UPDATE AS_REQUEST
            SET
                DEL_YN = 'Y'
                , MODIFY_DATE = SYSDATE
            WHERE NO IN (${no})
            """)
    int delete(String no);

    @Update("""
            UPDATE AS_REQUEST
            SET
                STATUS_CODE = 2
                , MODIFY_DATE = SYSDATE
            WHERE NO = #{no}
            """)
    int receiveAsreq(String no);

    @Insert("""
            INSERT INTO AS_WORK
            (
                NO
                , AS_NO
            )
            VALUES
            (
                SEQ_AS_WORK.NEXTVAL
                , #{no}
            )
            """)
    int enrollAswork(String no);

    List<ItemProductVo> getProductList(PageVo pvo, String productSearchType, String productSearchValue);

    int getProductCnt(String productSearchType, String productSearchValue);
}
