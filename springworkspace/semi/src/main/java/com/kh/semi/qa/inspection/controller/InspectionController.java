package com.kh.semi.qa.inspection.controller;

import com.kh.semi.qa.inspection.service.InspectionService;
import com.kh.semi.qa.inspection.vo.InspectionVo;
import com.kh.semi.qa.inspection.vo.StatusVo;
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
@RequiredArgsConstructor
@RequestMapping("qa/inspection")
@Slf4j
public class InspectionController {

    private final InspectionService service;

    // 품질 검사 목록 조회
    @GetMapping("list")
    public String getInspectionList(Model model) {

        List<InspectionVo> inspectionVoList = service.getInspectionList(model);

        if(inspectionVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("inspectionVoList", inspectionVoList);
        System.out.println("inspectionVoList = " + inspectionVoList);

        return "qa/inspection/list";
    }

    // 진행상태 옵션 조회
    @GetMapping("statuslist")
    @ResponseBody
    public StatusVo getStatusList(Model model) throws Exception {
        StatusVo voList = service.getStatusList(model);

        if(voList == null) {
            throw new Exception("Error");
        }

        return voList;
    }

    // 품질 검사 등록
    @PostMapping("write")
    @ResponseBody
    public int write(InspectionVo vo) throws Exception {

        int result = service.write(vo);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;

    }

    // 품질 검사 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public InspectionVo getinspectionDetail(String no, Model model) throws Exception {

        InspectionVo inspectionVo = service.getinspectionDetail(no, model);

        if(inspectionVo == null) {
            throw new Exception("Error");
        }

        return inspectionVo;
    }

    // 품질 검사 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(InspectionVo vo) throws Exception {
        System.out.println("수정 로직 호출됨~~~");
        System.out.println("vo = " + vo);
        int result = service.edit(vo);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;

    }

    // 품질 검사 삭제
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
