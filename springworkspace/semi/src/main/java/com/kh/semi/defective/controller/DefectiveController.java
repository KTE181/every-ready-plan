package com.kh.semi.defective.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.semi.defective.service.DefectiveService;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.defective.vo.DefectiveVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("qa/defective")
public class DefectiveController {

    private final DefectiveService service;
    private final ObjectMapper objectMapper;

    //상품 목록 조회
    @GetMapping("list")
    public void list(Model model,
                     @RequestParam(value = "searchValue", required = false) String searchValue,
                     @RequestParam(value = "searchValueError", required = false) String searchValueError) {
        List<DefectiveVo> defectiveVo = service.getDefective(searchValue, searchValueError);
        model.addAttribute("defectiveVo", defectiveVo);
    }

    //상품 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public DefectiveVo getDefectiveDetail(@RequestParam("no") String defectiveNo) {
        DefectiveVo defectiveVo = service.getDefectiveDetail(defectiveNo);

        if (defectiveVo == null) {
            throw new IllegalStateException();
        }
        return defectiveVo;
    }

    @GetMapping("write")
    public String write(Model model){
        List<DefectiveCodeVo> defectiveCodeList =service.getdefectiveCodeVoList();
        model.addAttribute("defectiveCodeList",defectiveCodeList);
        return "qa/defective/write";
    }

    @GetMapping("dclist")
    @ResponseBody
    public List<DefectiveCodeVo> dclist(){
        return service.getdefectiveCodeVoList();
    }

    @GetMapping("/getDefectiveName")
    @ResponseBody
    public DefectiveCodeVo getDefectiveName(@RequestParam("code") String defectiveCode) {
        return service.getDefectiveName(defectiveCode);
    }

    // 상품 등록 처리
    @PostMapping("write")
    @ResponseBody
    public String write(DefectiveVo vo, HttpSession session) throws Exception {
        int result = service.write(vo);
        if(result == 1){
            session.setAttribute("alertMsg","작성되었습니다.");
            return "redirect:/qa/defective/list";
        }else{
            throw new Exception("redirect:/error");
        }

    }

    //불량 상품 삭제
    @DeleteMapping("delete")
    @ResponseBody
    public String delete(String defectiveNoArr)throws JsonProcessingException {
        List<String> defectiveNoList = objectMapper.readValue(defectiveNoArr, List.class);
        int result = service.delete(defectiveNoList);

        if(result == 0){
            return "bad";
        }
            return "good";
    }

    @GetMapping("edit")
    public void edit(String defectiveNo, Model model){
        DefectiveVo defectiveVo  = service.getDefectiveDetail(defectiveNo);
        model.addAttribute("defectiveVo", defectiveVo);
    }


    //상품 수정(처리)
    @PostMapping("edit")
    public String edit(DefectiveVo vo, Model model) throws Exception {

        int result = service.edit(vo);

        if(result != 1){
            throw new IllegalStateException("수정하기 중 에러...");
        }

        DefectiveVo defectiveVo = service.getDefectiveDetail(vo.getNo());
        model.addAttribute("defectiveVo", defectiveVo);
        return "redirect:/qa/defective/list";

    }


}
