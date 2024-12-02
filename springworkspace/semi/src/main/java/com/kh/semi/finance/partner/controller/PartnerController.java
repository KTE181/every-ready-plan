package com.kh.semi.finance.partner.controller;

import com.kh.semi.finance.partner.service.PartnerService;
import com.kh.semi.finance.partner.vo.PartnerVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("fi/partner")
@RequiredArgsConstructor
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
    public String getPartnerDetail(String partnerNo , Model model) {
        PartnerVo partnerVo = service.getPartnerDetail(partnerNo , model);

        if(partnerVo == null ) {
            return "redirect:/error";
        }

        model.addAttribute("partnerVo" , partnerVo);
        System.out.println("partnerVo = " + partnerVo);

        return "finance/partner/detail";

    }

    //거래처 수정(화면)
    @GetMapping("edit")
    public String edit(String partnerNo, Model model) throws Exception {

        PartnerVo partnerVo = service.getPartnerDetail(partnerNo , model);

        if(partnerVo == null) {
            return "redirect:/error";
        }

        model.addAttribute("partnerVo" , partnerVo);

        return "finance/partner/edit";
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
    public String delete(String no ,HttpSession session) throws Exception {

        int result = service.delete(no);

        if (result != 1) {
            throw new Exception("error");
        }

            session.setAttribute("alertMsg", "partner 데이터 삭제완료");
            return "redirect:/finance/partner/list";
    }


}

