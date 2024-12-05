package com.kh.semi.defective.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.semi.defective.service.DefectiveCodeService;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("qa/defectivecode")
public class DefectiveCodeController {

    private final DefectiveCodeService service;
    private final ObjectMapper objectMapper;

    //불량 코드 목록 조회
    @GetMapping("list")
    public void list(Model model,
                     @RequestParam(value = "searchValue", required = false) String searchValue,
                     @RequestParam(value = "searchValueName", required = false) String searchValueName,
                     @RequestParam(name = "pno", defaultValue = "1") int currentPage) {

        int listCount = service.getDefectiveCodeCnt();
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pageVo = new PageVo(listCount , currentPage, pageLimit, boardLimit);
        List<DefectiveCodeVo> defectiveCodeVo = service.getDefectiveCodeList(searchValue, searchValueName,pageVo);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("defectiveCodeVo", defectiveCodeVo);
    }

    //불량 코드 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public DefectiveCodeVo getDefectiveCodeDetail(@RequestParam("no") String defectiveCodeNo) {
        DefectiveCodeVo defectiveCodeVo = service.getDefectiveCodeDetail(defectiveCodeNo);
        System.out.println("defectiveVo = " + defectiveCodeVo);
        if (defectiveCodeVo == null) {
            throw new IllegalStateException();
        }
        return defectiveCodeVo;
    }

    //불량 코드 등록(처리)
    @PostMapping("write")
    public String write(DefectiveCodeVo vo, HttpSession session) throws Exception {
        System.out.println(vo);
        int result = service.write(vo);
        if(result == 1){
            return "redirect:/qa/defectivecode/list";
        }else{
            throw new Exception("redirect:/error");
        }
    }

    //불량 코드 삭제
    @DeleteMapping("delete")
    @ResponseBody
    public String delete(String defectiveCodeNoArr)throws JsonProcessingException {
        List<String> defectiveCodeNoList = objectMapper.readValue(defectiveCodeNoArr, List.class);
        int result = service.delete(defectiveCodeNoList);

        if(result == 0){
            return "bad";
        }
        return "good";
    }


    //상품 수정(화면)
    @GetMapping("edit")
    public void edit(String defectiveCodeNo, Model model){
        DefectiveCodeVo defectiveCodeVo  = service.getDefectiveCodeDetail(defectiveCodeNo);
        model.addAttribute("defectiveCodeVo",defectiveCodeVo);
    }


    //상품 수정(처리)
    @PostMapping("edit")
    public String edit(DefectiveCodeVo vo, Model model) throws Exception {


        int result = service.edit(vo);

        if(result != 1){
            throw new IllegalStateException("수정하기 중 에러...");
        }

        DefectiveCodeVo defectiveCodeVo = service.getDefectiveCodeDetail(vo.getNo());
        model.addAttribute("defectiveCodeVo", defectiveCodeVo);
        return "redirect:/qa/defectivecode/list";

    }



}
