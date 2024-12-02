package com.kh.semi.product.vo;

import lombok.Data;

@Data
public class ProductVo {

    private String no;
    private String itemCode;
    private String name;
    private String serialNumber;
    private String quantity;
    private String price;
    private String warrantyPeriod;
    private String receivedDate;
    private String factoryName;
    private String factoryLocation;
    private String enrollDate;
    private String modifyDate;
    private String delYn;
}
