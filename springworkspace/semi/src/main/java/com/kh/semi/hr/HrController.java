package com.kh.semi.hr;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HrController {

    @GetMapping("hr")
    public String phr(HttpSession session){
        // 세션에서 로그인 정보 확인
        AdminLoginVo loginAdminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");

        // 로그인 정보가 없으면 로그인 페이지로 이동
//        if (loginEmployeeVo == null) {
//            return "redirect:/login"; // redirect를 사용해 로그인 페이지로 이동
//        }
        return "hr/hr";
    }
}
