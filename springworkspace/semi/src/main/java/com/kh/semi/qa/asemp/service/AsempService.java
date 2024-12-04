package com.kh.semi.qa.asemp.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asemp.mapper.AsempMapper;
import com.kh.semi.qa.asemp.vo.AsempVo;
import com.kh.semi.hr.employee.vo.EmployeeVo;
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
public class AsempService {

    private final AsempMapper mapper;

    public List<AsempVo> getAsempList(Model model, PageVo pvo, String area, String searchType, String searchValue) {
        return mapper.getAsempList(model, pvo, area, searchType, searchValue);
    }

    public int enroll(AsempVo vo) {
        return mapper.enroll(vo);
    }

    public AsempVo getAsempDetail(String no, Model model) {
        return mapper.getAsempDetail(no, model);
    }

    public int edit(AsempVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public List<EmployeeVo> getEmpList() {
        return mapper.getEmpList();
    }

    public int getAsempListCnt(String area, String searchType, String searchValue) {
        return mapper.getAsempListCnt(area, searchType, searchValue);
    }
}
