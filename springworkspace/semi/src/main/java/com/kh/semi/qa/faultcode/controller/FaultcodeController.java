package com.kh.semi.qa.faultcode.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.faultcode.service.FaultcodeService;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("qa/faultcode")
@RequiredArgsConstructor
@Slf4j
public class FaultcodeController {

    private final FaultcodeService service;

    // 고장코드 목록 조회
    @GetMapping("list")
    public String getFaultCodeList(Model model, @RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                                   String searchType, String searchValue, HttpSession session) throws Exception {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // pno = currentPage
        int listCount = service.getFaultCodeListCnt(searchType, searchValue);
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<FaultcodeVo> faultcodeVoList = service.getFaultCodeList(model, pvo, searchType, searchValue);

        if(faultcodeVoList == null) {
            String errCode = "[FAULTCODE_002] 고장 코드 목록을 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        model.addAttribute("faultcodeVoList", faultcodeVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        return "qa/faultcode/list";
    }

    // 고장코드 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public FaultcodeVo getFaultCodeDetail(String no) throws Exception {

        FaultcodeVo faultcodeVo = service.getFaultCodeDetail(no);

        if(faultcodeVo == null) {
            String errCode = "[FAULTCODE_003] 고장 코드 상세정보를 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return faultcodeVo;
    }

    // 고장코드 등록
    @PostMapping("write")
    @ResponseBody
    public int enroll(FaultcodeVo vo) throws Exception {

        int result = service.enroll(vo);

        if(result != 1) {
            String errCode = "[FAULTCODE_001] 고장 코드 등록에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;

    }

    // 고장코드 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(String no, String faultName) throws Exception {

        int result = service.edit(no, faultName);

        if(result != 1) {
            String errCode = "[FAULTCODE_004] 고장 코드 수정에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;

    }

    // 고장코드 삭제
    @PostMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result < 1) {
            String errCode = "[FAULTCODE_005] 고장 코드 삭제에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }

}
