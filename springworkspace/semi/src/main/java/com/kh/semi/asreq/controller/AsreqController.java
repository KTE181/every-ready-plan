package com.kh.semi.asreq.controller;

import com.kh.semi.asreq.vo.AsreqVo;
import com.kh.semi.asreq.service.AsreqService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("qa/asreq")
@RequiredArgsConstructor
public class AsreqController {

    private final AsreqService service;

    // AS 요청 등록 (화면)
    @GetMapping("write")
    public String write() {
        return "qa/asreq/write";
    }

    // AS 요청 등록 (처리)
    @PostMapping("write")
    public String write(AsreqVo vo, HttpSession session) throws Exception {
        int result = service.write(vo);
        if(result != 1) {
            throw new Exception("Error");
        }
        session.setAttribute("alertMsg", "등록되었습니다.");
        return "redirect:/qa/asreq/list";
    }

    // AS 요청 목록 조회
    @GetMapping("list")
    public String getAsreqList(Model model) {

        List<AsreqVo> asreqVoList = service.getAsreqList(model);

        if(asreqVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("asreqVoList", asreqVoList);
        System.out.println("voList = " + asreqVoList);

        return "qa/asreq/list";
    }

    // AS 요청 상세 조회
    @GetMapping("detail")
    public String getAsreqDetail(String asreqNo, Model model) {

        AsreqVo asreqVo = service.getAsreqDetail(asreqNo, model);

        if(asreqVo == null) {
            return "redirect:/error";
        }

        model.addAttribute("asreqVo", asreqVo);
        System.out.println("asreqVo = " + asreqVo);

        return "qa/asreq/detail";
    }

    // AS 요청 수정 (화면)
    @GetMapping("edit")
    public String edit(String asreqNo, Model model) throws Exception {

        AsreqVo asreqVo = service.getAsreqDetail(asreqNo, model);

        if(asreqVo == null) {
            return "redirect:/error";
        }

        model.addAttribute("asreqVo", asreqVo);
        System.out.println("asreqVo = " + asreqVo);

        return "qa/asreq/edit";
    }

    // AS 요청 수정 (처리)
    @PostMapping("edit")
    public String edit(AsreqVo vo, HttpSession session, Model model) throws Exception {

        System.out.println("vo = " + vo);
        
        int result = service.edit(vo);

        if(result != 1) {
            throw new Exception("Error");
        }

        session.setAttribute("alertMsg", "수정되었습니다.");

        AsreqVo asreqVo = service.getAsreqDetail(vo.getNo(), model);
        model.addAttribute("asreqVo", asreqVo);

        return "qa/asreq/detail";
    }

    // AS 요청 삭제
    @GetMapping("delete")
    public String delete(String asreqNo, HttpSession session) throws Exception {

        int result = service.delete(asreqNo);

        if(result != 1) {
            throw new Exception("Error");
        }

        session.setAttribute("alertMsg", "삭제되었습니다.");
        return "redirect:/qa/asreq/list";
    }

}
