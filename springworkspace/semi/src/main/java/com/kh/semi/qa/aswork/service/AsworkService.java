package com.kh.semi.qa.aswork.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asemp.vo.AsempVo;
import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.qa.aswork.mapper.AsworkMapper;
import com.kh.semi.qa.aswork.vo.AsworkStatusVo;
import com.kh.semi.qa.aswork.vo.AsworkVo;
import com.kh.semi.qa.faultcode.vo.FaultcodeVo;
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
public class AsworkService {

    private final AsworkMapper mapper;

    public List<AsworkVo> getAsworkList(Model model, PageVo pvo, String area, String status, String type, String searchType, String searchValue) {

        List<AsworkVo> asworkList = mapper.getAsworkList(model, pvo, area, status, type, searchType, searchValue);

        for (AsworkVo vo : asworkList) {
            if(vo.getRepairDate() != null) {
                vo.setRepairDate(vo.getRepairDate().substring(0,10));
            }
        }
        return asworkList;

    }

    public int getAsworkListCnt(String area, String status, String type, String searchType, String searchValue) {
        return mapper.getAsworkListCnt(area, status, type, searchType, searchValue);
    }

    public AsworkVo getAsworkDetail(String asworkNo) {

        AsworkVo vo = mapper.getAsworkDetail(asworkNo);

        if(vo.getPurchaseDate() != null) {
            vo.setPurchaseDate(vo.getPurchaseDate().substring(0,10));
        }

        if(vo.getPreferredServiceDate() != null) {
            vo.setPreferredServiceDate(vo.getPreferredServiceDate().substring(0,10));
        }

        if(vo.getRepairDate() != null) {
            vo.setRepairDate(vo.getRepairDate().substring(0,10));
        }

        return vo;
    }

    public int edit(AsworkVo vo) {

        if(vo.getPurchaseDate() != null) {
            vo.setPurchaseDate(vo.getPurchaseDate().replace("-", ""));
        }

        if(vo.getPreferredServiceDate() != null) {
            vo.setPreferredServiceDate(vo.getPreferredServiceDate().replace("-", ""));
        }

        if(vo.getRepairDate() != null) {
            vo.setRepairDate(vo.getRepairDate().replace("-", ""));
        }

        int result1 = mapper.edit(vo);
        int result2 = mapper.updateStatus(vo);
        return result1 * result2;
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public List<AsworkStatusVo> getStatusList(Model model) {
        return mapper.getStatusList(model);
    }

    public List<FaultcodeVo> getTypeList(Model model) {
        return mapper.getTypeList(model);
    }

    public int getAsempCnt(String area) {
        return mapper.getAsempCnt(area);
    }

    public List<AsempVo> getAsempList(PageVo pvo, String area) {
        return mapper.getAsempList(pvo, area);
    }
}
