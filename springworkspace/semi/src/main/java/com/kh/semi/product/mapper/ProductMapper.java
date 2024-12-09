package com.kh.semi.product.mapper;

import com.kh.semi.itemcode.vo.ItemVo;
import com.kh.semi.product.vo.ProductVo;
import com.kh.semi.util.page.PageVo;
import org.apache.ibatis.annotations.*;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("""
            SELECT
                NO
                ,ITEM_CODE
                ,NAME
                ,PRICE
                ,SERIAL_NUMBER
                ,TO_CHAR(RECEIVED_DATE, 'YYYY-MM-DD') AS RECEIVED_DATE
                ,FACTORY_NAME
                ,ENROLL_DATE
            FROM PRODUCT_REGISTRATION
            WHERE DEL_YN = 'N'
            ${str}
            ORDER BY NO DESC
            OFFSET #{pvo.offset} ROWS FETCH NEXT #{pvo.boardLimit} ROWS ONLY
            """)
    List<ProductVo> getProductList(String str, PageVo pvo);



    @Insert("""
            INSERT INTO PRODUCT_REGISTRATION
                (  
                 NO
                ,ITEM_CODE
                ,PRICE
                ,NAME
                ,SERIAL_NUMBER
                ,FACTORY_NAME
                ,FACTORY_LOCATION
                ,WARRANTY_PERIOD
                ,RECEIVED_DATE
                )
                VALUES
                (
                SEQ_PRODUCT_REGISTRATION.NEXTVAL
                ,#{itemCode}
                ,#{price}
                ,#{name}
                ,SEQ_SERIAL_NUMBER.NEXTVAL
                ,#{factoryName}
                ,#{factoryLocation}
                ,#{warrantyPeriod}
                ,#{receivedDate}
                )
            """)
    int write(ProductVo vo);



    @Update("""
            UPDATE PRODUCT_REGISTRATION
                SET
                DEL_YN = 'Y'
                WHERE NO IN(${x})
            """)
    int delete(String x);


    @Update("""
            UPDATE PRODUCT_REGISTRATION
                SET
                ITEM_CODE = #{itemCode}
                ,NAME = #{name}
                ,PRICE = #{price}
                ,FACTORY_NAME = #{factoryName}
                ,FACTORY_LOCATION = #{factoryLocation}
                ,WARRANTY_PERIOD = #{warrantyPeriod}
                WHERE NO = #{no} AND DEL_YN = 'N'
            """)
    int edit(ProductVo vo);


    @Select("""
            SELECT
                 ITEM_CODE
                ,PRICE
                ,NAME
                ,(SELECT COUNT(ITEM_CODE)
                             FROM PRODUCT_REGISTRATION
                             WHERE ITEM_CODE = A.ITEM_CODE AND DEL_YN = 'N') AS QUANTITY
                ,SERIAL_NUMBER
                ,FACTORY_NAME
                ,FACTORY_LOCATION
                ,WARRANTY_PERIOD
                ,RECEIVED_DATE
                FROM PRODUCT_REGISTRATION A
                WHERE NO = #{no}
            """)
    ProductVo findByNo(String productNo);

    @Insert("""
            INSERT INTO PRODUCT_REGISTRATION
                (
                 NO
                ,ITEM_CODE
                ,NAME
                ,PRICE
                ,SERIAL_NUMBER
                ,FACTORY_NAME
                ,FACTORY_LOCATION
                ,WARRANTY_PERIOD
                ,RECEIVED_DATE
                )
                VALUES
                (
                    SEQ_PRODUCT_REGISTRATION.NEXTVAL,
                     #{itemCode},
                     #{name},
                     #{price},
                     '${itemCode}-' || SEQ_ITEM_${itemCode}.NEXTVAL,
                     #{factoryName},
                     #{factoryLocation},
                     #{warrantyPeriod},
                     #{receivedDate}
                )
            """)
    int insertProduct(ProductVo productvo);


    @Select("""
            SELECT
                 ITEM_CODE
                ,PRICE
                ,NAME
                ,(SELECT COUNT(ITEM_CODE)
                             FROM PRODUCT_REGISTRATION
                             WHERE ITEM_CODE = A.ITEM_CODE AND DEL_YN = 'N') AS QUANTITY
                ,SERIAL_NUMBER
                ,FACTORY_NAME
                ,FACTORY_LOCATION
                ,WARRANTY_PERIOD
                ,RECEIVED_DATE
                FROM PRODUCT_REGISTRATION A
                WHERE NO = #{productNo}
            """)
    ProductVo getProductDetail(String productNo);


    @Select("""
            SELECT COUNT(NO)
            FROM PRODUCT_REGISTRATION
            WHERE DEL_YN = 'N'
            """)
    int getBoardCnt();


    @Insert("""
            INSERT INTO PRODUCT_INQUIRY
            (
             NO
            ,ITEM_NAME
            )
            VALUES
            (
            SEQ_PRODUCT_INQUIRY.NEXTVAL
            ,#{itemName}
            )
            """)
    int insertItemCode(ItemVo vo);


    @Select("""
            SELECT *
            FROM PRODUCT_INQUIRY
            ORDER BY NO ASC
            """)
    List<ItemVo> getItemCodeVoList();


    @Select("""
            SELECT *
            FROM PRODUCT_INQUIRY
            WHERE NO = #{itemCode}
            """)
    ItemVo getItemCodeName(String itemCode);


}
