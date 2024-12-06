package com.kh.semi.qa.asemp.controller;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asemp.service.AsempService;
import com.kh.semi.qa.asemp.vo.AsempVo;
import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("qa/asemp")
@RequiredArgsConstructor
@Slf4j
public class AsempController {

    private final AsempService service;

    // AS 담당자 목록 조회
    @GetMapping("list")
    public String getAsempList(Model model, @RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                               String area, String searchType, String searchValue)
    {
        // pno = currentPage
        int listCount = service.getAsempListCnt(area, searchType, searchValue);
        int pageLimit = 10;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<AsempVo> asempVoList = service.getAsempList(model, pvo, area, searchType, searchValue);

        if(asempVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("asempVoList", asempVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("area", area);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        getEmpList(model);

        return "qa/asemp/list";
    }

    // 사원 목록 가져오기
    @GetMapping("data/emp")
    public void getEmpList(Model model) {
        List<EmployeeVo> empVoList = service.getEmpList();
        model.addAttribute("empVoList", empVoList);
    }

    // AS 담당자 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsempVo getAsempDetail(String no, Model model) {

        AsempVo asempVo = service.getAsempDetail(no, model);

        model.addAttribute("asempVo", asempVo);

        return asempVo;
    }

    // AS 담당자 등록
    @PostMapping("write")
    @ResponseBody
    public int enroll(AsempVo vo) throws Exception {

        int result = service.enroll(vo);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;
    }

    // AS 담당자 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(AsempVo vo) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            throw new Exception("Error");
        }

        return result;
    }

    // AS 담당자 삭제
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
