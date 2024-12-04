package com.kh.semi.qa.faultcode.controller;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.faultcode.service.FaultcodeService;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("qa/faultcode")
@RequiredArgsConstructor
@Slf4j
public class FaultcodeController {

    private final FaultcodeService service;

    // 고장코드 목록 조회
    @GetMapping("list")
    public String getFaultCodeList(Model model, @RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                                   String searchType, String searchValue)
    {
        // pno = currentPage
        int listCount = service.getFaultCodeListCnt(searchType, searchValue);
        int pageLimit = 10;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<FaultcodeVo> faultcodeVoList = service.getFaultCodeList(model, pvo, searchType, searchValue);

        if(faultcodeVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("faultcodeVoList", faultcodeVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

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
    @PostMapping("write")
    @ResponseBody
    public int enroll(FaultcodeVo vo) throws Exception {

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
    @PostMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result < 1) {
            throw new Exception("Error");
        }

        return result;
    }

}
