package com.kh.semi.finance.sale.controller;

import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.finance.sale.service.SaleService;
import com.kh.semi.finance.sale.vo.SaleVo;
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
            throw new Exception("매출관리 에러");
        }

        session.setAttribute("alertMsg" , "매출관리 오류");

        return "redirect:/finance/sale/list";

    }

    //매출 목록 조회(화면)
    @GetMapping("list")
    public String getSaleList(Model model) {
        List<SaleVo> saleVoList = service.getSaleList();

        if(saleVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("saleVoList" , saleVoList);

        System.out.println("saleVoList = " + saleVoList);

        return "finance/sale/list";
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
        model.addAttribute("에디트 saleVo" , saleVo);

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

}
