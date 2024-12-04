package com.kh.semi.hr.salary.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.employee.vo.SearchVo;
import com.kh.semi.hr.salary.service.SalaryService;
import com.kh.semi.hr.salary.vo.SalaryVo;
import com.kh.semi.pb.vo.PageVo;
import jakarta.servlet.http.HttpSession;
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
    public String listAll(Model model, @RequestParam(name = "pno" , required = false, defaultValue = "1") int currentPage,
                          SearchVo searchVo){

        if(searchVo.getSearchMonth()!=null){
            String resetdate=searchVo.getSearchMonth();
            String formattedPayYearmonth = resetdate.replace("-", "");
            System.out.println(formattedPayYearmonth);
            searchVo.setSearchMonth(formattedPayYearmonth);
        }


        System.out.println(searchVo);

        int listCount = service.getSalaryCnt();
        int pageLimit = 5;
        int boardLimit = 14;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);


        List<SalaryVo> SalaryVoList = service.listAll(pvo,searchVo);



        int listCount2 = service.getEmpCnt();
        int currentPage2 = 1;
        int pageLimit2 = 5;
        int boardLimit2 = 10;
        PageVo pvo2 = new PageVo(listCount2, currentPage2, pageLimit2, boardLimit2);
        List<EmployeeVo> empVoList = service.empVoList(pvo2);

//        for (EmployeeVo vo : empVoList) {
//            System.out.println("vo = " + vo);
//        }
//        for (SalaryVo vo : SalaryVoList) {
//            System.out.println("vo    :" +vo);
//        }
        model.addAttribute("pvo2",pvo2);
        model.addAttribute("pvo",pvo);
        model.addAttribute("SalaryVoList",SalaryVoList);
        model.addAttribute("empVoList",empVoList);
        return "hr/salary/list";
    }




    @PostMapping("write")
    public String write(SalaryVo vo, HttpSession session){
        System.out.println("payYearmonth  == " +vo.getPayYearmonth());

        String payYearmonth = vo.getPayYearmonth();
        String formattedPayYearmonth = payYearmonth.replace("-", "");

        vo.setPayYearmonth(formattedPayYearmonth);

        int result = service.write(vo);

        if(result != 1){
            session.setAttribute("alertMsg","급여등록 실패");
        }
        session.setAttribute("alerMsg","급여 등록 성공");
        return "redirect:/api/hr/salary/list";
    }



    //////사원 선택 버튼클릭후  모달창에 있는 정보를 받아오는 ajax
    @PostMapping("getEmployeeData")
    @ResponseBody
    public EmployeeVo getEmployeeData(@RequestParam("empNo") String empNo){

        System.out.println(empNo);
        EmployeeVo selectvolist = service.selectvolist(empNo);
        if(selectvolist ==null){

        }

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
    @PostMapping("getEmplistdata")
    @ResponseBody
    public EmployeeVo getEmpVo(String searchEmpNo, String searchEname){
        System.out.println(searchEmpNo);
        System.out.println(searchEname);

        EmployeeVo vo = service.selectEmpVo(searchEmpNo,searchEname);
        return vo;
    }


}