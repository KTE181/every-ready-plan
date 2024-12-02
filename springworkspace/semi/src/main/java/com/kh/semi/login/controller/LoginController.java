package com.kh.semi.login.controller;

import com.kh.semi.login.service.LoginService;
import com.kh.semi.login.vo.LoginVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService service;
    //로그인 화면
    @GetMapping("login")
    public String login() {
        return "login/login";}

    //로그인
    @PostMapping("login")
    public String login(LoginVo vo, HttpSession session) throws Exception {
        //sql
        LoginVo loginEmployeeVo= service.login(vo);
        //result
        if (loginEmployeeVo == null) {
            log.warn("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login/login"; // 로그인 실패 시 로그인 페이지로 다시 이동
        }
        // 로그인 성공 처리
        log.info("로그인 성공: {}", loginEmployeeVo);
        session.setAttribute("loginEmployeeVo", loginEmployeeVo); // 세션에 로그인 정보 저장
        return "redirect:/pb"; // 로그인 성공 후 메인 페이지로 리다이렉트
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 loginEmployeeVo 제거
        session.removeAttribute("loginEmployeeVo");
        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }
}
