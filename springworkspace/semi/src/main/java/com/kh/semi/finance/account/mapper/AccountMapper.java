package com.kh.semi.finance.account.mapper;

import com.kh.semi.finance.account.vo.AccountVo;
import com.kh.semi.pb.vo.PageVo;
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

//origin
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
//            ORDER BY ACCOUNT.NO DESC
//            """)
//    List<AccountVo> selectAccountVoList();

//페이징
//    @Select("""
//        SELECT
//            ACCOUNT.NO,
//            ACCOUNT.ACCOUNT_NAME,
//            ACCOUNT.ACCOUNT_NO,
//            ACCOUNT.BANK_CODE AS BANK_CODE,
//            BANK.NAME AS BANKNAME
//        FROM
//            ACCOUNT
//        JOIN
//            BANK
//        ON
//            ACCOUNT.BANK_CODE = BANK.NO
//        ORDER BY ACCOUNT.NO DESC
//        """)
//    List<AccountVo> selectAccountVoList(PageVo pageVo);

//    @Select("""
//            SELECT
//                ACCOUNT.NO,
//                ACCOUNT.ACCOUNT_NAME,
//                ACCOUNT.ACCOUNT_NO,
//                ACCOUNT.BANK_CODE AS BANK_CODE,
//                BANK.NAME AS BANKNAME
//            FROM
//                ACCOUNT
//            JOIN
//                BANK
//            ON
//                ACCOUNT.BANK_CODE = BANK.NO
//            ORDER BY ACCOUNT.NO DESC
//            LIMIT #{offset}, #{boardLimit}
//            """)
//    List<AccountVo> selectPagedAccounts(PageVo pageVo);
//
//    @Select("SELECT COUNT(*) FROM ACCOUNT")
//    int getAccountListCnt();

    @Select("""
    SELECT COUNT(*)
    FROM ACCOUNT
    JOIN BANK ON ACCOUNT.BANK_CODE = BANK.NO
    WHERE
        (#{area} = '1' AND ACCOUNT.BANK_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '2' AND BANK.NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '3' AND ACCOUNT.ACCOUNT_NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '4' AND ACCOUNT.ACCOUNT_NO LIKE '%' || #{searchValue} || '%')
    """)
    int getAccountListCnt(@Param("area") String area, @Param("searchValue") String searchValue);

    @Select("""
    SELECT
        ACCOUNT.NO,
        ACCOUNT.ACCOUNT_NAME,
        ACCOUNT.ACCOUNT_NO,
        ACCOUNT.BANK_CODE AS BANK_CODE,
        BANK.NAME AS BANKNAME
    FROM ACCOUNT
    JOIN BANK ON ACCOUNT.BANK_CODE = BANK.NO
    WHERE
        (#{area} = '1' AND ACCOUNT.BANK_CODE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '2' AND BANK.NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '3' AND ACCOUNT.ACCOUNT_NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '4' AND ACCOUNT.ACCOUNT_NO LIKE '%' || #{searchValue} || '%')
    ORDER BY ACCOUNT.NO DESC
    OFFSET #{pageVo.offset} ROWS FETCH NEXT #{pageVo.boardLimit} ROWS ONLY
    """)
    List<AccountVo> selectAccountVoList(
            @Param("pageVo") PageVo pageVo,
            @Param("area") String area,
            @Param("searchValue") String searchValue);

    @Select("SELECT COUNT(*) FROM ACCOUNT")
    int getTotalAccountCount();

    @Select("""
    SELECT
        ACCOUNT.NO,
        ACCOUNT.ACCOUNT_NAME,
        ACCOUNT.ACCOUNT_NO,
        ACCOUNT.BANK_CODE AS BANK_CODE,
        BANK.NAME AS BANKNAME
    FROM ACCOUNT
    JOIN BANK ON ACCOUNT.BANK_CODE = BANK.NO
    ORDER BY ACCOUNT.NO DESC
    OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
    """)
    List<AccountVo> getAllAccounts(PageVo pageVo);

    @Delete("""
            DELETE FROM ACCOUNT
            WHERE NO = #{no}
            """)
    int del(String no);

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



    @Delete("""
    <script>
        DELETE FROM ACCOUNT
        WHERE NO IN
        <foreach collection="accountIds" item="accountId" open="(" separator="," close=")">
            #{accountId}
        </foreach>
    </script>
    """)
    int deleteMultipleAccounts(List<String> accountIds);
}

