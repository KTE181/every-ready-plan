package com.kh.semi.finance.incstat.mapper;

import com.kh.semi.finance.incstat.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IncMapper {

    @Select("""
            SELECT
                NO
                ,YEAR
                ,SALES_MERCHANDISE
                ,SALES_GOODS
                ,SALARIES_EXPENSE
                ,TRAVEL_EXPENSE
                ,TAXES_DUES
                ,SUPPLIES_EXPENSE
                ,ADVERTISING
            FROM INCOMESTATEMENT
            """)
    List<IncVo> getIncVoList();


    @Select("""
            SELECT
                NO
                ,YEAR
                ,SALES_MERCHANDISE
                ,SALES_GOODS
                ,SALARIES_EXPENSE
                ,TRAVEL_EXPENSE
                ,TAXES_DUES
                ,SUPPLIES_EXPENSE
                ,ADVERTISING
            FROM INCOMESTATEMENT
            WHERE NO = '4'
            """)
    IncVo getIncByNo(String no);
}
