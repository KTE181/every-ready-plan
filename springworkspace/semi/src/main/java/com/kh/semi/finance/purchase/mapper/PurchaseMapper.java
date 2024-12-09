package com.kh.semi.finance.purchase.mapper;

import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.finance.purchase.vo.PurchaseVo;
import com.kh.semi.pb.vo.PageVo;
import org.apache.ibatis.annotations.*;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface PurchaseMapper {

    @Insert("""
            INSERT INTO PURCHASE
            (
            NO
            ,PARTNER_CODE
            ,TRANS_CODE
            ,ACCOUNT_CODE
            ,TRANS_DATE
            ,SUPPLY_AMOUNT
            ,TAX_AMOUNT
            ,ATTACHMENT
            ,COMMENTS
            )
            VALUES
            (
            SEQ_PURCHASE.NEXTVAL
            , #{partnerCode}
            , #{transCode}
            , #{accountCode}
            , #{transDate}
            , #{supplyAmount}
            , #{taxAmount}
            , #{attachment}
            , #{comments}
            )
            """)
    int write(PurchaseVo vo);




    @Update("""
            UPDATE PURCHASE
                SET
                    DEL_YN = 'Y'
                WHERE
                    NO = #{no}
            """)
    int delete(String no);


    @Update("""
            UPDATE PURCHASE
                SET
                    PARTNER_CODE = #{partnerCode},
                    TRANS_CODE = #{transCode},
                    ACCOUNT_CODE = #{accountCode},
                    TRANS_DATE = #{transDate},
                    SUPPLY_AMOUNT = #{supplyAmount},
                    TAX_AMOUNT = #{taxAmount},
                    COMMENTS = #{comments},
                    MODIFY_DATE = SYSDATE
                WHERE
                    NO = #{no}
            """)
    int edit(PurchaseVo vo);

    @Select("""
            SELECT
                S.NO,
                S.PARTNER_CODE,
                S.TRANS_CODE,
                S.ACCOUNT_CODE,
                S.TRANS_DATE,
                S.SUPPLY_AMOUNT,
                S.TAX_AMOUNT,
                TI.NAME AS TRANS_ITEM_NAME,
                P.NAME AS PARTNER_NAME,
                S.COMMENTS,
                S.ENROLL_DATE,
                S.MODIFY_DATE,
                S.DEL_YN
            FROM PURCHASE S
            JOIN PARTNER P
                ON S.PARTNER_CODE = P.NO
            JOIN TRANS_ITEM TI
                ON S.TRANS_CODE = TI.NO
            JOIN ACCOUNT A
                ON S.ACCOUNT_CODE = A.NO
            WHERE S.NO = #{no}
            AND S.DEL_YN = 'N'
            """)
    PurchaseVo getPurchaseDetail(String no, Model model);

    @Update("""
    <script>
        UPDATE PURCHASE
        SET DEL_YN = 'Y'
        WHERE NO IN
        <foreach collection="purchaseIds" item="purchaseId" open="(" separator="," close=")">
            #{purchaseId}
        </foreach>
    </script>
    """)
    int deleteMultiplePurchases(@Param("purchaseIds") List<String> purchaseIds);


    
    
    @Select("SELECT COUNT(*) FROM PURCHASE WHERE DEL_YN = 'N'")
    int getPurchaseListCnt(@Param("area") String area, @Param("searchValue") String searchValue);


    @Select("""
    SELECT COUNT(*)
    FROM PURCHASE
    WHERE DEL_YN = 'N'
      AND (
        (#{area} = '1' AND PARTNER_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '2' AND TRANS_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '3' AND ACCOUNT_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '4' AND TRANS_DATE LIKE '%' || #{searchValue} || '%')
      )
    """)
    int getTotalPurchaseCount();


    @Select("""
    SELECT
        NO,
        PARTNER_CODE,
        TRANS_CODE,
        ACCOUNT_CODE,
        TRANS_DATE,
        SUPPLY_AMOUNT,
        TAX_AMOUNT,
        COMMENTS
    FROM purchase
    WHERE DEL_YN = 'N'
    ORDER BY NO DESC
    OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
    """)
    List<PurchaseVo> getAllPurchases(PageVo pageVo);


    @Select("""
    SELECT
        NO,
        PARTNER_CODE,
        TRANS_CODE,
        ACCOUNT_CODE,
        TRANS_DATE,
        SUPPLY_AMOUNT,
        TAX_AMOUNT,
        COMMENTS
    FROM purchase
    WHERE DEL_YN = 'N'
      AND (
        (#{area} = '1' AND PARTNER_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '2' AND TRANS_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '3' AND ACCOUNT_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '4' AND TRANS_DATE LIKE '%' || #{searchValue} || '%')
      )
    ORDER BY NO DESC
    OFFSET #{pageVo.offset} ROWS FETCH NEXT #{pageVo.boardLimit} ROWS ONLY
    """)
    List<PurchaseVo> getPurchaseList(@Param("pageVo") PageVo pageVo, @Param("area") String area, @Param("searchValue") String searchValue);

    @Select("""
    SELECT *
    FROM (
        SELECT 
            ROWNUM AS RN,
            NO, PARTNER_CODE, TRANS_CODE, ACCOUNT_CODE, SUPPLY_AMOUNT, TAX_AMOUNT, TRANS_DATE, COMMENTS
        FROM PURCHASE
        WHERE DEL_YN = 'N'
        AND (
            (#{area} = '1' AND PARTNER_CODE LIKE '%' || #{searchValue} || '%') OR
            (#{area} = '2' AND TRANS_CODE LIKE '%' || #{searchValue} || '%') OR
            (#{area} = '3' AND ACCOUNT_CODE LIKE '%' || #{searchValue} || '%') OR
            (#{area} = '4' AND TRANS_DATE LIKE '%' || #{searchValue} || '%') OR
            (#{area} IS NULL OR #{area} = '')
        )
        ORDER BY NO DESC
    )
    WHERE RN BETWEEN #{pageVo.offset} + 1 AND #{pageVo.offset} + #{pageVo.boardLimit}
    """)
    List<PurchaseVo> selectPurchaseVoList(@Param("pageVo") PageVo pageVo, @Param("area") String area, @Param("searchValue") String searchValue);


}
