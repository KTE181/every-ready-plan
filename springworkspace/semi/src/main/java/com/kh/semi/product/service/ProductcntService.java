package com.kh.semi.product.service;

import com.kh.semi.product.mapper.ProductMapper;
import com.kh.semi.product.mapper.ProductcntMapper;
import com.kh.semi.product.vo.ProductcntVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductcntService {
    private final ProductcntMapper mapper;

    public List<ProductcntVo> getproductCnt() {
        return mapper.getproductCnt();
    }
}
