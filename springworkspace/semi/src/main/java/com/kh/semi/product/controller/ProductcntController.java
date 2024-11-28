package com.kh.semi.product.controller;

import com.kh.semi.product.service.ProductcntService;
import com.kh.semi.product.vo.ProductcntVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("qa/productcnt")
@RequiredArgsConstructor
public class ProductcntController {

    private final ProductcntService service;

    @GetMapping("list")
    public void list(Model model){
        List<ProductcntVo> productcntVo = service.getproductCnt();
        model.addAttribute("productcntVo",productcntVo);
    }


}
