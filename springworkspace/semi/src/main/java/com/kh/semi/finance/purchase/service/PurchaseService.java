package com.kh.semi.finance.purchase.service;

import com.kh.semi.finance.purchase.mapper.PurchaseMapper;
import com.kh.semi.finance.purchase.vo.PurchaseVo;
import com.kh.semi.pb.vo.PageVo;
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


    public int deleteMultiplePurchases(List<String> purchaseIds) {
        return mapper.deleteMultiplePurchases(purchaseIds);
    }

    public int getPurchaseListCnt(String area, String searchValue) {
        return mapper.getPurchaseListCnt(area, searchValue);
    }

    public int getTotalPurchaseCount() {
        return mapper.getTotalPurchaseCount();
    }


    public List<PurchaseVo> getAllPurchases(PageVo pageVo) {
        return mapper.getAllPurchases(pageVo);
    }


    public List<PurchaseVo> getPurchaseList(PageVo pageVo, String area, String searchValue) {
        return mapper.selectPurchaseVoList(pageVo, area, searchValue);
    }

}
