package com.kh.semi.hr.employeehr.controller;

import com.kh.semi.hr.employeehr.service.EmployeeHrService;
import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmployeeHrController {

    private final EmployeeHrService service;

    @GetMapping("employeehr")
    public String getEmployeeList(
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dname", required = false) String dname,
            @RequestParam(value = "pname", required = false) String pname,
            @RequestParam(value = "esname", required = false) String esname,
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            Model model
    ) {
        // 전체 직원 수 조회 (검색 조건 포함)
        int listCount = service.getEmployeeCount(no, name, dname, pname, esname);

        // 페이징 정보 계산
        int pageLimit = 5; // 하단 페이지 번호 최대 개수
        int boardLimit = 12; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // 직원 리스트 조회
        List<EmployeeVo> employeeList = service.searchEmployee(no, name, dname, pname, esname, pageVo);

        // 모델에 데이터 전달
        model.addAttribute("employeeVoList", employeeList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("no", no);
        model.addAttribute("name", name);
        model.addAttribute("dname", dname);
        model.addAttribute("pname", pname);
        model.addAttribute("esname", esname);

        return "hr/employeehr/employeehr"; // JSP 페이지로 이동
    }
    @PostMapping("/employeehr/delete")
    public ResponseEntity<String> deleteEmployees(@RequestBody List<String> ids) {
        try {
            // 리스트를 "1,2,3" 형식의 문자열로 변환
            String idString = String.join(",", ids);

            // 서비스에 전달
            service.deleteEmployees(idString);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            log.error("삭제 중 오류 발생", e);
            return ResponseEntity.status(500).body("삭제 실패");
        }
    }




}
