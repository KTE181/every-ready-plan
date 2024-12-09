package com.kh.semi.finance.partner.service;


import com.kh.semi.finance.account.vo.AccountVo;
import com.kh.semi.finance.partner.mapper.PartnerMapper;
import com.kh.semi.finance.partner.vo.PartnerVo;
import com.kh.semi.pb.vo.PageVo;
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

    //    public List<PartnerVo> getPartnerList(Model model) {
//        return mapper.getPartnerList();
//    }
//
    public PartnerVo getPartnerDetail(String partnerNo, Model model) {

        PartnerVo vo = mapper.getPartnerDetail(partnerNo, model);

        return vo;
    }

    public int getTotalPartnerCount() {
        return mapper.getTotalPartnerCount();
    }

    public int getPartnerListCnt(String area, String searchValue) {
        return mapper.getPartnerListCnt(area, searchValue);
    }

    public List<PartnerVo> getAllPartners(PageVo pageVo) {
        return mapper.getAllPartners(pageVo);
    }

    public List<PartnerVo> getPartnerList(PageVo pageVo, String area, String searchValue) {
        return mapper.getPartnerList(pageVo, area, searchValue);
    }

    public List<PartnerVo> getAllPartner(PageVo pageVo) {
        return mapper.getAllPartners(pageVo);
    }

    public int edit(PartnerVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }


    public int deleteMultiplePartners(List<String> partnerIds) {
        return mapper.deleteMultiplePartners(partnerIds);
    }
}
