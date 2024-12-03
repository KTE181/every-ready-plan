package com.kh.semi.qa.faultcode.controller;

import com.kh.semi.qa.faultcode.service.FaultcodeService;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
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
@RequestMapping("qa/faultcode")
@RequiredArgsConstructor
@Slf4j
public class FaultcodeController {

    private final FaultcodeService service;

    // 고장코드 목록 조회
    @GetMapping("list")
    public String getFaultCodeList(Model model) {

        List<FaultcodeVo> faultcodeVoList = service.getFaultCodeList(model);

        if(faultcodeVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("faultcodeVoList", faultcodeVoList);
        System.out.println("faultcodeVoList = " + faultcodeVoList);

        return "qa/faultcode/list";
    }

    // 고장코드 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public FaultcodeVo getFaultCodeDetail(String no, Model model) throws Exception {

        FaultcodeVo faultcodeVo = service.getFaultCodeDetail(no, model);

        if(faultcodeVo == null) {
            throw new Exception("Error");
        }

        return faultcodeVo;
    }

    // 고장코드 등록
    @PostMapping("enroll")
    @ResponseBody
    public int enroll(FaultcodeVo vo) throws Exception {

        System.out.println("vo = " + vo);

        int result = service.enroll(vo);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;

    }

    // 고장코드 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(String no, String faultName) throws Exception {

        int result = service.edit(no, faultName);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;

    }

    // 고장코드 삭제
    @GetMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;
    }

}
