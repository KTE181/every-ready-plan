package com.kh.semi.qa.aswork.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.aswork.vo.AsworkStatusVo;
import com.kh.semi.qa.aswork.vo.AsworkVo;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface AsworkMapper {

    List<AsworkVo> getAsworkList(Model model, PageVo pvo, String area, String status, String type, String searchType, String searchValue);

    int getAsworkListCnt(String area, String status, String type, String searchType, String searchValue);

    @Select("""
            SELECT
                W.NO
                , W.AS_NO   
                , R.P_NO    AS PRODUCT_NO 
                , P.SERIAL_NUMBER  
                , P.NAME    AS PRODUCT_NAME        
                , R.CUSTOMER_NAME
                , R.CUSTOMER_AREA
                , R.CUSTOMER_ADRESS
                , R.CUSTOMER_PHONE
                , R.PURCHASE_DATE
                , R.WARRANTY_YN
                , R.ISSUE_TITLE
                , R.ISSUE_DESCRIPTION
                , R.PREFERRED_SERVICE_DATE
                , R.STATUS_CODE
                , S.NAME    AS STATUS_NAME
                , W.FAULT_CODE
                , F.NAME    AS FAULT_NAME
                , W.EMP_NO          
                , E.NAME    AS EMP_NAME
                , TO_DATE (W.REPAIR_DATE, 'YYYY-MM-DD') AS REPAIR_DATE
                , W.REPAIR_DETALIS
                , W.ENROLL_DATE
                , W.MODIFY_DATE
                , W.DEL_YN
            FROM AS_WORK W
            JOIN AS_REQUEST R ON (W.AS_NO = R.NO)
            JOIN PRODUCT_REGISTRATION P ON (R.P_NO = P.NO)
            JOIN AS_STATUS S ON (R.STATUS_CODE = S.NO)
            LEFT JOIN EMPLOYEE E ON (W.EMP_NO = E.NO)
            LEFT JOIN FAULT_CODE F ON (W.FAULT_CODE = F.NO)
            WHERE W.NO = #{asworkNo}
            AND W.DEL_YN = 'N'
            """)
    AsworkVo getAsworkDetail(String asworkNo, Model model);

    @Update("""
            UPDATE AS_WORK
            SET
                FAULT_CODE = #{faultCode}
                , EMP_NO = #{empNo}
                , REPAIR_DATE = #{repairDate}
                , REPAIR_DETALIS = #{repairDetalis}
                , MODIFY_DATE = SYSDATE
            WHERE NO = #{no}
            """)
    int edit(AsworkVo vo);

    @Update("""
            UPDATE AS_WORK
            SET
                DEL_YN = 'Y'
                , MODIFY_DATE = SYSDATE
            WHERE NO IN (${no})
            """)
    int delete(String no);

    @Select("""
            SELECT 
                NO
                , NAME
            FROM AS_STATUS
            WHERE NO != 1
            """)
    List<AsworkStatusVo> getStatusList(Model model);

    @Select("""
            SELECT
                NO
                , NAME AS FAULT_NAME
            FROM FAULT_CODE 
            """)
    List<FaultcodeVo> getTypeList(Model model);
}
