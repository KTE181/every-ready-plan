package com.kh.semi.finance.balanceSheet.mapper;

import com.kh.semi.finance.balanceSheet.vo.BasVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BasMapper {


    @Select("""
            SELECT
                NO
                , YEAR
                , DEPOSIT
                , PREPAID_TAX
                , MERCHADISE
                , RAW_MATERIAL
                , WITHHOLDINGS
                , WITHHELD_TAX
                , CAPITAL_STOCK
            FROM BALANCESHEET
            WHERE NO = '4'
            """)
    BasVo getBasByNo(String no);
}
