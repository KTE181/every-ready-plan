package com.kh.semi.product.service;

import com.kh.semi.product.mapper.ProductMapper;
import com.kh.semi.product.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;


    public List<ProductVo> getProductList() {
        return mapper.getProductList();
    }


    public int write(ProductVo vo) {
        return mapper.write(vo);
    }


    public List<ProductVo> getProductDetail(String bno, Model model) {
        return mapper.getProductDetail(bno, model);
    }

    public int edit(ProductVo vo) {
        return mapper.edit(vo);
    }

    public int delete(List<String> productNoList) {
        String x = String.join("," , productNoList);
        System.out.println("x = " + x);
        return mapper.delete(x);
    }
}
