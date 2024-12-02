package com.kh.semi.finance.account.controller;

import com.kh.semi.finance.account.service.AccountService;
import com.kh.semi.finance.account.vo.AccountVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        int result = service.write(vo);
        if(result != 1) {
            throw new Exception("은행계좌 Error");
        }
        session.setAttribute("alertMsg" ,"은행계좌 등록");
        return "redirect:/finance/account/list";
    }

    //은행 목록 조회(화면)
    @GetMapping("list")
    public String getAccountlist(Model model) {
        // 서비스 호출로 데이터 가져오기
        List<AccountVo> accountVoList = service.getAccountList();

        if(accountVoList == null) {
            return "redirect:/error";
        }

        // JSP에서 참조할 데이터 이름을 "accountVoList"로 설정
        model.addAttribute("accountVoList", accountVoList);

        // 로그로 데이터 출력
        System.out.println("accountVoList = " + accountVoList);

        // 뷰 이름 반환 (JSP 경로)
        return "finance/account/list";
    }

    // 은행 상세 조회
    @GetMapping("detail")
    public String getAccountDetail(String no, Model model) {
        AccountVo accountVo = service.getAccountDetail(no, model);

        if (accountVo == null) {
            return "redirect:/error";
        }

        model.addAttribute("accountVo", accountVo);
        System.out.println("accountVo = " + accountVo);

        return "finance/account/detail";
    }

    //은행 수정
    @GetMapping("edit")
    public String edit(String no, Model model) throws Exception {
        AccountVo accountVo = service.getAccountDetail(no, model);

        if (accountVo == null) {
            return "redirect:/error";
        }

        model.addAttribute("accountVo", accountVo);
        System.out.println("accountVo = " + accountVo);

        return "finance/account/edit";
    }

    //은행 삭제(DB에서도 삭제함)
    @DeleteMapping("del")
    public ResponseEntity<String> del(@RequestParam("no") String no, HttpSession session) {
        int result = service.del(no);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회사 계좌 삭제 실패...");
        }
        session.setAttribute("alertMsg", "회사 계좌 삭제 성공!");
        return ResponseEntity.ok("회사 계좌 삭제 성공");
    }



}
