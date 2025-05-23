package com.kh.semi.defective.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.semi.defective.service.DefectiveService;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.defective.vo.DefectiveVo;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.util.page.PageVo;
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
    public String list(Model model,
                       @RequestParam(value = "searchValue", required = false) String searchValue,
                       @RequestParam(value = "searchValueError", required = false) String searchValueError,
                       @RequestParam(name = "pno", defaultValue = "1") int currentPage,
                       HttpSession session) {

        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        int listCount = service.getDefectiveCnt();
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pageVo = new PageVo(listCount , currentPage, pageLimit, boardLimit);
        List<DefectiveVo> defectiveVo = service.getDefective(searchValue, searchValueError, pageVo);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("defectiveVo", defectiveVo);

        return "qa/defective/list";
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

    //불량 코드 받아오기
    @GetMapping("dclist")
    @ResponseBody
    public List<DefectiveCodeVo> dclist(){
        return service.getdefectiveCodeVoList();
    }

    //불량 코드 받아와서 불량명 출력
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

    //불량 상품 수정(화면)
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
