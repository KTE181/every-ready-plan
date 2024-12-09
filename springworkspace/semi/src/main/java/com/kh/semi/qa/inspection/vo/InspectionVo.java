package com.kh.semi.qa.inspection.vo;

import lombok.Data;

@Data
public class InspectionVo {
    private String no;
    private String itemCode;
    private String itemName;
    private String productNo;
    private String productName;
    private String serialNumber;
    private String inspectionCode;
    private String inspectionName;
    private String inspectionDate;
    private String successYn;
    private String statusCode;
    private String statusName;
    private String enrollDate;
    private String modifyDate;
    private String delYn;
}