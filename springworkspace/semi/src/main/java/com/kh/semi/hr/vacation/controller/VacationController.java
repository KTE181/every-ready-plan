package com.kh.semi.hr.vacation.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.employee.vo.SearchVo;
import com.kh.semi.hr.vacation.service.VacationService;
import com.kh.semi.hr.vacation.vo.VacationVo;
import com.kh.semi.pb.vo.PageVo;
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
    public String list(Model model, @RequestParam(name = "pno" , required = false, defaultValue = "1") int currentPage,
                       SearchVo searchVo) {

        System.out.println(searchVo);




        int listCount = service.getVacationCnt();
        int pageLimit = 5;
        int boardLimit = 14;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        System.out.println(pvo);

        List<VacationVo> listVo = service.list(pvo,searchVo);

        int listCount2 = service.getEmpCnt();
        int currentPage2 = 1;
        int pageLimit2 = 5;
        int boardLimit2 = 10;
        PageVo pvo2 = new PageVo(listCount2, currentPage2, pageLimit2, boardLimit2);
        List<EmployeeVo> empVoList = service.empVoList(pvo2);



        model.addAttribute("pvo2",pvo2);
        model.addAttribute("pvo",pvo);
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