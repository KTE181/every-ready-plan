package com.kh.semi.finance.incstat.controller;

import com.kh.semi.finance.incstat.service.IncService;
import com.kh.semi.finance.incstat.vo.*;
import com.kh.semi.login.vo.LoginVo;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("finance/inc/")
public class IncController {

    private final IncService service;

    @GetMapping("detail")
    public String detail(String no, Model model , HttpSession session){
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        if(loginEmployeeVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        IncVo vo = service.getIncByNo(no);
        model.addAttribute("vo", vo);
        return "finance/incStat/detail";
    }


}