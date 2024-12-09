package com.kh.semi.finance.sale.mapper;

import com.kh.semi.finance.sale.vo.SaleVo;
import com.kh.semi.pb.vo.PageVo;
import org.apache.ibatis.annotations.*;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface SaleMapper {

    @Insert("""
            INSERT INTO SALE
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
            SEQ_SALE.NEXTVAL
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
    int write(SaleVo vo);


//    @Select("""
//            SELECT
//                S.NO,
//                S.PARTNER_CODE,
//                S.TRANS_CODE,
//                S.ACCOUNT_CODE,
//                S.TRANS_DATE,
//                S.SUPPLY_AMOUNT,
//                S.TAX_AMOUNT,
//                TI.NAME AS TRANS_ITEM_NAME,
//                P.NAME AS PARTNER_NAME,
//                S.COMMENTS,
//                S.ENROLL_DATE,
//                S.MODIFY_DATE,
//                S.DEL_YN
//            FROM SALE S
//            JOIN PARTNER P
//                ON S.PARTNER_CODE = P.NO
//            JOIN TRANS_ITEM TI
//                ON S.TRANS_CODE = TI.NO
//            JOIN ACCOUNT A
//                ON S.ACCOUNT_CODE = A.NO
//            WHERE S.DEL_YN = 'N'
//            ORDER BY S.NO DESC
//            """)
//    @Select("""
//            SELECT
//                NO
//                , PARTNER_CODE
//                , TRANS_CODE
//                , ACCOUNT_CODE
//                , TRANS_DATE
//                , SUPPLY_AMOUNT
//                , TAX_AMOUNT
//                , ATTACHMENT
//                , COMMENTS
//                , ENROLL_DATE
//                , MODIFY_DATE
//                , DEL_YN
//            FROM SALE
//            WHERE DEL_YN = 'N'
//            ORDER BY NO DESC
//            """)
//    List<SaleVo> selectSaleVoList();

    @Update("""
            UPDATE SALE
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
    int edit(SaleVo vo);

    @Update("""
            UPDATE SALE
                SET
                    DEL_YN = 'Y'
                WHERE
                    NO = #{no}
            """)
    int delete(String no);


    @Select("""
            SELECT
                S.NO,
                S.PARTNER_CODE,
                S.TRANS_CODE,
                S.ACCOUNT_CODE,
                S.TRANS_DATE,
                S.SUPPLY_AMOUNT,
                S.TAX_AMOUNT,
                TI.NAME AS TRANS_ITEM_NAME,  -- TRANS_ITEM 테이블에서 거래항목명
                P.NAME AS PARTNER_NAME,  -- PARTNER 테이블에서 상호명
                S.COMMENTS,
                S.ENROLL_DATE,
                S.MODIFY_DATE,
                S.DEL_YN
            FROM SALE S
            JOIN PARTNER P
                ON S.PARTNER_CODE = P.NO
            JOIN TRANS_ITEM TI
                ON S.TRANS_CODE = TI.NO
            JOIN ACCOUNT A
                ON S.ACCOUNT_CODE = A.NO
            WHERE S.NO = #{no}
            AND S.DEL_YN = 'N'
            """)
    SaleVo getSaleDetail(String no, Model model);

    List<SaleVo> selectSaleVoList();


    @Select("SELECT COUNT(*) FROM SALE WHERE DEL_YN = 'N'")
    int getTotalSaleCount();

    @Select("""
    SELECT COUNT(*)
    FROM SALE
    WHERE DEL_YN = 'N'
      AND (
        (#{area} = '1' AND PARTNER_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '2' AND TRANS_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '3' AND ACCOUNT_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '4' AND TRANS_DATE LIKE '%' || #{searchValue} || '%')
      )
    """)
    int getSaleListCnt(@Param("area") String area, @Param("searchValue") String searchValue);

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
    FROM SALE
    WHERE DEL_YN = 'N'
    ORDER BY NO DESC
    OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
    """)
    List<SaleVo> getAllSales(PageVo pageVo);

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
    FROM SALE
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
    List<SaleVo> getSaleList(@Param("pageVo") PageVo pageVo, @Param("area") String area, @Param("searchValue") String searchValue);


    @Update("""
    <script>
        UPDATE SALE
        SET DEL_YN = 'Y'
        WHERE NO IN
        <foreach collection="saleIds" item="saleId" open="(" separator="," close=")">
            #{saleId}
        </foreach>
    </script>
    """)
    int deleteMultipleSales(@Param("saleIds") List<String> saleIds);


}
