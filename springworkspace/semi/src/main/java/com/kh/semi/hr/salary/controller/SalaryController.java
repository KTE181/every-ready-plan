package com.kh.semi.hr.salary.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.salary.service.SalaryService;
import com.kh.semi.hr.salary.vo.SalaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/hr/salary")
public class SalaryController {

    private final SalaryService service;

    @GetMapping("list")
    public String listAll(Model model){

        List<SalaryVo> SalaryVoList = service.listAll();
        List<EmployeeVo> empVoList = service.empVoList();

//        for (EmployeeVo vo : empVoList) {
//            System.out.println("vo = " + vo);
//        }
//        for (SalaryVo vo : SalaryVoList) {
//            System.out.println("vo    :" +vo);
//        }
        model.addAttribute("SalaryVoList",SalaryVoList);
        model.addAttribute("empVoList",empVoList);
        return "hr/salary/list";
    }




    @PostMapping("write")
    public String write(SalaryVo vo){
        System.out.println("payYearmonth  == " +vo.getPayYearmonth());

        String payYearmonth = vo.getPayYearmonth();
        String formattedPayYearmonth = payYearmonth.replace("-", "");

        vo.setPayYearmonth(formattedPayYearmonth);

        int result = service.write(vo);

        return "redirect:/api/hr/salary/list";
    }



    //////사원 선택 버튼클릭후  모달창에 있는 정보를 받아오는 ajax
    @PostMapping("getEmployeeData")
    @ResponseBody
    public EmployeeVo getEmployeeData(@RequestParam("empNo") String empNo){

        System.out.println(empNo);
        EmployeeVo selectvolist = service.selectvolist(empNo);
        return selectvolist;
    }


    //급여 상세조회 하기
    @PostMapping("detail")
    @ResponseBody
    public SalaryVo detail(String selectNo){


        SalaryVo vo = service.detail(selectNo);


        return vo;
    }

    //수정하기
    @PostMapping("edit")
    @ResponseBody
    public String edit(@RequestBody HashMap<String,String> editdata){

        System.out.println(editdata.get("payYearmonth"));

        String resetdate=editdata.get("payYearmonth");
        String formattedPayYearmonth = resetdate.replace("-", "");
        System.out.println(formattedPayYearmonth);

        editdata.put("payYearmonth",formattedPayYearmonth);

        System.out.println("editdata=="+editdata);

        int result = service.edit(editdata);
        return "수정~~";
    }

    @PostMapping("del")
    @ResponseBody
    public int del(String no){
        System.out.println(no);

        int result = service.delete(no);
        
        return result;
    }
}