package com.kh.semi.finance.account.mapper;

import com.kh.semi.finance.account.vo.AccountVo;
import org.apache.ibatis.annotations.*;

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
            """)
    List<AccountVo> selectAccountVoList();

    @Delete("""
            DELETE FROM ACCOUNT
            WHERE NO = #{no}
            """)
    int del(String no);

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
            WHERE BANK.NO = #{no}
            """)
    AccountVo getAccountDetail();

//    @Update("""
//           Update
//            """)
//    int edit(AccountVo vo);

}
