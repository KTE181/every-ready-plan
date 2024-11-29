package com.kh.semi.finance.partner.mapper;

import com.kh.semi.finance.partner.vo.PartnerVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PartnerMapper {

    @Insert("""
            INSERT INTO PARTNER
            (
            NO
            ,BUSINESS_CODE
            ,NAME
            ,BIZ_REGIST_NO
            ,CEO_NAME
            ,ADDRESS
            )
            VALUES
            (
            SEQ_BOARD.NEXTVAL
            , #{businessCode}
            , #{name}
            , #{bizRegistNo}
            , #{ceoName}
            , #{address}
            )
            
            """)
    int write(PartnerVo vo);

//    @Select("""
//            SELECT
//                P.NO,
//                P.BUSINESS_CODE,
//                P.NAME,
//                P.BIZ_REGIST_NO,
//                P.CEO_NAME,
//                P.ADDRESS,
//                P.DEL_YN,
//                BT.BIZ_TYPE,
//                BT.BIZ_CATEGORY
//            FROM PARTNER P
//            JOIN BUSINESS_TYPE BT
//                ON P.BUSINESS_CODE = BT.NO
//            ORDER BY P.NO DESC;
//
//            """)
//    List<PartnerVo> getPartnerVoList();

    @Select("""
            SELECT
                P.NO,
                P.BUSINESS_CODE,
                P.NAME,
                P.BIZ_REGIST_NO,
                P.CEO_NAME,
                P.ADDRESS,
                P.DEL_YN,
                BT.BIZ_TYPE,
                BT.BIZ_CATEGORY
            FROM PARTNER P
            JOIN BUSINESS_TYPE BT
                ON P.BUSINESS_CODE = BT.NO
            ORDER BY P.NO DESC
            """)
    List<PartnerVo> getPartnerList();
}
