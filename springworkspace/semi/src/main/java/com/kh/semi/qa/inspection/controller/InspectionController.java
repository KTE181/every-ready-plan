package com.kh.semi.qa.inspection.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.inspection.service.InspectionService;
import com.kh.semi.qa.inspection.vo.InspectionTypeVo;
import com.kh.semi.qa.inspection.vo.InspectionVo;
import com.kh.semi.qa.inspection.vo.InspectionStatusVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("qa/inspection")
@Slf4j
public class InspectionController {

    private final InspectionService service;

    // 품질 검사 목록 조회
    @GetMapping("list")
    public String getInspectionList(Model model, @RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                                    String inspectionType, String status, String passYn, String searchType, String searchValue, HttpSession session) throws Exception {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // pno = currentPage
        int listCount = service.getInspectionListCnt(inspectionType, status, passYn, searchType, searchValue);
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<InspectionVo> inspectionVoList = service.getInspectionList(model, pvo, inspectionType, status, passYn, searchType, searchValue);

        if(inspectionVoList == null) {
            String errCode = "[INSPECTION_002] 품질 검사 목록을 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        model.addAttribute("inspectionVoList", inspectionVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("inspectionType", inspectionType);
        model.addAttribute("status", status);
        model.addAttribute("passYn", passYn);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        getStatus(model);
        getType(model);

        return "qa/inspection/list";
    }

    // 진행상태 가져오기
    public void getStatus(Model model) {
        List<InspectionStatusVo> statusVoList =service.getStatusList(model);
        model.addAttribute("statusVoList", statusVoList);
    }

    // 검사유형 가져오기
    public void getType(Model model) {
        List<InspectionTypeVo> typeVoList =service.getInspectionTypeList(model);
        model.addAttribute("typeVoList", typeVoList);
    }

    // 품질 검사 등록
    @PostMapping("write")
    @ResponseBody
    public int write(InspectionVo vo) throws Exception {

        int result = service.write(vo);

        if(result != 1) {
            String errCode = "[INSPECTION_001] 품질 검사 등록에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;

    }

    // 품질 검사 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public InspectionVo getinspectionDetail(String no) throws Exception {

        InspectionVo inspectionVo = service.getinspectionDetail(no);

        if(inspectionVo == null) {
            String errCode = "[INSPECTION_003] 품질 검사 상세정보를 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return inspectionVo;
    }

    // 품질 검사 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(InspectionVo vo) throws Exception {

        int result = service.edit(vo);

        if(result != 1) {
            String errCode = "[INSPECTION_004] 품질 검사 수정에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;

    }

    // 품질 검사 삭제
    @PostMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result < 1) {
            String errCode = "[INSPECTION_005] 품질 검사 삭제에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }
}
