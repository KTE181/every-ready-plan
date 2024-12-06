package com.kh.semi.hr.salary.mapper;

import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.hr.employeehr.vo.SearchVo;
import com.kh.semi.hr.salary.vo.SalaryVo;
import com.kh.semi.pb.vo.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SalaryMapper {


         List<SalaryVo> listAll(PageVo pvo, SearchVo searchVo);


    //사원정보 가져오기
    @Select("""
            SELECT
                E.NO,
            PROFILE_IMAGE,
            PWD,
            E.NAME,
            BIRTH,
            GENDER,
            EMAIL,
            PHONE,
            EMERGENCY_PHONE,
            ADDRESS,
            DEPT_CODE,
            D.NAME AS DNAME,
            POSITION_CODE,
            P.NAME AS PNAME,
            SALARY,
            BANK_CODE,
            ACCOUNT_NO,
            TOTAL_VACATION_DAYS,
            STATUS_CODE,
            ES.NAME AS ESNAME,
            ENTER_DATE,
            OUT_DATE,
            ENROLL_DATE,
            MODIFY_DATE,
            DEL_YN
            FROM EMPLOYEE  E
            JOIN DEPARTMENT D ON(E.DEPT_CODE = D.NO)
            JOIN POSITION P ON(E.POSITION_CODE = P.NO)
            JOIN EMP_STATUS ES ON(E.STATUS_CODE =ES.NO)
            WHERE E.NO=#{empNo} AND  E.DEL_YN='N' 
            """)
    EmployeeVo selectvolist(String empNo);
    @Select("""
            
                        SELECT
                        E.NO,
                        PROFILE_IMAGE,
                        PWD,
                        E.NAME,
                        BIRTH,
                        GENDER,
                        EMAIL,
                        PHONE,
                        EMERGENCY_PHONE,
                        ADDRESS,
                        DEPT_CODE,
                        D.NAME AS DNAME,
                        POSITION_CODE,
                        P.NAME AS PNAME,
                        SALARY,
                        BANK_CODE,
                        ACCOUNT_NO,
                        TOTAL_VACATION_DAYS,
                        STATUS_CODE,
                        ES.NAME AS ESNAME,
                        ENTER_DATE,
                        OUT_DATE,
                        ENROLL_DATE,
                        MODIFY_DATE,
                        DEL_YN
                        FROM EMPLOYEE  E
                        JOIN DEPARTMENT D ON(E.DEPT_CODE = D.NO)
                        JOIN POSITION P ON(E.POSITION_CODE = P.NO)
                        JOIN EMP_STATUS ES ON(E.STATUS_CODE =ES.NO)
                        WHERE E.DEL_YN='N'
                        ORDER BY E.NO DESC
                        OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
                        """)
    List<EmployeeVo> empVoList(PageVo pvo2);

    @Insert("""
            INSERT INTO SALARY(
                NO,
                EMP_NO,
                PAY_YEARMONTH,
                BASIC,
                MEAL_ALLOWANCE,
                COMMUNICATION_COST,
                NATIONAL_PENSION,
                HEALTH_INSURANCE,
                EMPLOYMENT_INSURANCE,
                LONGTERM_CARE_INSURANCE,
                INCOME_TAX,
                LOCAL_TAXES
            )VALUES(
                SEQ_SALARY.NEXTVAL,
                #{empNo},
                #{payYearmonth},
                #{basic} ,
                #{mealAllowance} ,
                #{communicationCost} ,
                #{nationalPension} ,
                #{healthInsurance} ,
                #{employmentInsurance} ,
                #{longtermCareInsurance} ,
                #{incomeTax} ,
                #{localTaxes}
            )
            """)
    int write(SalaryVo vo);

    @Select("""
            SELECT\s
                A.NO ,
                B.SALARY,
                A.PAY_YEARMONTH ,
                A.EMP_NO ,
                B.NAME ENAME,
                D.NAME DNAME,
                C.NAME PNAME,
                A.BASIC ,
                A.MEAL_ALLOWANCE ,
                A.COMMUNICATION_COST ,
                (A.BASIC+A.MEAL_ALLOWANCE+A.COMMUNICATION_COST) AS PAYMENT,
                A.NATIONAL_PENSION ,
                A.HEALTH_INSURANCE ,
                A.EMPLOYMENT_INSURANCE ,
                A.LONGTERM_CARE_INSURANCE ,
                A.INCOME_TAX ,
                A.LOCAL_TAXES ,
                (A.NATIONAL_PENSION+A.HEALTH_INSURANCE+A.EMPLOYMENT_INSURANCE+A.LONGTERM_CARE_INSURANCE+A.INCOME_TAX+A.LOCAL_TAXES)AS DEDUCTIONS,
                ((A.BASIC+A.MEAL_ALLOWANCE+A.COMMUNICATION_COST) - (A.NATIONAL_PENSION+A.HEALTH_INSURANCE+A.EMPLOYMENT_INSURANCE+A.LONGTERM_CARE_INSURANCE+A.INCOME_TAX+A.LOCAL_TAXES)) AS NETPAYMENT
                FROM SALARY A
                JOIN EMPLOYEE B ON (A.EMP_NO = B.NO)
                JOIN POSITION C ON (B.POSITION_CODE= C.NO)
                JOIN DEPARTMENT D ON (B.DEPT_CODE = D.NO)
                WHERE A.NO=#{selectNo} AND A.DEL_YN = 'N'
            """)
    SalaryVo detail(String selectNo);

    @Update("""
            UPDATE SALARY
            SET
            PAY_YEARMONTH = #{payYearmonth},
            NATIONAL_PENSION=#{nationalPension},
            COMMUNICATION_COST=#{communicationCost},
            MEAL_ALLOWANCE=#{mealAllowance},
            HEALTH_INSURANCE = #{healthInsurance},
            EMPLOYMENT_INSURANCE = #{employmentInsurance},
            LONGTERM_CARE_INSURANCE=#{longtermCareInsurance},
            INCOME_TAX=#{incomeTax},
            LOCAL_TAXES = #{localTaxes}
            WHERE NO = #{no} AND DEL_YN='N'
            """)
    int edit(SalaryVo editdata);

    @Update("""
            UPDATE SALARY
            SET
            DEL_YN='Y'
            WHERE NO =#{no}
            """)
    int delete(String no);

    int editAll(String[] dataArr);

    @Select("""
            SELECT COUNT(NO)
            FROM SALARY
            ORDER BY NO DESC
            """)
    int getSalaryCnt();

    @Select("""
            SELECT COUNT(NO)
            FROM EMPLOYEE
            ORDER BY NO DESC
            """)
    int getEmpCnt();


    @Select("""
             SELECT
                        E.NO,
                        PROFILE_IMAGE,
                        PWD,
                        E.NAME,
                        BIRTH,
                        GENDER,
                        EMAIL,
                        PHONE,
                        EMERGENCY_PHONE,
                        ADDRESS,
                        DEPT_CODE,
                        D.NAME AS DNAME,
                        POSITION_CODE,
                        P.NAME AS PNAME,
                        SALARY,
                        BANK_CODE,
                        ACCOUNT_NO,
                        TOTAL_VACATION_DAYS,
                        STATUS_CODE,
                        ES.NAME AS ESNAME,
                        ENTER_DATE,
                        OUT_DATE,
                        ENROLL_DATE,
                        MODIFY_DATE,
                        DEL_YN
                        FROM EMPLOYEE  E
                        JOIN DEPARTMENT D ON(E.DEPT_CODE = D.NO)
                        JOIN POSITION P ON(E.POSITION_CODE = P.NO)
                        JOIN EMP_STATUS ES ON(E.STATUS_CODE =ES.NO)
                        WHERE E.DEL_YN='N'
                        ORDER BY E.NO DESC
                        OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
            """)
    List<EmployeeVo> getEmplistdata(PageVo pvo);

    EmployeeVo selectEmpVo(String searchEmpNo, String searchEname);
}
