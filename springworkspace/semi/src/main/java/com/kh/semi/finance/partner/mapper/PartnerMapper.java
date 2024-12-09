package com.kh.semi.finance.partner.mapper;

import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.pb.vo.PageVo;
import org.apache.ibatis.annotations.*;
import org.springframework.ui.Model;

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
            #{no}
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
//            WHERE P.DEL_YN = 'N'
//            ORDER BY P.NO DESC
//            """)
//    List<PartnerVo> getPartnerList();

    @Select("SELECT COUNT(*) FROM PARTNER WHERE DEL_YN = 'N'")
    int getTotalPartnerCount();

    @Select("""
    SELECT COUNT(*)
    FROM PARTNER P
    JOIN BUSINESS_TYPE BT ON P.BUSINESS_CODE = BT.NO
    WHERE P.DEL_YN = 'N'
      AND (
        (#{area} = '1' AND P.NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '2' AND P.BIZ_REGIST_NO LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '3' AND P.CEO_NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '4' AND BT.BIZ_TYPE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '5' AND P.BUSINESS_CODE LIKE '%' || #{searchValue} || '%')
      )
    """)
    int getPartnerListCnt(@Param("area") String area, @Param("searchValue") String searchValue);

    @Select("""
    SELECT
        P.NO,
        P.NAME,
        P.BIZ_REGIST_NO,
        P.CEO_NAME,
        BT.BIZ_TYPE,
        P.BUSINESS_CODE
    FROM PARTNER P
    JOIN BUSINESS_TYPE BT ON P.BUSINESS_CODE = BT.NO
    WHERE P.DEL_YN = 'N'
    ORDER BY P.NO DESC
    OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
    """)
    List<PartnerVo> getAllPartners(PageVo pageVo);

    @Select("""
    SELECT
        P.NO,
        P.NAME,
        P.BIZ_REGIST_NO,
        P.CEO_NAME,
        BT.BIZ_TYPE,
        P.BUSINESS_CODE
    FROM PARTNER P
    JOIN BUSINESS_TYPE BT ON P.BUSINESS_CODE = BT.NO
    WHERE P.DEL_YN = 'N'
      AND (
        (#{area} = '1' AND P.NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '2' AND P.BIZ_REGIST_NO LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '3' AND P.CEO_NAME LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '4' AND BT.BIZ_TYPE LIKE '%' || #{searchValue} || '%')
        OR (#{area} = '5' AND P.BUSINESS_CODE LIKE '%' || #{searchValue} || '%')
      )
    ORDER BY P.NO DESC
    OFFSET #{pageVo.offset} ROWS FETCH NEXT #{pageVo.boardLimit} ROWS ONLY
    """)
    List<PartnerVo> getPartnerList(@Param("pageVo") PageVo pageVo, @Param("area") String area, @Param("searchValue") String searchValue);

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
            WHERE P.NO = #{no}
            AND DEL_YN ='N'
            """)
    PartnerVo getPartnerDetail(String no, Model model);


    @Update("""
        UPDATE PARTNER
        SET
            DEL_YN = 'Y'
        WHERE NO = #{no}
        """)
    int delete(String no);

    @Update("""
            UPDATE PARTNER
            SET
                BUSINESS_CODE = #{businessCode}
                ,NAME = #{name}
                ,BIZ_REGIST_NO = #{bizRegistNo}
                ,CEO_NAME = #{ceoName}
                ,ADDRESS = #{address}
            WHERE NO = #{no}
            """)
    int edit(PartnerVo vo);

    @Update("""
    <script>
        UPDATE PARTNER
        SET DEL_YN = 'Y'
        WHERE NO IN
        <foreach collection="partnerIds" item="partnerId" open="(" separator="," close=")">
            #{partnerId}
        </foreach>
    </script>
    """)
    int deleteMultiplePartners(List<String> partnerIds);
}
