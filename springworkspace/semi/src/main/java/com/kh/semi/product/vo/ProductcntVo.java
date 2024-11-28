package com.kh.semi.product.vo;

import lombok.Data;

@Data
public class ProductcntVo {
    private String itemCode;
    private String name;
    private String price;
    private String count;
    private String nondefectivecount;
}
