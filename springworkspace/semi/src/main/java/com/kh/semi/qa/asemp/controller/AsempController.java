package com.kh.semi.qa.asemp.controller;

import com.kh.semi.qa.asemp.service.AsempService;
import com.kh.semi.qa.asemp.vo.AsempVo;
import com.kh.semi.hr.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("qa/asemp")
@RequiredArgsConstructor
@Slf4j
public class AsempController {

    private final AsempService service;

    // AS 담당자 목록 조회
    @GetMapping("list")
    public String getAsempList(Model model) {

        List<AsempVo> asempVoList = service.getAsempList(model);

        if(asempVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("asempVoList", asempVoList);

        return "qa/asemp/list";
    }

    // AS 담당자 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsempVo getAsempDetail(String no, Model model) {

        AsempVo asempVo = service.getAsempDetail(no, model);

        model.addAttribute("asempVo", asempVo);
        System.out.println("asempVo = " + asempVo);

        return asempVo;
    }

    // 사원 조회 (화면)
    @GetMapping("emplist")
    @ResponseBody
    public List<EmployeeVo> getEmpList() {

        List<EmployeeVo> empVoList = service.getEmpList();

        return empVoList;
    }

    // AS 담당자 등록 (처리)
    @PostMapping("enroll")
    public String enroll(AsempVo vo, HttpSession session) throws Exception {

        System.out.println("vo = " + vo);

        int result = service.enroll(vo);

        if(result != 1) {
            throw new Exception("Error");
        }

        session.setAttribute("alertMsg", "등록되었습니다.");
        return "redirect:/qa/asemp/list";

    }

    // AS 담당자 수정 (화면)
    @GetMapping("edit")
    @ResponseBody
    public AsempVo edit(String no, Model model) {

        AsempVo asempVo = service.getAsempDetail(no, model);

        if (asempVo == null) {
            throw new IllegalStateException("ERROR");
        }

        return asempVo;
    }

    // AS 담당자 수정 (처리)
    @PostMapping("edit")
    public String edit(AsempVo vo, HttpSession session, Model model) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            throw new Exception("Error");
        }

        session.setAttribute("alertMsg", "수정되었습니다.");

        return "redirect:/qa/asemp/list";
    }

    // AS 담당자 삭제
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
