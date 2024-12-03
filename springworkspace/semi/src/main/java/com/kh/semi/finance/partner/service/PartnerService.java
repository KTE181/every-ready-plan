package com.kh.semi.finance.partner.service;


import com.kh.semi.finance.account.vo.AccountVo;
import com.kh.semi.finance.partner.mapper.PartnerMapper;
import com.kh.semi.finance.partner.vo.PartnerVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PartnerService {

    private final PartnerMapper mapper;

    public int write(PartnerVo vo) {

        return mapper.write(vo);

    }

    public List<PartnerVo> getPartnerList(Model model) {
        return mapper.getPartnerList();
    }

    public PartnerVo getPartnerDetail(String partnerNo, Model model) {

        PartnerVo vo = mapper.getPartnerDetail(partnerNo ,model);

        return vo;
    }

    public int edit(PartnerVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }
}
