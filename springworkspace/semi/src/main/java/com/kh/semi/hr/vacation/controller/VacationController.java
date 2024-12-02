package com.kh.semi.hr.vacation.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.vacation.service.VacationService;
import com.kh.semi.hr.vacation.vo.VacationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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



        model.addAttribute("vacationListVo", listVo);

        model.addAttribute("empVoList", empVoList);

        return "hr/vacation/list";
    }

    //휴가 상세조회
    @PostMapping("detail")
    @ResponseBody
    public VacationVo detail(String selectNo){
         VacationVo voList = service.getVacationVo(selectNo);
        return voList;
    }


    //휴가 수정
    @PostMapping("update")
    @ResponseBody
    public String update(@RequestBody Map<String, String> alldata){
        System.out.println(alldata);
        for (String key : alldata.keySet()) {
            String value = alldata.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
        int result= service.update(alldata);

        if(result != 1){
            throw new IllegalStateException("업데이트 실패했다~~");
        }
        return "good";
    }
    @PostMapping("del")
    @ResponseBody
    public int del(String no){
        System.out.println(no);

        int result = service.delete(no);

        return result;
    }
    @DeleteMapping("del")
    @ResponseBody
    public String del(@RequestBody String[] dataArr){
//        for (String s : dataArr) {
//            System.out.println(s);
//        }
        int result = service.editAll(dataArr);
        return "통신성공";
    }

}