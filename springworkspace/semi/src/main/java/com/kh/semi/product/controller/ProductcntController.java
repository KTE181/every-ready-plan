package com.kh.semi.product.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.product.service.ProductcntService;
import com.kh.semi.product.vo.ProductcntVo;
import com.kh.semi.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
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
    public String list(Model model,
                       @RequestParam(value = "searchValue", required = false) String searchValue,
                       @RequestParam(value = "searchValueCode", required = false) String searchValueCode,
                       @RequestParam(name = "pno", defaultValue = "1") int currentPage,
                       HttpSession session) {

        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        int listCount = service.getProductPageCnt();
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pageVo = new PageVo(listCount , currentPage, pageLimit, boardLimit);

        List<ProductcntVo> productcntVo = service.getproductCnt(searchValue, searchValueCode, pageVo);

        model.addAttribute("pageVo", pageVo);
        model.addAttribute("productcntVo", productcntVo);

        return "qa/productcnt/list";
    }


}
