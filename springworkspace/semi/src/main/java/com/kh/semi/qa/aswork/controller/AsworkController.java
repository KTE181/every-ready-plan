package com.kh.semi.qa.aswork.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.qa.aswork.service.AsworkService;
import com.kh.semi.qa.aswork.vo.AsworkStatusVo;
import com.kh.semi.qa.aswork.vo.AsworkVo;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
import com.kh.semi.qa.inspection.vo.InspectionStatusVo;
import com.kh.semi.qa.inspection.vo.InspectionTypeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("qa/aswork")
@RequiredArgsConstructor
@Slf4j
public class AsworkController {

    private final AsworkService service;

    // AS 작업 목록 조회
    @GetMapping("list")
    public String getAsworkList(Model model, @RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                                String area, String status, String type, String searchType, String searchValue, HttpSession session)
    {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // pno = currentPage
        int listCount = service.getAsworkListCnt(area, status, type, searchType, searchValue);
        int pageLimit = 10;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<AsworkVo> asworkVoList = service.getAsworkList(model, pvo, area, status, type, searchType, searchValue);

        if (asworkVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("asworkVoList", asworkVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("area", area);
        model.addAttribute("status", status);
        model.addAttribute("type", type);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        List<AsworkStatusVo> statusVoList =service.getStatusList(model);
        model.addAttribute("statusVoList", statusVoList);

        List<FaultcodeVo> typeVoList =service.getTypeList(model);
        model.addAttribute("typeVoList", typeVoList);

        System.out.println("typeVoList = " + typeVoList);

        return "qa/aswork/list";
    }

    // AS 작업 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsworkVo getAsworkDetail(String asworkNo, Model model) {

        AsworkVo asworkVo = service.getAsworkDetail(asworkNo, model);

        if (asworkVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("asworkVo", asworkVo);

        return asworkVo;
    }

    // AS 작업 수정 (화면)
    @GetMapping("edit")
    @ResponseBody
    public AsworkVo edit(String asworkNo, Model model) {

        AsworkVo asworkVo = service.getAsworkDetail(asworkNo, model);

        if (asworkVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("asworkVo", asworkVo);

        return asworkVo;
    }

    // AS 작업 수정 (처리)
    @PostMapping("edit")
    public String edit(AsworkVo vo, HttpSession session, Model model) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            throw new Exception("Error");
        }

        session.setAttribute("alertMsg", "수정되었습니다.");

        AsworkVo asworkVo = service.getAsworkDetail(vo.getNo(), model);
        model.addAttribute("asworkVo", asworkVo);

        return "redirect:/qa/aswork/list";
    }

    // AS 작업 삭제
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