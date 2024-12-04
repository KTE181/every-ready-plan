package com.kh.semi.finance.purchase.controller;

import com.kh.semi.finance.purchase.service.PurchaseService;
import com.kh.semi.finance.purchase.vo.PurchaseVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

        session.setAttribute("alertMsg" , "매입관리 오류");

        return "redirect:/finance/purchase/list";
    }

    //매입 목록 조회(화면)
    @GetMapping("list")
    public String getPurchaseList(Model model) {

        List<PurchaseVo> purchaseVoList = service.getPurchaseList();

        if(purchaseVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("purchaseVoList" , purchaseVoList);

        System.out.println("purchaseVoList = " + purchaseVoList);

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

}