package com.kh.semi.finance.purchase.service;

import com.kh.semi.finance.account.controller.AccountController;
import com.kh.semi.finance.purchase.mapper.PurchaseMapper;
import com.kh.semi.finance.purchase.vo.PurchaseVo;
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
public class PurchaseService {


    private final PurchaseMapper mapper;

    public int write(PurchaseVo vo) {
        return mapper.write(vo);
    }

    public List<PurchaseVo> getPurchaseList() {
        return mapper.selectPurchaseVoList();
    }

    private List<PurchaseVo> selectPurchaseVoList() {
        return mapper.selectPurchaseVoList();
    }

    public int edit(PurchaseVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public PurchaseVo getPurchaseDetail(String no, Model model) {
        PurchaseVo vo = mapper.getPurchaseDetail(no ,model);

        return vo;
    }

}
