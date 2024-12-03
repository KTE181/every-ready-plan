package com.kh.semi.qa.aswork.controller;

import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.qa.aswork.service.AsworkService;
import com.kh.semi.qa.aswork.vo.AsworkVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("qa/aswork")
@RequiredArgsConstructor
@Slf4j
public class AsworkController {

    private final AsworkService service;

    // AS 작업 목록 조회
    @GetMapping("list")
    public String getAsworkList(Model model) {

        List<AsworkVo> asworkVoList = service.getAsworkList(model);

        if (asworkVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("asworkVoList", asworkVoList);

        return "qa/aswork/list";
    }

    // AS 작업 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsworkVo getAsworkDetail(String asworkNo, Model model) {

        AsworkVo asworkVo = service.getAsworkDetail(asworkNo, model);

        if (asworkVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("asworkVo", asworkVo);

        return asworkVo;
    }

    // AS 작업 수정 (화면)
    @GetMapping("edit")
    @ResponseBody
    public AsworkVo edit(String asworkNo, Model model) {

        AsworkVo asworkVo = service.getAsworkDetail(asworkNo, model);

        if (asworkVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("asworkVo", asworkVo);

        return asworkVo;
    }

    // AS 작업 수정 (처리)
    @PostMapping("edit")
    public String edit(AsworkVo vo, HttpSession session, Model model) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            throw new Exception("Error");
        }

        session.setAttribute("alertMsg", "수정되었습니다.");

        AsworkVo asworkVo = service.getAsworkDetail(vo.getNo(), model);
        model.addAttribute("asworkVo", asworkVo);

        return "redirect:/qa/aswork/list";
    }

    // AS 작업 삭제
    @GetMapping("delete")
    public String delete(String no, HttpSession session) throws Exception {

        System.out.println("no = " + no);

        int result = service.delete(no);

        if(result != 1) {
            throw new Exception("Error");
        }

        session.setAttribute("alertMsg", "삭제되었습니다.");
        return "redirect:/qa/aswork/list";
    }
}