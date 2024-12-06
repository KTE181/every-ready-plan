package com.kh.semi.product.vo;

import lombok.Data;

@Data
public class ProductcntVo {
    private String itemCode;
    private String name;
    private String price;
    private String count;
    private String totalCount;
    private String defectRate;
    private String defectiveCount;
    private int defectiveCnt;

}
