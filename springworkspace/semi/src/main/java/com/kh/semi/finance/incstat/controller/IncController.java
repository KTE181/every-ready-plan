package com.kh.semi.finance.incstat.controller;

import com.kh.semi.finance.incstat.service.IncService;
import com.kh.semi.finance.incstat.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("fi/inc/")
public class IncController {

    private final IncService service;

//    // 게시글 목록 조회 화면
//    @GetMapping("list")
//    public String list(Model model) {
//        List<IncVo> incVoList = service.getIncVoList(); // IncService에서 데이터를 조회
//        model.addAttribute("incVoList", incVoList); // 모델에 incVoList를 추가
//        return "finance/incStat/list"; // incStat/list 뷰를 반환
//    }
//
//    // 게시글 목록 조회 데이터 (AJAX 요청에 대한 처리)
//    @GetMapping("list/data")
//    @ResponseBody
//    public HashMap getBoardVoList(Model model) {
//        List<IncVo> incVoList = service.getIncVoList(); // IncService에서 데이터를 조회
//        HashMap map = new HashMap<>();
//        System.out.println("incVoList = " + incVoList);
//        System.out.println("map = " + map);
//        System.out.println("service = " + service);
//        map.put("a", incVoList); // "a" 키로 게시글 목록을 반환
//        return map; // 클라이언트에게 데이터를 반환
//
////        IncVo vo = IncService.getIncVoList(); // 데이터를 가져오는 서비스 호출
////        model.addAttribute("vo", vo); // JSP로 전달
////        return "finance/incStat/list";
//    }

    @GetMapping("detail")
    public String detail(String no, Model model){
        IncVo vo = service.getIncByNo(no);
        model.addAttribute("vo", vo);
        return "finance/incStat/detail";
    }


}