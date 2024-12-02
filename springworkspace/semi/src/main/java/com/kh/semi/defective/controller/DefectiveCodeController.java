package com.kh.semi.defective.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.semi.defective.service.DefectiveCodeService;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.product.vo.ProductVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
                     @RequestParam(value = "searchValueName", required = false) String searchValueName) {
        List<DefectiveCodeVo> defectiveCodeVo = service.getDefectiveCodeList(searchValue, searchValueName);
        model.addAttribute("defectiveCodeVo", defectiveCodeVo);
    }

    //불량 코드 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<DefectiveCodeVo> getDefectiveCodeDetail(@RequestParam("no") String defectiveCodeNo) {
        DefectiveCodeVo defectiveCode = service.getCodeByNo(defectiveCodeNo);
        if (defectiveCode != null) {
            return ResponseEntity.ok(defectiveCode);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //불량 코드 등록(처리)
    @PostMapping("write")
    public void write(DefectiveCodeVo vo, HttpSession session) throws Exception {
        System.out.println(vo);
        int result = service.write(vo);
        if(result == 1){
            session.setAttribute("alertMsg","등록되었습니다.");
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


    //불량 코드 수정 (화면)
    @GetMapping("edit")
    public String edit(String bno, Model model) throws Exception {
        List<DefectiveCodeVo> dcVoList = service.getdcVoList(bno, model);

        if (dcVoList == null){
            throw new Exception("redirect:/error");
        }else{
            model.addAttribute("dcVoList",dcVoList);
            return "redirect:/qa/defectiveCode/edit";
        }
    }

    @PostMapping("edit")
    public String edit(DefectiveCodeVo vo, HttpSession session, Model model){
        int result = service.edit(vo);

        if(result != 1){
            return "redirect:/error";
        }else{
            session.setAttribute("alertMsg","수정 완료 되었습니다.");

            List<DefectiveCodeVo> defectiveCodeVoList = service.getdcVoList(vo.getNo(), model);
            model.addAttribute("defectiveCodeVoList", defectiveCodeVoList);

            return "qa/defectiveCode/detail";
        }

    }

}
