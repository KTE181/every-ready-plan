package com.kh.semi.defective.service;

import com.kh.semi.defective.mapper.DefectiveCodeMapper;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectiveCodeService {

    private final DefectiveCodeMapper mapper;

    public List<DefectiveCodeVo> getDefectiveCode() {

        return mapper.getDefectiveCode();
    }

    public List<DefectiveCodeVo> getdcVoList() {
        return mapper.getdcVoList();
    }
}
