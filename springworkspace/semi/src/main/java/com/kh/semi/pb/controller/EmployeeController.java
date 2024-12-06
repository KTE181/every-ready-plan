package com.kh.semi.pb.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.service.EmployeeService;
import com.kh.semi.pb.vo.EmployeeVo;
import com.kh.semi.pb.vo.PageVo;
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
@Slf4j
public class EmployeeController {
    private final EmployeeService service;

    // 직원 목록 및 검색
    @GetMapping("employee")
    public String searchEmployees(
            @RequestParam(value = "department", required = false) String department,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1", required = false) int currentPage,
            Model model, HttpSession session) {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        System.out.println("Current Page: " + currentPage);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("Name: " + name);
        // 전체 데이터 수 (검색 조건 포함)
        int listCount = service.getEmployeeCountWithSearch(department, position, name);
        int pageLimit = 5;  // 하단 페이지 번호 개수
        int boardLimit = 7; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // 검색 조건 + 페이징 처리된 직원 목록 조회
        List<EmployeeVo> employeeVoList = service.searchEmployeesWithPaging(department, position, name, pageVo);

        // 모델에 데이터 전달
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("employeeVoList", employeeVoList);
        model.addAttribute("department", department);
        model.addAttribute("position", position);
        model.addAttribute("name", name);

        return "pb/employee/employee";
    }
}
