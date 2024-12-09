package com.kh.semi.qa.asemp.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;

import com.kh.semi.qa.areainfo.AreaInfo;
import com.kh.semi.qa.asemp.service.AsempService;
import com.kh.semi.qa.asemp.vo.AsempVo;
import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.qa.asemp.vo.DeptVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
                               String area, String searchType, String searchValue, HttpSession session) throws Exception
    {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // pno = currentPage
        int listCount = service.getAsempListCnt(area, searchType, searchValue);
        int pageLimit = 10;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<AsempVo> asempVoList = service.getAsempList(model, pvo, area, searchType, searchValue);

        if(asempVoList == null) {
            String errCode = "[ASEMP_001] AS 담당자 등록에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        model.addAttribute("asempVoList", asempVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("area", area);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        // 지역 정보 가져오기
        AreaInfo areaInfo = new AreaInfo();
        model.addAttribute("areaList", areaInfo.getAreaInfo());

        // 부서 목록 가져오기
        List<DeptVo> deptVoList = service.getDeptList(model);
        model.addAttribute("deptVoList", deptVoList);

        return "qa/asemp/list";
    }

    // 사원 검색 목록 조회
    @GetMapping("emplist")
    @ResponseBody
    public HashMap getEmpList(@RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                           String dept, String empSearchType, String empSearchValue) {

        // pno = currentPage
        int listCount = service.getEmpCnt(dept, empSearchType, empSearchValue);
        int pageLimit = 5;
        int boardLimit = 10;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<EmployeeVo> empVoList = service.getEmpList(pvo, dept, empSearchType, empSearchValue);

        HashMap map = new HashMap();
        map.put("a", empVoList);
        map.put("b", pvo);

        return map;

    }

    // AS 담당자 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsempVo getAsempDetail(String no) throws Exception {

        AsempVo asempVo = service.getAsempDetail(no);

        if(asempVo == null) {
            String errCode = "[ASEMP_003] AS 담당자 상세정보를 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return asempVo;
    }

    // AS 담당자 등록
    @PostMapping("write")
    @ResponseBody
    public int enroll(AsempVo vo) throws Exception {

        int result = service.enroll(vo);

        if(result != 1) {
            String errCode = "[ASEMP_001] AS 담당자 등록에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }

    // AS 담당자 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(AsempVo vo) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            String errCode = "[ASEMP_004] AS 담당자 수정에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }

    // AS 담당자 삭제
    @PostMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result < 1) {
            String errCode = "[ASEMP_005] AS 담당자 삭제에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }
}
