package com.kh.semi.hr.overtime.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.overtime.service.OverTimeService;
import com.kh.semi.hr.overtime.vo.OverTimeVo;
import com.kh.semi.pb.vo.PageVo;
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
    public String list(Model model,@RequestParam(name = "pno" , required = false, defaultValue = "1") int currentPage){

        int listCount = service.getOverTimeCnt();
        int pageLimit = 5;
        int boardLimit = 14;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        System.out.println(pvo);

        List<OverTimeVo> listVo = service.list(pvo);


        int listCount2 = service.getEmpCnt();
        int currentPage2 = 1;
        int pageLimit2 = 5;
        int boardLimit2 = 10;
        PageVo pvo2 = new PageVo(listCount2, currentPage2, pageLimit2, boardLimit2);

        List<EmployeeVo> empVoList = service.empVoList(pvo2);


//        for (OverTimeVo vo : listVo) {
//            System.out.println(vo);
//        }
//        for (EmployeeVo vo : empVoList) {
//            System.out.println("vo = " + vo);
//        }

        model.addAttribute("pvo2",pvo2);
        model.addAttribute("pvo",pvo);
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

    @GetMapping("getEmplistdata")
    @ResponseBody
    public  List<EmployeeVo> getEmplistdata(String pno){
        System.out.println(pno);
        int currentPage = Integer.parseInt(pno);
        int listCount2 = service.getEmpCnt();
        int pageLimit2 = 5;
        int boardLimit2 = 10;
        PageVo pvo = new PageVo(listCount2, currentPage, pageLimit2, boardLimit2);

        List<EmployeeVo> empVoList = service.getEmplistdata(pvo);

        for (EmployeeVo employeeVo : empVoList) {
            System.out.println("employeeVo = " + employeeVo);

        }
        return empVoList;
    }

}
