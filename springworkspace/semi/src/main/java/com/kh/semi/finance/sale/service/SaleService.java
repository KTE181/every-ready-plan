package com.kh.semi.finance.sale.service;

import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.finance.sale.mapper.SaleMapper;
import com.kh.semi.finance.sale.vo.SaleVo;
import com.kh.semi.pb.vo.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SaleService {

    private final SaleMapper mapper;

    public List<SaleVo> getSaleList() {
        return mapper.selectSaleVoList();
    }

    private List<SaleVo> selectSaleVoList() {
        return mapper.selectSaleVoList();
    }

    public int write(SaleVo vo) {
        return mapper.write(vo);
    }


    public SaleVo getSaleDetail(String no, Model model) {
        SaleVo vo = mapper.getSaleDetail(no, model);

        return vo;
    }

    public int edit(SaleVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int getTotalSaleCount() {
        return mapper.getTotalSaleCount();
    }

    public int getSaleListCnt(String area, String searchValue) {
        return mapper.getSaleListCnt(area, searchValue);
    }

    public List<SaleVo> getAllSales(PageVo pageVo) {
        return mapper.getAllSales(pageVo);
    }

    public List<SaleVo> getSaleList(PageVo pageVo, String area, String searchValue) {
        return mapper.getSaleList(pageVo, area, searchValue);
    }

    public int deleteMultipleSales(List<String> saleIds) {
        return mapper.deleteMultipleSales(saleIds);
    }

}
