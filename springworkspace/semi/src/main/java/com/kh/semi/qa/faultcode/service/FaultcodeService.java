package com.kh.semi.qa.faultcode.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.faultcode.mapper.FaultcodeMapper;
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
public class FaultcodeService {

    private final FaultcodeMapper mapper;

    public List<FaultcodeVo> getFaultCodeList(Model model, PageVo pvo, String searchType, String searchValue) {
        return mapper.getFaultCodeList(model, pvo, searchType, searchValue);
    }

    public int getFaultCodeListCnt(String searchType, String searchValue) {
        return mapper.getFaultCodeListCnt(searchType, searchValue);
    }

    public FaultcodeVo getFaultCodeDetail(String no) {
        return mapper.getFaultCodeDetail(no);
    }

    public int enroll(FaultcodeVo vo) {
        return mapper.enroll(vo);
    }

    public int edit(String no, String faultName) {
        return mapper.edit(no, faultName);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

}
