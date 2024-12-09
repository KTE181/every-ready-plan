package com.kh.semi.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.itemcode.vo.ItemVo;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.product.service.ProductService;
import com.kh.semi.product.vo.ProductVo;
import com.kh.semi.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
@RequestMapping("qa/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ObjectMapper objectMapper;

    //상품 목록 조회
    @GetMapping("list")
    public String list(Model model,
                       @RequestParam(value = "searchValue", required = false) String searchValue,
                       @RequestParam(value = "searchValueName", required = false) String searchValueName
            ,@RequestParam(name = "pno", defaultValue = "1") int currentPage
            ,HttpSession session) {


        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }

        int listCount = service.getBoardCnt();
        int pageLimit = 5;
        int boardLimit = 14;

        PageVo pageVo = new PageVo(listCount , currentPage, pageLimit, boardLimit);
        List<ProductVo> productVo = service.getProductList(searchValue, searchValueName, pageVo);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("productVo", productVo);

        return "qa/product/list";
    }


    //상품 상세 조회
    @GetMapping("detail")
    @ResponseBody
    public ProductVo getProductDetail(@RequestParam("no") String productNo) {
        ProductVo productVo = service.getProductDetail(productNo);
        if (productVo == null) {
            throw new IllegalStateException();
        }
        return productVo;
    }

    //품목코드 가져오기
    @GetMapping("iclist")
    @ResponseBody
    public List<ItemVo> iclist(){
        return service.getitemCodeVoList();
    }

    //상품명 불러오기
    @GetMapping("/getItemCodeName")
    @ResponseBody
    public ItemVo getItemCodeName(@RequestParam("code") String itemCode) {
        return service.getItemCodeName(itemCode);
    }



    //상품 등록(처리)
    @PostMapping("write")
    public String write(ProductVo vo, HttpSession session) throws Exception {

        int result = service.write(vo);

        if(result == 1){
            return "redirect:/qa/product/list";
        }else{
            throw new IllegalStateException("[ERROR-WRITE] 등록 중 에러..");
        }
    }

    //품목 코드 등록(처리)
    @PostMapping("item")
    public String item(ItemVo vo, HttpSession session) throws Exception {
        System.out.println(vo);
        int result = service.itemCode(vo);
        if(result == 1){
            return "redirect:/qa/product/list";
        }else{
            throw new Exception("redirect:/error");
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
