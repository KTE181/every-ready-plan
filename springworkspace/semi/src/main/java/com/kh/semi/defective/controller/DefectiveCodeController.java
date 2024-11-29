package com.kh.semi.defective.controller;

import com.kh.semi.defective.service.DefectiveCodeService;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.product.vo.ProductVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("qa/defectivecode")
public class DefectiveCodeController {

    private final DefectiveCodeService service;

    //불량 코드 목록 조회
    @GetMapping("list")
    public void list(Model model){
        List<DefectiveCodeVo> defectiveCodeVo= service.getDefectiveCode();
        model.addAttribute("defectiveCodeVo", defectiveCodeVo);
    }

    //불량 코드 상세 조회
    @GetMapping("detail")
    public void detail(Model model, String bno){
        List<DefectiveCodeVo> dcVoList = service.getdcVoList(bno, model);
        model.addAttribute("dcVoList", dcVoList);
    }

    //불량 코드 등록(화면)
    @GetMapping("write")
    public String write(){
        return "qa/defectiveCode/write";
    }

    //불량 코드 등록(처리)
    @PostMapping("write")
    public void write(DefectiveCodeVo vo, HttpSession session) throws Exception {
        int result = service.write(vo);
        if(result == 1){
            session.setAttribute("alertMsg","등록되었습니다.");
        }else{
            throw new Exception("redirect:/error");
        }
    }

    //불량 코드 삭제
    @GetMapping("delete")
    public String delete(String bno , HttpSession session){
        int result = service.delete(bno);

        if(result != 1){
            throw new IllegalStateException("게시글 삭제 실패 ...");
        }

        session.setAttribute("alertMsg" , "게시글 삭제 성공 !");
        return "redirect:/qa/defectivecode/list";
    }

    //불량 코드 수정 (화면)
    @GetMapping("edit")
    public String edit(String bno, Model model) throws Exception {
        List<DefectiveCodeVo> dcVoList = service.getdcVoList(bno, model);

        if (dcVoList == null){
            throw new Exception("redirect:/error");
        }else{
            model.addAttribute("dcVoList",dcVoList);
            return "redirect:/qa/defectiveCode/edit";
        }
    }

    @PostMapping("edit")
    public String edit(DefectiveCodeVo vo, HttpSession session, Model model){
        int result = service.edit(vo);

        if(result != 1){
            return "redirect:/error";
        }else{
            session.setAttribute("alertMsg","수정 완료 되었습니다.");

            List<DefectiveCodeVo> defectiveCodeVoList = service.getdcVoList(vo.getNo(), model);
            model.addAttribute("defectiveCodeVoList", defectiveCodeVoList);

            return "qa/defectiveCode/detail";
        }

    }

}
