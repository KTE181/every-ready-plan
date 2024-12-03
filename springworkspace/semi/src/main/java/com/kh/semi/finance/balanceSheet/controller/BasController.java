package com.kh.semi.finance.balanceSheet.controller;

import com.kh.semi.finance.balanceSheet.service.BasService;
import com.kh.semi.finance.balanceSheet.vo.BasVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("finance/bas/")
public class BasController {
    private final BasService service;

    @GetMapping("detail")
    public String detail(String no , Model model){

        BasVo vo = service.getBasByNo(no);
        model.addAttribute("vo" , vo);
        return "finance/balanceSheet/detail";

    }
}
