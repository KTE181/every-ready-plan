package com.kh.semi.finance.balanceSheet.controller;

import com.kh.semi.finance.balanceSheet.service.BasService;
import com.kh.semi.finance.balanceSheet.vo.BasVo;
import com.kh.semi.login.vo.LoginVo;
import jakarta.servlet.http.HttpSession;
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
    public String detail(String no , Model model , HttpSession session){

        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        if(loginEmployeeVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        
        BasVo vo = service.getBasByNo(no);
        model.addAttribute("vo" , vo);
        return "finance/balanceSheet/detail";

    }
}
