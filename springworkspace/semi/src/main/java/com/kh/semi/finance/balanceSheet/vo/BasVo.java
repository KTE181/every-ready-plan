package com.kh.semi.finance.balanceSheet.vo;

import lombok.Data;

@Data
public class BasVo {

    private String no;
    private String year;
    private String deposit;
    private String prepaidTax;
    private String merchadise;
    private String rawMaterial;
    private String withholdings;
    private String withheldTax;
    private String capitalStock;
}
