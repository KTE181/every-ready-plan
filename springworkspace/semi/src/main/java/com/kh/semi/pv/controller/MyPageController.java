package com.kh.semi.pv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    //마이페이지 화면
    @GetMapping("mypage")
    public String mypage(){
        return "pv/mypage/mypage";
    }
}
