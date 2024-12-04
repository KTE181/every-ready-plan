package com.kh.semi.finance.account.mapper;

import com.kh.semi.finance.account.vo.AccountVo;
import org.apache.ibatis.annotations.*;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface AccountMapper {


    @Insert("""
            INSERT INTO ACCOUNT
            (
            NO
            ,BANK_CODE
            ,ACCOUNT_NAME
            ,ACCOUNT_NO
            )
            VALUES
            (
            SEQ_ACCOUNT.NEXTVAL
            , #{bankCode}
            , #{accountName}
            , #{accountNo}
            )
            """)
    int write(AccountVo vo);


    @Select("""
            SELECT
                ACCOUNT.NO,
                ACCOUNT.ACCOUNT_NAME,
                ACCOUNT.ACCOUNT_NO,
                ACCOUNT.BANK_CODE AS BANK_CODE,
                BANK.NAME AS BANKNAME
            FROM
                ACCOUNT ACCOUNT
            JOIN
                BANK BANK
            ON
                ACCOUNT.BANK_CODE = BANK.NO
            ORDER BY ACCOUNT.NO DESC
            """)
    List<AccountVo> selectAccountVoList();

    @Delete("""
            DELETE FROM ACCOUNT
            WHERE NO = #{no}
            """)
    int del(String no);

//    @Select("""
//            SELECT
//                ACCOUNT.NO,
//                ACCOUNT.ACCOUNT_NAME,
//                ACCOUNT.ACCOUNT_NO,
//                ACCOUNT.BANK_CODE AS BANK_CODE,
//                BANK.NAME AS BANKNAME
//            FROM
//                ACCOUNT ACCOUNT
//            JOIN
//                BANK BANK
//            ON
//                ACCOUNT.BANK_CODE = BANK.NO
//            WHERE BANK.NO = #{no}
//            """)
//    AccountVo getAccountDetail();


    @Update("""
            UPDATE ACCOUNT
            SET
            BANK_CODE = #{bankCode}
            ,ACCOUNT_NAME = #{accountName}
            ,ACCOUNT_NO = #{accountNo}
            WHERE NO = #{no}
            """)
    int edit(AccountVo vo);

    @Select("""
            SELECT
                ACCOUNT.NO,
                ACCOUNT.ACCOUNT_NAME,
                ACCOUNT.ACCOUNT_NO,
                ACCOUNT.BANK_CODE AS BANK_CODE,
                BANK.NAME AS BANKNAME
            FROM
                ACCOUNT ACCOUNT
            JOIN
                BANK BANK
            ON
                ACCOUNT.BANK_CODE = BANK.NO
            WHERE ACCOUNT.NO = #{no}
            """)
    AccountVo getAccountDetail(String no, Model model);
}
