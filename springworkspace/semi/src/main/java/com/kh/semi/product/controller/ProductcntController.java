package com.kh.semi.product.controller;

import com.kh.semi.defective.vo.DefectiveVo;
import com.kh.semi.product.service.ProductcntService;
import com.kh.semi.product.vo.ProductcntVo;
import com.kh.semi.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("qa/productcnt")
@RequiredArgsConstructor
public class ProductcntController {

    private final ProductcntService service;

    //상품 목록 조회
    @GetMapping("list")
    public void list(Model model,
                     @RequestParam(value = "searchValue", required = false) String searchValue,
                     @RequestParam(value = "searchValueCode", required = false) String searchValueCode,
                     @RequestParam(name = "pno", defaultValue = "1") int currentPage) {

        int listCount = service.getProductPageCnt();
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pageVo = new PageVo(listCount , currentPage, pageLimit, boardLimit);

        List<ProductcntVo> productcntVo = service.getproductCnt(searchValue, searchValueCode, pageVo);

        model.addAttribute("pageVo", pageVo);
        model.addAttribute("productcntVo", productcntVo);
    }


}
