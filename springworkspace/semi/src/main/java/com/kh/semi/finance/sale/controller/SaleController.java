package com.kh.semi.finance.sale.controller;


import com.kh.semi.finance.sale.service.SaleService;
import com.kh.semi.finance.sale.vo.SaleVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("finance/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    //매출 관리 작성(화면)
    @GetMapping("write")
    public String write(){
        return "finance/sale/write";
    }

    //매출 관리 작성(데이터)
    @PostMapping("write")
    public String write(SaleVo vo , HttpSession session) throws Exception {
        System.out.println("vo = " + vo);

        int result = service.write(vo);

        if(result != 1) {
            throw new Exception("매출 관리 에러");
        }

        session.setAttribute("alertMsg" , "매출 등록");

        return "redirect:/finance/sale/list";

    }

    //매출 목록 조회(화면)
//    @GetMapping("list")
//    public String getSaleList(Model model) {
//        List<SaleVo> saleVoList = service.getSaleList();
//
//        if(saleVoList == null) {
//            return "redirect:/error";
//        }
//
//        model.addAttribute("saleVoList" , saleVoList);
//
//        System.out.println("saleVoList = " + saleVoList);
//
//        return "finance/sale/list";
//    }

    @GetMapping("list")
    public String getSaleList(

            @RequestParam(name = "pno", defaultValue = "1") int currentPage,
            @RequestParam(name = "area", required = false) String area,
            @RequestParam(name = "searchValue", required = false) String searchValue,
            Model model , HttpSession session) {


        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        if(loginEmployeeVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // 검색 조건 기본값 설정
        boolean isSearch = !(area == null || area.isBlank()) && !(searchValue == null || searchValue.isBlank());
        area = (area == null) ? "" : area.trim();
        searchValue = (searchValue == null) ? "" : searchValue.trim();

        // 데이터 개수 가져오기
        int listCount = isSearch
                ? service.getSaleListCnt(area, searchValue) // 검색 조건 있을 때
                : service.getTotalSaleCount(); // 검색 조건 없을 때

        // PageVo 생성
        int pageLimit = 5;  // 하단 페이지 번호 개수
        int boardLimit = 14; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // 페이징 처리된 데이터 가져오기
        List<SaleVo> saleVoList = isSearch
                ? service.getSaleList(pageVo, area, searchValue) // 검색 조건 있을 때
                : service.getAllSales(pageVo); // 검색 조건 없을 때

        // Model에 데이터 전달
        model.addAttribute("saleVoList", saleVoList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("area", area);
        model.addAttribute("searchValue", searchValue);

        return "finance/sale/list"; // JSP 경로
    }

    //매출 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public SaleVo getSaleDetail(String no , Model model) throws Exception {

        SaleVo saleVo = service.getSaleDetail(no, model);

        if (saleVo == null) {
            throw new IllegalStateException("Error");
        }

        System.out.println("no = " + no);
        model.addAttribute("saleVo", saleVo);
        System.out.println("saleVo = " + saleVo);

        return saleVo;
    }

    //매출 수정
    @GetMapping("edit")
    @ResponseBody
    public SaleVo edit(String no , Model model) throws Exception {
        SaleVo saleVo = service.getSaleDetail(no , model);

        System.out.println("saleVo = " + saleVo);

        if(saleVo == null) {
            throw new IllegalStateException("ERROR");
        }
        model.addAttribute("에디트 화면 saleVo" , saleVo);

        return saleVo;

    }
    //매출 수정(데이터)
    @PostMapping("edit")
    public String edit(SaleVo vo ,  HttpSession session, Model model) throws Exception{
        int result = service.edit(vo);

        if(result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg" , "sale 수정");

        SaleVo saleVo = service.getSaleDetail(vo.getNo(), model);
        model.addAttribute("saleVo" , saleVo);

        return "redirect:/finance/sale/list";

    }

    @PostMapping("delete")
    public String delete(String no , HttpSession session) throws Exception {

        int result = service.delete(no);

        if(result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg" , "sale 삭제");

        return "redirect:/finance/sale/list";
    }

    @PostMapping("deleteMultiple")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteMultiple(@RequestBody List<String> saleIds) {
        if (saleIds == null || saleIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "삭제할 항목이 없습니다."));
        }

        System.out.println("삭제 요청 받은 saleIds: " + saleIds);
        int result = service.deleteMultipleSales(saleIds);

        if (result > 0) {
            return ResponseEntity.ok(Map.of("message", "선택된 항목이 삭제되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "삭제에 실패했습니다."));
        }
    }
}
