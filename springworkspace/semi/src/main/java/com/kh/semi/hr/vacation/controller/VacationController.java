package com.kh.semi.hr.vacation.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.vacation.service.VacationService;
import com.kh.semi.hr.vacation.vo.VacationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/hr/vacation")
public class VacationController {
    private final VacationService service;

    //게시글 작성 화면 연결


    @PostMapping("write")
    public String write(VacationVo vo) {

        int result = service.insert(vo);

        return "redirect:/api/hr/vacation/list";
    }


    //////사원 선택 버튼클릭후  모달창에 있는 정보를 받아오는 ajax
    @PostMapping("getEmployeeData")
    @ResponseBody
    public EmployeeVo getEmployeeData(String empNo) {

        System.out.println("empNo======" + empNo);

        System.out.println(empNo);

        EmployeeVo selectvolist = service.selectvolist(empNo);
        return selectvolist;
    }


    @GetMapping("list")
    public String list(Model model) {
        List<VacationVo> listVo = service.list();
        List<EmployeeVo> empVoList = service.empVoList();

        for (VacationVo vo : listVo) {
            System.out.println("VacationVo  =======" +vo);
        }
        for (EmployeeVo vo2 : empVoList) {
            System.out.println("vo2====="+vo2);
        }

        model.addAttribute("vacationListVo", listVo);

        model.addAttribute("empVoList", empVoList);

        return "hr/vacation/list";
    }


}