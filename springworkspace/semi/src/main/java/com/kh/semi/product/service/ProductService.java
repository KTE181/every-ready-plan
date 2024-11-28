package com.kh.semi.product.service;

import com.kh.semi.product.mapper.ProductMapper;
import com.kh.semi.product.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;


    public List<ProductVo> getProductList() {
        return mapper.getProductList();
    }

    public List<ProductVo> getProductDetail() {
        return mapper.getProductDetail();
    }

    @Transactional
    public int write(ProductVo vo) {
        return mapper.write(vo);
    }
}
