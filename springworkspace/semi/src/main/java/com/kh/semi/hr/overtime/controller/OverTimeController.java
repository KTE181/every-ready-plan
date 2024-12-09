package com.kh.semi.hr.overtime.controller;

import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.hr.employeehr.vo.SearchVo;
import com.kh.semi.hr.overtime.service.OverTimeService;
import com.kh.semi.hr.overtime.vo.OverTimeVo;
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
@RequestMapping("api/hr/overtime")
public class OverTimeController{
    private final OverTimeService service;




    @PostMapping("write")
    public String write(OverTimeVo vo, HttpSession session){

//        System.out.println("vo.hour =="+vo.getHour());
//        System.out.println("vo.minute =="+vo.getMinute());



        String no =vo.getEmpNo();
        String year = vo.getThisDate();
        String overtime = vo.getHour()+":"+vo.getMinute();
        int cnt = service.getCnt(no,year);

        if(cnt != 0){
            session.setAttribute("alertMsg","근무 일자 중복확인하세요");
            return "redirect:/api/hr/overtime/list";
        }

//        System.out.println("overtime : " +overtime);
        vo.setWorkHour(overtime);

//        System.out.println(vo);
        String result = service.insert(vo);
        if (result.equals("1")) {
            session.setAttribute("alertMsg","초과근무 등록 성공");
        }else{
            session.setAttribute("alertMsg",result);
        }

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
    public String list(Model model, @RequestParam(name = "pno" , required = false, defaultValue = "1") int currentPage,
                       SearchVo searchVo,HttpSession session){
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        int listCount = service.getOverTimeCnt();
        int pageLimit = 5;
        int boardLimit = 14;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

//        System.out.println(pvo);

        List<OverTimeVo> listVo = service.list(pvo,searchVo);


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
//        System.out.println(vo.getWorkHour());

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
    public void edit(OverTimeVo alldata,HttpSession session){
        String no = alldata.getEmpNo();
        String year = alldata.getThisDate();
        String overtime = alldata.getHour()+":"+alldata.getMinute();
        alldata.setWorkHour(overtime);

        int cnt = service.getCnt(no,year);
        if(cnt != 0){
            session.setAttribute("alertMsg","일자가 중복되었습니다.");
            return;
        }
//        System.out.println(alldata);
        String result = service.edit(alldata);

        if(result.equals("1")){
          session.setAttribute("alertMsg","수정 완료");
        }else{
            session.setAttribute("alertMsg",result);
        }
    }

    @PostMapping("del")
    @ResponseBody
    public void del(String no,HttpSession session){
//        System.out.println(no);

        int result = service.delete(no);
        if(result == 1){
            session.setAttribute("alertMsg","삭제 완료");
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
            session.setAttribute("alertMsg","삭제하기 성공");
        }else{
            session.setAttribute("alertMsg","삭제하기 실패");
        }
    }

    @GetMapping("getEmplistdata")
    @ResponseBody
    public  List<EmployeeVo> getEmplistdata(String pno,HttpSession session){
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
        if(empVoList.isEmpty()){
            session.setAttribute("alertMsg","사원정보 불러오기 실패");
        }
        return empVoList;
    }

    @PostMapping("getEmplistdata")
    @ResponseBody
    public List<EmployeeVo>  getEmpVo(String searchEmpNo, String searchEname){
//        System.out.println(searchEmpNo);
//        System.out.println(searchEname);

        List<EmployeeVo>  vo = service.selectEmpVo(searchEmpNo,searchEname);

//        System.out.println("vo = "+vo);
        if(vo == null){
            return null;
        }
        return vo;
    }

}
