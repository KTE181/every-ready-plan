package com.kh.semi.pb.controller;

import com.kh.semi.login.vo.LoginVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrganizationChartController {
    @GetMapping("organization")
    public String organization(HttpSession session) {
        // 세션에서 로그인 정보 확인
//        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
//        // 로그인 정보가 없으면 로그인 페이지로 이동
//        if (loginEmployeeVo == null) {
//            return "redirect:/login"; // redirect를 사용해 로그인 페이지로 이동
//        }
        // 로그인 정보가 있으면 organizationChart 페이지로 이동
        return "pb/organization/organizationChart";
    }
}
