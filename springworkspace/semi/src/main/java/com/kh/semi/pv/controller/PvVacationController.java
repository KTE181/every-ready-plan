package com.kh.semi.pv.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
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
@RequestMapping("pv/vacation")
@Slf4j
public class PvVacationController {
    private final PvVacationService service;

    @GetMapping("list")
    public String list(Model model, HttpSession session,String date,@RequestParam(name = "pno" , required = false, defaultValue = "1")int currentPage){


        String no = "";
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        int listCount = service.getVacationCnt(no);
        int pageLimit = 5;
        int boardLimit = 14;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
        if(loginEmployeeVo!=null){
            no= ((LoginVo) session.getAttribute("loginEmployeeVo")).getNo();
            List<PvVacationVo> voList=service.getVacationList(no,date,pvo);
            PvVacationVo vacationVo=service.getVacationVo(no);
            model.addAttribute("voList",voList);
            model.addAttribute("vacationVo",vacationVo);
        }


        model.addAttribute("pvo",pvo);

        return "pv/vacation/list";
    }



}
