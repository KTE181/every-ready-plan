package com.kh.semi.finance.expense.vo;

import lombok.Data;

@Data
public class ExpenseVo {
    private String no;
    private String partnerCode;
    private String transCode;
    private String accountCode;
    private String transDate;
    private String supplyAmount;
    private String taxAmount;
    private String attachment;
    private String comments;
    private String enrollDate;
    private String modifyDate;
    private String delYn;
}
