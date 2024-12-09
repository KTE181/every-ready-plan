package com.kh.semi.qa.aswork.controller;

import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.areainfo.AreaInfo;
import com.kh.semi.qa.asemp.vo.AsempVo;
import com.kh.semi.qa.aswork.service.AsworkService;
import com.kh.semi.qa.aswork.vo.AsworkStatusVo;
import com.kh.semi.qa.aswork.vo.AsworkVo;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
                                String area, String status, String type, String searchType, String searchValue, HttpSession session) throws Exception {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // pno = currentPage
        int listCount = service.getAsworkListCnt(area, status, type, searchType, searchValue);
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<AsworkVo> asworkVoList = service.getAsworkList(model, pvo, area, status, type, searchType, searchValue);

        if (asworkVoList == null) {
            String errCode = "[ASWORK_001] AS 작업 목록을 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        model.addAttribute("asworkVoList", asworkVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("area", area);
        model.addAttribute("status", status);
        model.addAttribute("type", type);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        AreaInfo areaInfo = new AreaInfo();
        model.addAttribute("areaList", areaInfo.getAreaInfo());

        List<AsworkStatusVo> statusVoList =service.getStatusList(model);
        model.addAttribute("statusVoList", statusVoList);

        List<FaultcodeVo> typeVoList =service.getTypeList(model);
        model.addAttribute("typeVoList", typeVoList);

        return "qa/aswork/list";
    }
    
    // AS 담당자 조회
    @GetMapping("asemplist")
    @ResponseBody
    public HashMap getEmpList(@RequestParam(name="pno", defaultValue="1", required = false) int currentPage, String area) {

        // pno = currentPage
        int listCount = service.getAsempCnt(area);
        int pageLimit = 5;
        int boardLimit = 10;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<AsempVo> empVoList = service.getAsempList(pvo, area);

        HashMap map = new HashMap();
        map.put("a", empVoList);
        map.put("b", pvo);

        return map;

    }

    // AS 작업 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsworkVo getAsworkDetail(String asworkNo) throws Exception {

        AsworkVo asworkVo = service.getAsworkDetail(asworkNo);

        if (asworkVo == null) {
            String errCode = "[ASWORK_002] AS 작업 상세정보를 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }
        System.out.println("asworkVo = " + asworkVo);
        return asworkVo;
    }

    // AS 작업 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(AsworkVo vo) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            String errCode = "[ASWORK_003] AS 작업 수정에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }

    // AS 작업 삭제
    @PostMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result < 1) {
            String errCode = "[ASWORK_004] AS 작업 삭제에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }
}