package com.kh.semi.finance.purchase.controller;

import com.kh.semi.finance.purchase.service.PurchaseService;
import com.kh.semi.finance.purchase.vo.PurchaseVo;
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
@RequestMapping("finance/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;

    // 매입 관리 작성(화면)

    @GetMapping("write")
    public String write(){
        return "finance/purchase/write";
    }

    // 매입 관리 작성(데이터)

    @PostMapping("write")
    public String write(PurchaseVo vo , HttpSession session) throws Exception{
        System.out.println("vo = " + vo);

        int result =service.write(vo);

        if(result != 1) {
            throw new Exception("매입관리 에러");
        }

        session.setAttribute("alertMsg" , "매입 등록");

        return "redirect:/finance/purchase/list";
    }

    //매입 목록 조회(화면)
//    @GetMapping("list")
//    public String getPurchaseList(Model model) {
//
//        List<PurchaseVo> purchaseVoList = service.getPurchaseList();
//
//        if(purchaseVoList == null) {
//            return "redirect:/error";
//        }
//
//        model.addAttribute("purchaseVoList" , purchaseVoList);
//
//        System.out.println("purchaseVoList = " + purchaseVoList);
//
//        return "finance/purchase/list";
//    }


    // 거래처 리스트 조회
    @GetMapping("list")
    public String getPurchaseList(
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
                ? service.getPurchaseListCnt(area, searchValue) // 검색 조건 있을 때
                : service.getTotalPurchaseCount(); // 검색 조건 없을 때

        int pageLimit = 5;  // 하단 페이지 번호 개수
        int boardLimit = 14; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<PurchaseVo> purchaseVoList = isSearch
                ? service.getPurchaseList(pageVo , area, searchValue)// 검색 조건 있을
                : service.getAllPurchases(pageVo);// 검색 조건 없을 때

        if(purchaseVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("purchaseVoList" , purchaseVoList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("area", area);
        model.addAttribute("searchValue", searchValue);

        return "finance/purchase/list";

    }
    


    //매입 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public PurchaseVo getPurchaseDetail(String no , Model model) throws Exception {

        PurchaseVo purchaseVo = service.getPurchaseDetail(no , model);

        if(purchaseVo == null) {
            throw new IllegalStateException("Error");
        }

        System.out.println("no = " + no);
        model.addAttribute("purchaseVo" , purchaseVo);
        System.out.println("purchaseVo = " + purchaseVo);

        return purchaseVo;

    }
    //매입 수정(화면)
    @GetMapping("edit")
    @ResponseBody
    public PurchaseVo edit(String no , Model model) throws Exception {
        PurchaseVo purchaseVo = service.getPurchaseDetail(no,model);

        System.out.println("purchaseVo = " + purchaseVo);

        if (purchaseVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("purchaseVo" , purchaseVo);

        return purchaseVo;
    }

    //매입 수정(데이터)
    @PostMapping("edit")
    public String edit(PurchaseVo vo , HttpSession session , Model model) throws Exception{
        int result = service.edit(vo);

        if(result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg" , "purchase 수정");

        PurchaseVo purchaseVo = service.getPurchaseDetail(vo.getNo(), model);
        model.addAttribute("purchaseVo" , purchaseVo);

        return "redirect:/finance/purchase/list";
    }

    //매입 삭제
    @PostMapping("delete")
    public String delete(String no, HttpSession session) throws Exception{

        int result = service.delete(no);

        if(result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg" , "purchase 삭제");

        return "redirect:/finance/purchase/list";
    }

    @PostMapping("deleteMultiple")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteMultiple(@RequestBody List<String> purchaseIds) {
        if (purchaseIds == null || purchaseIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "삭제할 항목이 없습니다."));
        }

        System.out.println("삭제 요청 받은 purchaseIds: " + purchaseIds);
        int result = service.deleteMultiplePurchases(purchaseIds);

        if (result > 0) {
            return ResponseEntity.ok(Map.of("message", "선택된 항목이 삭제되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "삭제에 실패했습니다."));
        }
    }

}