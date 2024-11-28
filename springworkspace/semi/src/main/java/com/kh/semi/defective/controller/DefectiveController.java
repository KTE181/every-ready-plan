package com.kh.semi.defective.controller;

import com.kh.semi.defective.service.DefectiveService;
import com.kh.semi.defective.vo.DefectiveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("qa/defective")
public class DefectiveController {

    private final DefectiveService service;
    @GetMapping("list")
    public void list(Model model){
        List<DefectiveVo> defectiveVo = service.getDefective();
        model.addAttribute("defectiveVo", defectiveVo);
    }

    @GetMapping("detail")
    public void detail(Model model){
        List<DefectiveVo> voList = service.getDefectiveDetail();
        model.addAttribute("voList", voList);
    }

}
