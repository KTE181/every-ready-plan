package com.kh.semi.finance.partner.controller;

import com.kh.semi.finance.partner.service.PartnerService;
import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pb.vo.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("finance/partner")
@RequiredArgsConstructor
@Slf4j
public class PartnerController {

    private final PartnerService service;

//  게시글 작성(화면)
    @GetMapping("write")
    public String write(){
        return "finance/partner/write";
    }

    //게시글 작성(데이터)
    @PostMapping("write")
    public String write(PartnerVo vo , HttpSession session) throws Exception{
        System.out.println("vo = " + vo);

        int result = service.write(vo);

        if(result != 1) {
            throw new Exception("거래처 등록 오류");
        }
        session.setAttribute("alertMsg" , "거래처 등록");
        return "redirect:/finance/partner/list ";

    }

//    // 거래처 리스트 조회 origin
//    @GetMapping("list")
//    public String getPartnerList(Model model) {
//
//        List<PartnerVo> partnerVoList = service.getPartnerList(model);
//
//        if(partnerVoList == null) {
//            return "redirect:/error";
//        }
//
//        model.addAttribute("partnerVoList" , partnerVoList);
//        System.out.println("partnerVoList = " + partnerVoList);
//
//        return "finance/partner/list";
//
//    }

    // 거래처 리스트 조회
    @GetMapping("list")
    public String getPartnerList(
            @RequestParam(name = "pno", defaultValue = "1") int currentPage,
            @RequestParam(name = "area", required = false) String area,
            @RequestParam(name = "searchValue", required = false) String searchValue,
            Model model ,HttpSession session) {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        if(loginEmployeeVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        // 검색 조건 기본값 설정
        boolean isSearch = !(area == null || area.isBlank()) && !(searchValue == null || searchValue.isBlank());
        area = (area == null) ? "" : area.trim();
        searchValue = (searchValue == null) ? "" : searchValue.trim();


        // 데이터 개수 가져오기
        int listCount = isSearch
                ? service.getPartnerListCnt(area, searchValue) // 검색 조건 있을 때
                : service.getTotalPartnerCount(); // 검색 조건 없을 때

        int pageLimit = 5;  // 하단 페이지 번호 개수
        int boardLimit = 14; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<PartnerVo> partnerVoList = isSearch
                ? service.getPartnerList(pageVo , area, searchValue)// 검색 조건 있을
                : service.getAllPartner(pageVo);// 검색 조건 없을 때

        if(partnerVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("partnerVoList" , partnerVoList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("area", area);
        model.addAttribute("searchValue", searchValue);
//        System.out.println("partnerVoList = " + partnerVoList);

        return "finance/partner/list";

    }



    // 거래처 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public PartnerVo getPartnerDetail(String no , Model model) {

        PartnerVo partnerVo = service.getPartnerDetail(no , model);

        if(partnerVo == null) {
            throw new IllegalStateException("ERROR");
        }
        System.out.println("요청받은 no: " + no);
        model.addAttribute("partnerVo" , partnerVo);
        System.out.println("partnerVo = " + partnerVo);

        return partnerVo;

    }

    //거래처 수정(화면)
    @GetMapping("edit")
    @ResponseBody
    public PartnerVo edit(String no, Model model) throws Exception {

        PartnerVo partnerVo = service.getPartnerDetail(no , model);


        System.out.println("partnerVo = " + partnerVo);

        if(partnerVo == null) {
            throw new IllegalStateException("ERROR");
        }

        model.addAttribute("partnerVo" , partnerVo);


        return partnerVo;
    }
    //거래처 수정(데이터)
    @PostMapping("edit")
    public String edit (PartnerVo vo, HttpSession session , Model model) throws Exception {

        int result = service.edit(vo);

        if (result != 1) {
            throw new Exception("error");
        }

        session.setAttribute("alertMsg", "partner 수정완료.");

        PartnerVo partnerVo = service.getPartnerDetail(vo.getNo(), model);
        model.addAttribute("partnerVo", partnerVo);

        return "redirect:/finance/partner/list";
    }


    @GetMapping("delete")
    public String delete(@RequestParam("no") String no ,HttpSession session) throws Exception {

        System.out.println("no = " + no);

        int result = service.delete(no);

        if (result != 1) {
            throw new Exception("error");
        }

            session.setAttribute("alertMsg", "partner 데이터 삭제완료");
            return "redirect:/finance/partner/list";
    }

    @PostMapping("deleteMultiple")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteMultiple(@RequestBody List<String> partnerIds) {
        if (partnerIds == null || partnerIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "삭제할 항목이 없습니다."));
        }

        System.out.println("삭제 요청 받은 partnerIds: " + partnerIds);
        int result = service.deleteMultiplePartners(partnerIds);

        if (result > 0) {
            return ResponseEntity.ok(Map.of("message", "선택된 항목이 삭제되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "삭제에 실패했습니다."));
        }
    }

}

