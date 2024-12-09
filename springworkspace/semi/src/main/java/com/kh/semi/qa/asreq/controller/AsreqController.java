package com.kh.semi.qa.asreq.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.areainfo.AreaInfo;
import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.qa.asreq.service.AsreqService;
import com.kh.semi.qa.inspection.vo.ItemProductVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("qa/asreq")
@RequiredArgsConstructor
@Slf4j
public class AsreqController {

    private final AsreqService service;

    // AS 요청 목록 조회
    @GetMapping("list")
    public String getAsreqList(Model model, @RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                               String area, String searchType, String searchValue, HttpSession session) throws Exception {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // pno = currentPage
        int listCount = service.getAsreqListCnt(area, searchType, searchValue);
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<AsreqVo> asreqVoList = service.getAsreqList(model, pvo, area, searchType, searchValue);

        if(asreqVoList == null) {
            String errCode = "[ASREQ_002] AS 요청 목록을 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        model.addAttribute("asreqVoList", asreqVoList);
        model.addAttribute("pvo", pvo);
        model.addAttribute("area", area);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchValue", searchValue);

        // 지역 정보 가져오기
        AreaInfo areaInfo = new AreaInfo();
        model.addAttribute("areaList", areaInfo.getAreaInfo());

        return "qa/asreq/list";
    }

    // 상품 검색 목록 조회
    @GetMapping("productlist")
    @ResponseBody
    public HashMap getProductList(@RequestParam(name="pno", defaultValue="1", required = false) int currentPage,
                                  String productSearchType, String productSearchValue)
    {
        // pno = currentPage
        int listCount = service.getProductCnt(productSearchType, productSearchValue);
        int pageLimit = 5;
        int boardLimit = 10;

        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<ItemProductVo> productVoList = service.getProductList(pvo, productSearchType, productSearchValue);

        HashMap map = new HashMap();
        map.put("a", productVoList);
        map.put("b", pvo);

        return map;
    }

    // AS 요청 등록
    @PostMapping("write")
    @ResponseBody
    public int write(AsreqVo vo) throws Exception {
        int result = service.write(vo);
        if(result != 1) {
            String errCode = "[ASREQ_001] AS 요청 등록에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }
        return result;
    }

    // AS 요청 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsreqVo getAsreqDetail(String asreqNo) throws Exception {

        AsreqVo asreqVo = service.getAsreqDetail(asreqNo);

        if(asreqVo == null) {
            String errCode = "[ASREQ_003] AS 요청 상세정보를 불러올 수 없습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return asreqVo;
    }

    // AS 요청 접수
    @GetMapping("receive")
    @ResponseBody
    public int receive(String no) throws Exception {

        int result = service.receive(no);

        if(result != 1) {
            String errCode = "[ASREQ_004] AS 요청 접수에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }

    // AS 요청 수정
    @PostMapping("edit")
    @ResponseBody
    public int edit(AsreqVo vo) throws Exception {

        int result = service.edit(vo);

        if(result != 1) {
            String errCode = "[ASREQ_005] AS 요청 수정에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }

    // AS 요청 삭제
    @PostMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result < 1) {
            String errCode = "[ASREQ_006] AS 요청 삭제에 실패했습니다.";
            log.warn(errCode);
            throw new Exception(errCode);
        }

        return result;
    }

}
