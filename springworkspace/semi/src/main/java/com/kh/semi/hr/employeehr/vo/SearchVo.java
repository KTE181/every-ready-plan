package com.kh.semi.hr.employeehr.vo;

import lombok.Data;

@Data
public class SearchVo {
    private String searchMonth;
    private String searchYear;
    private String searchOption;
    private String searchType;
    private String searchValue;
}
