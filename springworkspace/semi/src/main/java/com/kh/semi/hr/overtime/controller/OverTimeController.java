package com.kh.semi.hr.overtime.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.overtime.service.OverTimeService;
import com.kh.semi.hr.overtime.vo.OverTimeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/hr/overtime")
public class OverTimeController{
    private final OverTimeService service;




    @PostMapping("write")
    public String write(OverTimeVo vo){

//        System.out.println("vo.hour =="+vo.getHour());
//        System.out.println("vo.minute =="+vo.getMinute());



        String overtime = vo.getHour()+":"+vo.getMinute();

//        System.out.println("overtime : " +overtime);
        vo.setWorkHour(overtime);

//        System.out.println(vo);
        int result = service.insert(vo);

        return "redirect:/api/hr/overtime/list";
    }



    //////사원 선택 버튼클릭후  모달창에 있는 정보를 받아오는 ajax
    @PostMapping("getEmployeeData")
    @ResponseBody
    public EmployeeVo getEmployeeData(@RequestParam("empNo") String empNo){

//        System.out.println(empNo);
        EmployeeVo selectvolist = service.selectvolist(empNo);
        return selectvolist;
    }


    @GetMapping("list")
    public String list(Model model){
        List<OverTimeVo> listVo = service.list();
        List<EmployeeVo> empVoList = service.empVoList();
//        for (OverTimeVo vo : listVo) {
//            System.out.println(vo);
//        }
//        for (EmployeeVo vo : empVoList) {
//            System.out.println("vo = " + vo);
//        }

        model.addAttribute("empVoList",empVoList);
        model.addAttribute("overTimeVoList",listVo);

        return "hr/overtime/list";
    }

    @PostMapping("detail")
    @ResponseBody
    public OverTimeVo detail(String no){


        OverTimeVo vo = service.detail(no);
        System.out.println(vo.getWorkHour());

        // 공백으로 날짜와 시간 나누기
        String[] dateTimeParts = vo.getWorkHour().split(" ");
        // 시간과 분을 ":"로 나누기
        String[] timeParts = dateTimeParts[1].split(":");
        String hours = timeParts[0];  //
        String minutes = timeParts[1]; //



        vo.setHour(hours);
        vo.setMinute(minutes);

//        // 원하는 형식으로 출력
//        System.out.println("시간 " + hours);
//        System.out.println("분 " + minutes);

        return vo;
    }

    @PostMapping("edit")
    @ResponseBody
    public int edit(OverTimeVo alldata){

        String overtime = alldata.getHour()+":"+alldata.getMinute();
        alldata.setWorkHour(overtime);
        System.out.println(alldata);
        int result = service.edit(alldata);

        if(result != 1){
            return 0;
        }
        return result;
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
