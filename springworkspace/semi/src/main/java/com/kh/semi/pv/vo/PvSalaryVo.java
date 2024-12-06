package com.kh.semi.pv.vo;

import lombok.Data;

@Data
public class PvSalaryVo {
    private String no; // 순번
    private String payYearmonth; //지급연월
    private String empNo;//사번
    private String salary;// 연봉
    private String ename;//사원명
    private String dname; // 부서명
    private String deptCode;//부서코드
    private String pname; // 직급
    private String basic; // 기본급
    private String mealAllowance; //식대
    private String communicationCost; //통신비
    private String payment; //지급액
    private String nationalPension; // 국민연금
    private String healthInsurance; // 건강보험
    private String employmentInsurance; //고용보험
    private String longtermCareInsurance; //장기 요양 보험
    private String incomeTax; //소득세
    private String localTaxes; //지방세
    private String deductions;//공제총액
    private String netPayment;// 실수령액


}
