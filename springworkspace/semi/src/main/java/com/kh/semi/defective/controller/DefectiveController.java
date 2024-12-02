package com.kh.semi.defective.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.semi.defective.service.DefectiveService;
import com.kh.semi.defective.vo.DefectiveVo;
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
    @GetMapping("/detail")
    public ResponseEntity<DefectiveVo> getProductDetail(@RequestParam("no") String defectiveNo) {

        DefectiveVo defectiveVo = service.getDefectiveByNo(defectiveNo);
        if (defectiveVo != null) {
            return ResponseEntity.ok(defectiveVo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 상품 등록 처리
    @PostMapping("write")
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

    //불량상품 수정(화면)
    @GetMapping("edit")
    public String edit(String bno, Model model){
        List<DefectiveVo> productVo = service.getDefectiveDetail(bno, model);

        if(productVo == null){
            return "redirect:/error";
        }else{
            model.addAttribute("productVo",productVo);
            return "redirect:/qa/product/edit";
        }
    }

    //불량상품 수정(출력)
    @PostMapping("edit")
    public String edit(Model model, HttpSession session, DefectiveVo vo){

            int result = service.edit(vo);

            if(result != 1){
                return "redirect:/error";
            }else{
                session.setAttribute("alertMsg","수정완료되었습니다.");
                List<DefectiveVo> defectiveVo = service.getDefectiveDetail(vo.getNo(), model);

                return "redirect:/qa/defective/edit";
            }

    }


}
