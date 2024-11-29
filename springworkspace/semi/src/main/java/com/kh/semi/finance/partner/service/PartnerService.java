package com.kh.semi.finance.partner.service;


import com.kh.semi.finance.account.vo.AccountVo;
import com.kh.semi.finance.partner.mapper.PartnerMapper;
import com.kh.semi.finance.partner.vo.PartnerVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerMapper mapper;

    public int write(PartnerVo vo) {

        return mapper.write(vo);

    }

    public List<PartnerVo> getPartnerList(Model model) {
        return mapper.getPartnerList();
    }

//    public List<PartnerVo> getPartnerVoList() {
//        return mapper.getPartnerVoList();
//    }
//
//
//    public List<PartnerVo> getPartnerList(Model model) {
//        return mapper.getPartnerList();
//    }
}
