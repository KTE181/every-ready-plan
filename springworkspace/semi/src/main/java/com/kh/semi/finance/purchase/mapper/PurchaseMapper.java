package com.kh.semi.finance.purchase.mapper;

import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.finance.purchase.vo.PurchaseVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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



    @Select("""
            SELECT
                NO
                , PARTNER_CODE
                , TRANS_CODE
                , ACCOUNT_CODE
                , TRANS_DATE
                , SUPPLY_AMOUNT
                , TAX_AMOUNT
                , ATTACHMENT
                , COMMENTS
                , ENROLL_DATE
                , MODIFY_DATE
                , DEL_YN
            FROM PURCHASE
            WHERE DEL_YN = 'N'
            ORDER BY NO DESC
            """)
    List<PurchaseVo> selectPurchaseVoList();



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
}
