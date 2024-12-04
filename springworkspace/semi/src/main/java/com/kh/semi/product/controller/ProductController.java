package com.kh.semi.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.semi.asreq.vo.AsreqVo;
import com.kh.semi.product.service.ProductService;
import com.kh.semi.product.vo.ProductVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("qa/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ObjectMapper objectMapper;

    //상품 목록 조회
    @GetMapping("list")
    public void list(Model model,
                     @RequestParam(value = "searchValue", required = false) String searchValue,
                     @RequestParam(value = "searchValueName", required = false) String searchValueName) {
        List<ProductVo> productVo = service.getProductList(searchValue, searchValueName);
        model.addAttribute("productVo", productVo);
    }

//    @GetMapping("/detail")
//    public ResponseEntity<ProductVo> getProductDetail(@RequestParam("no") String productNo) {
//        ProductVo product = service.getProductByNo(productNo);
//        if (product != null) {
//            return ResponseEntity.ok(product);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("detail")
    @ResponseBody
    public ProductVo getProductDetail(@RequestParam("no") String productNo) {
        ProductVo productVo = service.getProductDetail(productNo);
        if (productVo == null) {
            throw new IllegalStateException();
        }
        return productVo;
    }


    //상품 등록(처리)
    @PostMapping("write")
    public String write(ProductVo vo, HttpSession session) throws Exception {

        int result = service.write(vo);



        if(result == 1){
            session.setAttribute("alertMsg","등록성공!!");
            return "redirect:/qa/product/list";
        }else{
            throw new IllegalStateException("[ERROR-WRITE] 등록 중 에러..");
        }
    }



    //상품 삭제
    @DeleteMapping("delete")
    @ResponseBody
    public String delete(String ProductNoArr) throws JsonProcessingException {
        System.out.println("ProductNoArr = " + ProductNoArr);
        List<String> productNoList = objectMapper.readValue(ProductNoArr, List.class);
        int result = service.delete(productNoList);

        if(result == 0){
            return "bad";
        }
        return "good";

    }

    //상품 수정(화면)
    @GetMapping("edit")
    public void edit(String productNo, Model model){
        ProductVo productVo  = service.getProductByNo(productNo);
        model.addAttribute("productVo",productVo);
    }

    //상품 수정(처리)
    @PostMapping("edit")
    public String edit(ProductVo vo, Model model) throws Exception {

        System.out.println("@@@@@@@");
        System.out.println("vo = " + vo);

        int result = service.edit(vo);



        if(result != 1){
            throw new IllegalStateException("수정하기 중 에러...");
        }

        ProductVo productVo = service.getProductDetail(vo.getNo());
        model.addAttribute("productVo", productVo);
        return "redirect:/qa/product/list";

    }
}
