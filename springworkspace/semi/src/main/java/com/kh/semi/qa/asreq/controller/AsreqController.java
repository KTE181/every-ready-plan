package com.kh.semi.qa.asreq.controller;

import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.qa.asreq.service.AsreqService;
import com.kh.semi.product.vo.ProductVo;
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
@RequestMapping("qa/asreq")
@RequiredArgsConstructor
@Slf4j
public class AsreqController {

    private final AsreqService service;

    // AS 요청 등록
    @PostMapping("write")
    public String write(AsreqVo vo) throws Exception {
        int result = service.write(vo);
        if(result != 1) {
            throw new Exception("Error");
        }

        return "redirect:/qa/asreq/list";
    }

    // AS 요청 목록 조회
    @GetMapping("list")
    public String getAsreqList(Model model) {

        List<AsreqVo> asreqVoList = service.getAsreqList(model);

        if(asreqVoList == null) {
            return "redirect:/error";
        }

        model.addAttribute("asreqVoList", asreqVoList);
        System.out.println("voList = " + asreqVoList);

        return "qa/asreq/list";
    }

    // AS 요청 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public AsreqVo getAsreqDetail(String asreqNo, Model model) throws Exception {

        AsreqVo asreqVo = service.getAsreqDetail(asreqNo, model);

        if(asreqVo == null) {
            throw new Exception("Error");
        }

        return asreqVo;
    }

    // AS 요청 수정 (화면)
    @GetMapping("edit")
    @ResponseBody
    public AsreqVo edit(String asreqNo, Model model) throws Exception {

        AsreqVo asreqVo = service.getAsreqDetail(asreqNo, model);

        if(asreqVo == null) {
            throw new Exception("Error");
        }

        return asreqVo;
    }

    // AS 요청 수정
    @PostMapping("edit")
    public String edit(AsreqVo vo, Model model) throws Exception {

        int result = service.edit(vo);

        if(result != 1) {
            throw new Exception("Error");
        }

        AsreqVo asreqVo = service.getAsreqDetail(vo.getNo(), model);
        model.addAttribute("asreqVo", asreqVo);

        return "redirect:/qa/asreq/list";
    }

    // AS 요청 접수
    @GetMapping("receive")
    @ResponseBody
    public int receive(String no) throws Exception {

        int result = service.receive(no);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;
    }

    // AS 요청 삭제
    @GetMapping("delete")
    @ResponseBody
    public int delete(String no) throws Exception {

        int result = service.delete(no);

        if(result != 1) {
            throw new Exception("Error");
        }

        return result;
    }

    // 상품 조회
    @GetMapping("productlist")
    @ResponseBody
    public List<ProductVo> getEmpList() {

        List<ProductVo> productVoList = service.getProductList();

        return productVoList;
    }

}
