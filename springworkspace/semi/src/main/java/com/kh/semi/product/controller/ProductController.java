package com.kh.semi.product.controller;

import com.kh.semi.product.service.ProductService;
import com.kh.semi.product.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("qa/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("list")
    public void list(Model model){
        List<ProductVo> productVo = service.getProductList();
        model.addAttribute("productVo",productVo);

    }

    @GetMapping("detail")
    public String detail(Model model){
        List<ProductVo> productVo = service.getProductDetail();
        model.addAttribute("productVo", productVo);
        return "qa/product/detail";
    }

    @GetMapping("write")
    public String write(ProductVo vo){
        int result = service.write(vo);

        if(result == 1){
            return "redirect:/qa/product/list";
        }else{
            return "redirect:/error";
        }
    }




}
