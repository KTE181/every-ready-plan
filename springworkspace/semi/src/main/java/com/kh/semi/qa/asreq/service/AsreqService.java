package com.kh.semi.qa.asreq.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.qa.asreq.mapper.AsreqMapper;
import com.kh.semi.product.vo.ProductVo;
import com.kh.semi.qa.inspection.vo.ItemProductVo;
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
public class AsreqService {

    private final AsreqMapper mapper;

    public int write(AsreqVo vo) {

        if(vo.getPurchaseDate() != null) {
            vo.setPurchaseDate(vo.getPurchaseDate().replace("-", ""));
        }

        if(vo.getPreferredServiceDate() != null) {
            vo.setPreferredServiceDate(vo.getPreferredServiceDate().replace("-", ""));
        }

        return mapper.write(vo);
    }

    public List<AsreqVo> getAsreqList(Model model, PageVo pvo, String area, String searchType, String searchValue) {

        List<AsreqVo> asreqList = mapper.getAsreqList(model, pvo, area, searchType, searchValue);

        for (AsreqVo vo : asreqList) {
            if(vo.getPreferredServiceDate() != null) {
                vo.setPreferredServiceDate(vo.getPreferredServiceDate().substring(0,10));
            }
        }
        return asreqList;
    }

    public int getAsreqListCnt(String area, String searchType, String searchValue) {
        return mapper.getAsreqListCnt(area, searchType, searchValue);
    }

    public AsreqVo getAsreqDetail(String asreqNo) {

        AsreqVo vo = mapper.getAsreqDetail(asreqNo);

        if(vo.getPurchaseDate() != null) {
            vo.setPurchaseDate(vo.getPurchaseDate().substring(0,10));
        }

        if(vo.getPreferredServiceDate() != null) {
            vo.setPreferredServiceDate(vo.getPreferredServiceDate().substring(0,10));
        }

        return vo;
    }

    public int edit(AsreqVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int receive(String no) {

        int result1 = mapper.receiveAsreq(no);
        int result2 = mapper.enrollAswork(no);

        return result1 * result2;
    }

    public List<ItemProductVo> getProductList(PageVo pvo, String productSearchType, String productSearchValue) {
        return mapper.getProductList(pvo, productSearchType, productSearchValue);
    }

    public int getProductCnt(String productSearchType, String productSearchValue) {
        return mapper.getProductCnt(productSearchType, productSearchValue);
    }
}
