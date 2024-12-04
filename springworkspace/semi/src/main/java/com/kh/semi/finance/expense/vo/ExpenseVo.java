package com.kh.semi.finance.expense.vo;

import lombok.Data;

@Data
public class ExpenseVo {
    private String no;
    private String partner_code;
    private String trans_code;
    private String account_code;
    private String trans_date;
    private String supply_amount;
    private String tax_amount;
    private String attachment;
    private String comments;
    private String enroll_date;
    private String modify_date;
    private String del_yn;
}
