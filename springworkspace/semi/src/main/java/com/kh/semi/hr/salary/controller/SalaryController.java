package com.kh.semi.hr.salary.controller;

import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.hr.employeehr.vo.SearchVo;
import com.kh.semi.hr.salary.service.SalaryService;
import com.kh.semi.hr.salary.vo.SalaryVo;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/hr/salary")
public class SalaryController {

    private final SalaryService service;

    @GetMapping("list")
    public String listAll(Model model, @RequestParam(name = "pno" , required = false, defaultValue = "1") int currentPage,
                          SearchVo searchVo,HttpSession session){

        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        if(searchVo.getSearchMonth()!=null){
            String resetdate=searchVo.getSearchMonth();
            String formattedPayYearmonth = resetdate.replace("-", "");
//            System.out.println(formattedPayYearmonth);
            searchVo.setSearchMonth(formattedPayYearmonth);
        }


//        System.out.println(searchVo);

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
//        System.out.println("payYearmonth  == " +vo.getPayYearmonth());



        String no =vo.getEmpNo();
        String payYearmonth = vo.getPayYearmonth();
        String formattedPayYearmonth = payYearmonth.replace("-", "");

        int cnt = service.getCnt(no,formattedPayYearmonth);
        if(cnt !=0){
            session.setAttribute("alertMsg","등록일이 중복되었습니다 확인해주세요");
            return "redirect:/api/hr/salary/list";
        }
        vo.setPayYearmonth(formattedPayYearmonth);

        String result = service.write(vo);

        if(result.equals("1")){
            session.setAttribute("alertMsg","급여 등록 성공");
        }else{
        session.setAttribute("alertMsg",result);
        }
        return "redirect:/api/hr/salary/list";
    }



    //////사원 선택 버튼클릭후  모달창에 있는 정보를 받아오는 ajax
    @PostMapping("getEmployeeData")
    @ResponseBody
    public EmployeeVo getEmployeeData(@RequestParam("empNo") String empNo,HttpSession session){

//        System.out.println(empNo);
        EmployeeVo selectvolist = service.selectvolist(empNo);
        if(selectvolist ==null||selectvolist.getNo().isEmpty()){
            session.setAttribute("alertMsg","사원정보 불러오기 실패..");
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
    public void edit(SalaryVo editdata,HttpSession session){

//        System.out.println(editdata.get("payYearmonth"));

//        System.out.println(editdata);
        String no = editdata.getEmpNo();
        String resetdate=editdata.getPayYearmonth();
        String formattedPayYearmonth = resetdate.replace("-", "");
//        System.out.println(formattedPayYearmonth);
        int cnt = service.getCnt(no,formattedPayYearmonth);
        if(cnt !=0){
            session.setAttribute("alertMsg","일자 중복됬습니다 확인해주세요");
            return;
        }
       editdata.setPayYearmonth(formattedPayYearmonth);

//        System.out.println("editdata=="+editdata);

        String result = service.edit(editdata);
        
        if(result.equals("1")){
          session.setAttribute("alertMsg","수정 성공");  
        }else{
            session.setAttribute("alertMsg",result);
        }
        
  
    }

    @PostMapping("del")
    @ResponseBody
    public void del(String no,HttpSession session){
//        System.out.println(no);

        int result = service.delete(no);

        if(result ==1){
            session.setAttribute("alertMsg","삭제 성공");
        }else{
            session.setAttribute("alertMsg","삭제 실패");
        }
        
    }

    @DeleteMapping("del")
    @ResponseBody
    public void del(@RequestBody String[] dataArr,HttpSession session){
//        for (String s : dataArr) {
//            System.out.println(s);
//        }
        if(dataArr.length==0){
            return;
        }
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
//        System.out.println(pno);
        int currentPage = Integer.parseInt(pno);
        int listCount2 = service.getEmpCnt();
        int pageLimit2 = 5;

        int boardLimit2 = 10;
        PageVo pvo = new PageVo(listCount2, currentPage, pageLimit2, boardLimit2);

        List<EmployeeVo> empVoList = service.getEmplistdata(pvo);

//        for (EmployeeVo employeeVo : empVoList) {
//            System.out.println("employeeVo = " + employeeVo);
//
//        }
        return empVoList;
    }
    @PostMapping("getEmplistdata")
    @ResponseBody
    public List<EmployeeVo>  getEmpVo(String searchEmpNo, String searchEname){
//        System.out.println(searchEmpNo);
//        System.out.println(searchEname);

        List<EmployeeVo> vo = service.selectEmpVo(searchEmpNo,searchEname);
        if(vo == null){
            return null;
        }
        return vo;
    }


}