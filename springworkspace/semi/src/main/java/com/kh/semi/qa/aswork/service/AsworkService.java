package com.kh.semi.aswork.service;

import com.kh.semi.asreq.vo.AsreqVo;
import com.kh.semi.aswork.mapper.AsworkMapper;
import com.kh.semi.aswork.vo.AsworkVo;
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

    public List<AsworkVo> getAsworkList(Model model) {

        List<AsworkVo> asworkList = mapper.getAsworkList(model);

        for (AsworkVo vo : asworkList) {
            if(vo.getRepairDate() != null) {
                vo.setRepairDate(vo.getRepairDate().substring(0,10));
            }
        }
        return asworkList;

    }

    public AsworkVo getAsworkDetail(String asworkNo, Model model) {

        AsworkVo vo = mapper.getAsworkDetail(asworkNo, model);

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

        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }
}
