package com.kh.semi.product.service;

import com.kh.semi.defective.vo.DefectiveVo;
import com.kh.semi.product.mapper.ProductMapper;
import com.kh.semi.product.mapper.ProductcntMapper;
import com.kh.semi.product.vo.ProductcntVo;
import com.kh.semi.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductcntService {
    private final ProductcntMapper mapper;


    public List<ProductcntVo> getproductCnt(String searchValue, String searchValueCode, PageVo pageVo) {
        StringBuilder str = new StringBuilder();

        // 동적 조건 추가
        if (searchValue != null && !searchValue.isEmpty()) {
            str.append("AND NAME LIKE '%").append(searchValue).append("%' ");
        }
        if (searchValueCode != null && !searchValueCode.isEmpty()) {
            str.append("AND ITEM_CODE LIKE '%").append(searchValueCode).append("%' ");
        }

        return mapper.getProductList(str.toString(), pageVo);
    }


    public int getProductPageCnt() {
        return mapper.getProductPageCnt();
    }
}
