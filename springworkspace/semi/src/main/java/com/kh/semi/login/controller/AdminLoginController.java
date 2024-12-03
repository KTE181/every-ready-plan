package com.kh.semi.login.controller;

import com.kh.semi.login.service.AdminLoginService;
import com.kh.semi.login.vo.AdminLoginVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminLoginController {

    private final AdminLoginService service;
    //로그인 (화면)
    @GetMapping("adminlogin")
    public String adminlogin(){
        return "login/adminlogin";
    }

    //로그인
    @PostMapping("adminlogin")
    public String login(AdminLoginVo vo, HttpSession session) throws Exception {
        //sql
        AdminLoginVo loginAdminVo= service.adminlogin(vo);
        //result
        if (loginAdminVo == null) {
            log.warn("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login/adminlogin"; // 로그인 실패 시 로그인 페이지로 다시 이동
        }
        // 로그인 성공 처리
        log.info("로그인 성공: {}", loginAdminVo);
        System.out.println(loginAdminVo);
        session.setAttribute("loginAdminVo", loginAdminVo); // 세션에 로그인 정보 저장
        return "redirect:/hr"; // 로그인 성공 후 메인 페이지로 리다이렉트
    }
}
