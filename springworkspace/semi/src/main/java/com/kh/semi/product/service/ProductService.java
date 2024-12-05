package com.kh.semi.product.service;

import com.kh.semi.product.mapper.ProductMapper;
import com.kh.semi.product.vo.ProductVo;
import com.kh.semi.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductMapper mapper;


    public List<ProductVo> getProductList(String searchValue, String searchValueName, PageVo pageVo) {
        StringBuilder str = new StringBuilder();

        // 동적 조건 추가

        if (searchValueName != null && !searchValueName.isEmpty()) {
            str.append("AND NAME LIKE '%").append(searchValueName).append("%' ");
        }

        if (searchValue != null && !searchValue.isEmpty()) {
            str.append("AND SERIAL_NUMBER LIKE '%").append(searchValue).append("%' ");
        }


        return mapper.getProductList(str.toString(), pageVo);
    }



//    public int write(ProductVo vo) {
//        return mapper.write(vo);
//    }



    public int edit(ProductVo vo) {
        return mapper.edit(vo);
    }

    public int delete(List<String> productNoList) {
        String x = String.join("," , productNoList);
        System.out.println("x = " + x);
        return mapper.delete(x);
    }

    public ProductVo getProductByNo(String productNo) {
        return mapper.findByNo(productNo);
    }


    public boolean insertProduct(ProductVo productvo) {
        int result = mapper.insertProduct(productvo);
        return result > 0;
    }

    public int write(ProductVo vo) {
        return mapper.insertProduct(vo);
    }


    public ProductVo getProductDetail(String productNo) {
        return mapper.getProductDetail(productNo);
    }

    public int getBoardCnt() {
        return mapper.getBoardCnt();
    }
}
