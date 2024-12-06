package com.kh.semi.finance.expense.controller;

import com.kh.semi.finance.expense.service.ExpenseService;
import com.kh.semi.finance.expense.vo.ExpenseVo;
import com.kh.semi.pb.vo.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("finance/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService service;

    //경비 작성(화면)
    @GetMapping("write")
    public String write(){
        return "finance/expense/write";
    }

    //경비작성(데이터)
    @PostMapping("write")
    public String write(ExpenseVo vo , HttpSession session) throws Exception {

        System.out.println("vo = " + vo);

        int result = service.write(vo);

        if (result != 1) {
            throw new Exception("경비관리 에러");
        }

        session.setAttribute("alertMsg", "경비 등록");

        return "redirect:/finance/expense/list";

    }


//    //경비 목록 리스트
//    @GetMapping("list")
//    public String getExpenseList(Model model) {
//
//        List<ExpenseVo> expenseVoList = service.getExpenseList();
//
//        if(expenseVoList == null) {
//            return "redirect:/error";
//        }
//        model.addAttribute("expenseVoList" , expenseVoList);
//        System.out.println("expenseVoList = " + expenseVoList);
//
//        return "finance/expense/list";
//    }

    @GetMapping("list")
    public String getExpenseList(
            @RequestParam(name = "pno", defaultValue = "1") int currentPage,
            @RequestParam(name = "area", required = false) String area,
            @RequestParam(name = "searchValue", required = false) String searchValue,
            Model model) {

        // 검색 조건 기본값 설정
        boolean isSearch = !(area == null || area.isBlank()) && !(searchValue == null || searchValue.isBlank());
        area = (area == null) ? "" : area.trim();
        searchValue = (searchValue == null) ? "" : searchValue.trim();

        // 데이터 개수 가져오기
        int listCount = isSearch
                ? service.getExpenseListCnt(area, searchValue) // 검색 조건 있을 때
                : service.getTotalExpenseCount(); // 검색 조건 없을 때

        // PageVo 생성
        int pageLimit = 10;  // 하단 페이지 번호 개수
        int boardLimit = 14; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // 페이징 처리된 데이터 가져오기
        List<ExpenseVo> expenseVoList = isSearch
                ? service.getExpenseList(pageVo, area, searchValue) // 검색 조건 있을 때
                : service.getAllExpenses(pageVo); // 검색 조건 없을 때

        // Model에 데이터 전달
        model.addAttribute("expenseVoList", expenseVoList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("area", area);
        model.addAttribute("searchValue", searchValue);

        return "finance/expense/list"; // JSP 경로
    }
    //경비 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public ExpenseVo getExpenseDetail (String no , Model model) throws Exception{

        ExpenseVo expenseVo = service.getExpenseDetail(no,model);

        if (expenseVo == null) {
            throw new IllegalStateException("Error");
        }
        System.out.println("no = " + no);
        model.addAttribute("expenseVo" , expenseVo);
        System.out.println("expenseVo = " + expenseVo);

        return expenseVo;
    }

    @GetMapping("edit")
    @ResponseBody
    public ExpenseVo edit(String no , Model model ) throws Exception {


        ExpenseVo expenseVo = service.getExpenseDetail(no,model);
        System.out.println("expenseVo = " + expenseVo);

        if(expenseVo == null) {
            throw new IllegalStateException("ERROR");
        }
        model.addAttribute("expenseVo" , expenseVo);

        return expenseVo;
    }


    //경비 상세 수정
    @PostMapping("edit")
    public String edit(ExpenseVo vo , HttpSession session , Model model) throws Exception {
        int result = service.edit(vo);

        if(result!=1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg" , "expense 수정");

        ExpenseVo expenseVo = service.getExpenseDetail(vo.getNo() , model);
        model.addAttribute("expenseVo" , expenseVo);

        return "redirect:/finance/expense/list";

    }


    //경비삭제
    @PostMapping("delete")
    public String delete(String no , HttpSession session) throws Exception {

        int result = service.delete(no);

        if(result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg" , "expense 삭제");
        return "redirect:/finance/expense/list";
    }


}

