package com.kh.semi.defective.controller;

import com.kh.semi.defective.service.DefectiveCodeService;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("qa/defectivecode")
public class DefectiveCodeController {

    private final DefectiveCodeService service;

    @GetMapping("list")
    public void list(Model model){
        List<DefectiveCodeVo> defectiveCodeVo= service.getDefectiveCode();
        model.addAttribute("defectiveCodeVo", defectiveCodeVo);
    }

    @GetMapping("detail")
    public void detail(Model model){
        List<DefectiveCodeVo> dcVoList = service.getdcVoList();
        model.addAttribute("dcVoList", dcVoList);
    }
}
