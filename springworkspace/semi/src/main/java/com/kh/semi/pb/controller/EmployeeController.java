package com.kh.semi.pb.controller;

import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.service.EmployeeService;
import com.kh.semi.pb.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService service;

    //사원 목록조회
    @GetMapping("employee")
    public String historyMap(HttpSession session, Model model) {
        // 세션에서 로그인 정보 확인
//        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
//        // 로그인 정보가 없으면 로그인 페이지로 이동
//        if (loginEmployeeVo == null) {
//            return "redirect:/login"; // redirect를 사용해 로그인 페이지로 이동
//        }

        List<EmployeeVo> employeeVoList = service.employeeVoList();

        model.addAttribute("employeeVoList", employeeVoList);
        System.out.println("Employee List: " + employeeVoList);
        // 로그인 정보가 있으면 historyMap 페이지로 이동
        return "pb/employee/employee";
    }
}
