package com.kh.semi.pb.controller;

import com.kh.semi.pb.service.EmployeeService;
import com.kh.semi.pb.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService service;

    // 직원 목록 및 검색
    @GetMapping("employee")
    public String searchEmployees(
            @RequestParam(value = "department", required = false) String department,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "name", required = false) String name,
            Model model) {

        List<EmployeeVo> employeeVoList = service.searchEmployees(department, position, name);

        model.addAttribute("employeeVoList", employeeVoList);
        return "pb/employee/employee";
    }
}
