package com.kh.semi.finance.account.controller;


import com.kh.semi.finance.account.service.AccountService;
import com.kh.semi.finance.account.vo.AccountVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("finance/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService service;

    // 은행 계좌 작성 (화면)
    @GetMapping("write")
    public String write(){
        return "finance/account/write";
    }

    // 은행 계좌 작성 (데이터)
    @PostMapping("write")
    public String write(AccountVo vo , HttpSession session) throws Exception {
        System.out.println("vo = " + vo);

        int result = service.write(vo);

        if(result != 1) {
            throw new Exception("은행계좌 Error");
        }
        session.setAttribute("alertMsg" ,"은행계좌 등록");
        return "redirect:/finance/account/list";
    }

//origin
//    //은행 목록 조회(화면)
//    @GetMapping("list")
//    public String getAccountList(Model model) {
//        // 서비스 호출로 데이터 가져오기
//        List<AccountVo> accountVoList = service.getAccountList();
//
//        if(accountVoList == null) {
//            return "redirect:/error";
//        }
//
//        // JSP에서 참조할 데이터 이름을 "accountVoList"로 설정
//        model.addAttribute("accountVoList", accountVoList);
//
//        // 로그로 데이터 출력
//        System.out.println("accountVoList = " + accountVoList);
//
//        // 뷰 이름 반환 (JSP 경로)
//        return "finance/account/list";
//    }

    //list
//    @GetMapping("list")
//    public String getAccountList(
//            @RequestParam(name = "pno", defaultValue = "1") int currentPage,
//            Model model) {
//
//        // 데이터 개수 가져오기
//        int listCount = service.getAccountListCnt();
//
//        // PageVo 생성
//        int pageLimit = 10;  // 하단 페이지 번호 개수
//        int boardLimit = 14; // 한 페이지에 보여줄 데이터 수
//        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
//
//        // 페이징 처리된 데이터 가져오기
//        List<AccountVo> accountVoList = service.getAccountList(pageVo);
//
//        // Model에 데이터 전달
//        model.addAttribute("accountVoList", accountVoList);
//        model.addAttribute("pageVo", pageVo);
//
//        return "finance/account/list"; // JSP 경로
//    }
//
//@GetMapping("list")
//public String getAccountList(
//        @RequestParam(name = "pno", defaultValue = "1") int currentPage,
//        @RequestParam(name = "area", required = false) String area,
//        @RequestParam(name = "searchValue", required = false) String searchValue,
//        Model model) {
//
//    // 검색 조건 기본값 설정
//    area = (area == null || area.isBlank()) ? "" : area.trim();
//    searchValue = (searchValue == null || searchValue.isBlank()) ? "" : searchValue.trim();
//
//    // 데이터 개수 가져오기
//    int listCount = service.getAccountListCnt(area, searchValue);
//
//    // PageVo 생성
//    int pageLimit = 10;  // 하단 페이지 번호 개수
//    int boardLimit = 14; // 한 페이지에 보여줄 데이터 수
//    PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
//
//    // 페이징 처리된 데이터 가져오기
//    List<AccountVo> accountVoList = service.getAccountList(pageVo, area, searchValue);
//
//    // Model에 데이터 전달
//    model.addAttribute("accountVoList", accountVoList);
//    model.addAttribute("pageVo", pageVo);
//    model.addAttribute("area", area);
//    model.addAttribute("searchValue", searchValue);
//
//    return "finance/account/list"; // JSP 경로
//}

    @GetMapping("list")
    public String getAccountList(
            @RequestParam(name = "pno", defaultValue = "1") int currentPage,
            @RequestParam(name = "area", required = false) String area,
            @RequestParam(name = "searchValue", required = false) String searchValue,
            Model model ,HttpSession session) {

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
                ? service.getAccountListCnt(area, searchValue) // 검색 조건 있을 때
                : service.getTotalAccountCount(); // 검색 조건 없을 때

        // PageVo 생성
        int pageLimit = 5;  // 하단 페이지 번호 개수
        int boardLimit = 14; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // 페이징 처리된 데이터 가져오기
        List<AccountVo> accountVoList = isSearch
                ? service.getAccountList(pageVo, area, searchValue) // 검색 조건 있을 때
                : service.getAllAccounts(pageVo); // 검색 조건 없을 때

        // Model에 데이터 전달
        model.addAttribute("accountVoList", accountVoList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("area", area);
        model.addAttribute("searchValue", searchValue);

        return "finance/account/list"; // JSP 경로
    }


    // 은행 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AccountVo getAccountDetail(String no, Model model) throws Exception {

        AccountVo accountVo = service.getAccountDetail(no, model);

        if (accountVo == null) {
            throw new IllegalStateException("ERROR");
        }

        System.out.println("요청 받은 no = " + no);
        model.addAttribute("accountVo", accountVo);
        System.out.println("accountVo = " + accountVo);

        return accountVo;
    }

    //은행 수정
    @GetMapping("edit")
    @ResponseBody
    public AccountVo edit(String no, Model model) throws Exception {

        AccountVo accountVo = service.getAccountDetail(no, model);

        System.out.println("accountVo = " + accountVo);

        if (accountVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("accountVo", accountVo);

        return accountVo;
    }

    //은행계좌 수정(데이터)
    @PostMapping("edit")
    public String edit(AccountVo vo , HttpSession session , Model model) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg", "account 수정완료.");

        AccountVo accountVo = service.getAccountDetail(vo.getNo(), model);
        model.addAttribute("accountVo", accountVo);

        return "redirect:/finance/account/list";


    }

    //은행 삭제(DB에서도 삭제함)
    @DeleteMapping("delete")
    public ResponseEntity<String> del(@RequestParam("no") String no, HttpSession session) {
        int result = service.del(no);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회사 계좌 삭제 실패...");
        }
//        session.setAttribute("alertMsg", "회사 계좌 삭제 성공!");
        return ResponseEntity.ok("회사 계좌 삭제 성공");
    }

    @DeleteMapping("deleteMultiple")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteMultiple(@RequestBody List<String> accountIds) {
        if (accountIds == null || accountIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "삭제할 항목이 없습니다."));
        }

        // 디버깅 로그
        System.out.println("컨트롤러에서 받은 Account IDs: " + accountIds);

        // 서비스 호출
        int result = service.deleteMultipleAccounts(accountIds);

        // 결과 처리
        if (result > 0) {
            return ResponseEntity.ok(Map.of("message", "선택된 항목이 삭제되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "삭제에 실패했습니다."));
        }
    }

}
