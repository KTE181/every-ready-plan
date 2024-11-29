package com.kh.semi.product.mapper;

import com.kh.semi.product.vo.ProductVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
                ,RECEIVED_DATE
                ,FACTORY_NAME
                ,ENROLL_DATE
            FROM PRODUCT_REGISTRATION
            WHERE DEL_YN = 'N'
            ORDER BY NO DESC
            """)
    List<ProductVo> getProductList();


    @Select("""
            SELECT
                 ITEM_CODE
                ,PRICE
                ,NAME
                ,SERIAL_NUMBER
                ,FACTORY_NAME
                ,FACTORY_LOCATION
                ,WARRANTY_PERIOD
                ,RECEIVED_DATE
                FROM PRODUCT_REGISTRATION
                WHERE ITEM_CODE = #{bno}
            """)
    List<ProductVo> getProductDetail(String bno, Model model);

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
                PRICE = #{price}
                ,FACTORY_NAME = #{factoryName}
                ,FACTORY_LOCATION = #{factoryLocation}
                ,WARRANTY_PERIOD = #{warrantyPeriod}
                ,RECEIVED_DATE = #{receivedDate}
                WHERE NO = #{no}
            """)
    int edit(ProductVo vo);
}
