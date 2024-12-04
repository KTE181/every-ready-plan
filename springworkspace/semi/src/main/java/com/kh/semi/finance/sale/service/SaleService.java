package com.kh.semi.finance.sale.service;

import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.finance.sale.mapper.SaleMapper;
import com.kh.semi.finance.sale.vo.SaleVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
