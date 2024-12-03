package com.kh.semi.finance.partner.controller;

import com.kh.semi.finance.partner.service.PartnerService;
import com.kh.semi.finance.partner.vo.PartnerVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("finance/partner")
@RequiredArgsConstructor
@Slf4j
public class PartnerController {

    private final PartnerService service;

//  게시글 작성(화면)
    @GetMapping("write")
    public String write(){
        return "finance/partner/write";
    }

    //게시글 작성(데이터)
    @PostMapping("write")
    public String write(PartnerVo vo , HttpSession session) throws Exception{
        System.out.println("vo = " + vo);

        int result = service.write(vo);

        if(result != 1) {
            throw new Exception("게시글 작성 에러");
        }
        session.setAttribute("alertMsg" , "등록");
        return "redirect:/finance/partner/list ";

    }

    // 거래처 리스트 조회
    @GetMapping("list")
    public String getPartnerList(Model model) {

        List<PartnerVo> partnerVoList = service.getPartnerList(model);

        if(partnerVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("partnerVoList" , partnerVoList);
        System.out.println("partnerVoList = " + partnerVoList);

        return "finance/partner/list";

    }

    // 거래처 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public PartnerVo getPartnerDetail(String no , Model model) {

        PartnerVo partnerVo = service.getPartnerDetail(no , model);

        if(partnerVo == null) {
            throw new IllegalStateException("ERROR");
        }
        System.out.println("요청받은 no: " + no);
        model.addAttribute("partnerVo" , partnerVo);
        System.out.println("partnerVo = " + partnerVo);

        return partnerVo;

    }

    //거래처 수정(화면)
    @GetMapping("edit")
    @ResponseBody
    public PartnerVo edit(String no, Model model) throws Exception {

        PartnerVo partnerVo = service.getPartnerDetail(no , model);


        System.out.println("partnerVo = " + partnerVo);

        if(partnerVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("partnerVo" , partnerVo);


        return partnerVo;
    }
    //거래처 수정(데이터)
    @PostMapping("edit")
    public String edit (PartnerVo vo, HttpSession session , Model model) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg", "partner 수정완료.");

        PartnerVo partnerVo = service.getPartnerDetail(vo.getNo(), model);
        model.addAttribute("partnerVo", partnerVo);

        return "redirect:/finance/partner/list";
    }


    @GetMapping("delete")
    public String delete(@RequestParam("no") String no ,HttpSession session) throws Exception {

        System.out.println("no = " + no);

        int result = service.delete(no);

        if (result != 1) {
            throw new Exception("error");
        }

            session.setAttribute("alertMsg", "partner 데이터 삭제완료");
            return "redirect:/finance/partner/list";
    }


}

