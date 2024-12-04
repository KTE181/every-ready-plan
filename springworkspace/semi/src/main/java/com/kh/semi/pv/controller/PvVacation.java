package com.kh.semi.pv.controller;

import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pv.service.PvVacationService;
import com.kh.semi.pv.vo.PvVacationVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("pv")
@Slf4j
public class PvVacation {
    private final PvVacationService service;

    @GetMapping("vacation")
    public String list(Model model, HttpSession session,String date){

        System.out.println(date);

        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        if(loginEmployeeVo==null){
            session.setAttribute("alertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        String no = ((LoginVo) session.getAttribute("loginEmployeeVo")).getNo();
        List<PvVacationVo> voList=service.getVacationList(no,date);
        PvVacationVo vacationVo=service.getVacationVo(no);
        model.addAttribute("voList",voList);
        model.addAttribute("vacationVo",vacationVo);
        return "pv/vacation/list";
    }



}
