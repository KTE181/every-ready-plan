package com.kh.semi.finance.incstat.controller;

import com.kh.semi.finance.incstat.service.IncService;
import com.kh.semi.finance.incstat.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("fi/inc/")
public class IncController {

    private final IncService service;

    @GetMapping("detail")
    public String detail(String no, Model model){
        IncVo vo = service.getIncByNo(no);
        model.addAttribute("vo", vo);
        return "finance/incStat/detail";
    }


}