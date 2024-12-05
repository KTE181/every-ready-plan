package com.kh.semi.hr.vacation.controller;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.employee.vo.SearchVo;
import com.kh.semi.hr.vacation.service.VacationService;
import com.kh.semi.hr.vacation.vo.VacationVo;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import jakarta.servlet.http.HttpSession;
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
    public String write(VacationVo vo, HttpSession session) {

        System.out.println(vo);
        String result = service.insert(vo);

        if(result.equals("1")){
            session.setAttribute("alertMsg","휴가 등록 성공");
        }else {
        session.setAttribute("alertMsg",result);
        }
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
                       SearchVo searchVo,HttpSession session) {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

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
    public void update(VacationVo alldata,HttpSession session){
        System.out.println(alldata);

        String result= service.update(alldata);

        if(result.equals("1")){
            session.setAttribute("alertMsg","수정 성공 ~~");
        }else {
            session.setAttribute("alertMsg",result);
        }

    }
    @PostMapping("del")
    @ResponseBody
    public int del(String no,HttpSession session){
        System.out.println(no);

        int result = service.delete(no);
        if(result != 1){
            session.setAttribute("alertMsg","삭제하기 실패");
        }

        return result;
    }
    @DeleteMapping("del")
    @ResponseBody
    public void del(@RequestBody String[] dataArr,HttpSession session){
        if(dataArr.length==0){
            return;
        }
//        for (String s : dataArr) {
//            System.out.println(s);
//        }
        int result = service.editAll(dataArr);
        if(result>0){
            session.setAttribute("alertMsg","삭제 성공");
        }else{
            session.setAttribute("alertMsg","삭제 실패");
        }
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

        return empVoList;
    }
    @PostMapping("getEmplistdata")
    @ResponseBody
    public EmployeeVo getEmpVo(String searchEmpNo, String searchEname,HttpSession session){
//        System.out.println(searchEmpNo);
//        System.out.println(searchEname);

        EmployeeVo vo = service.selectEmpVo(searchEmpNo,searchEname);
        if(vo == null||vo.getNo().isEmpty()){
            return null;
        }
        return vo;
    }

}