package com.kh.semi.defective.service;

import com.kh.semi.defective.mapper.DefectiveMapper;
import com.kh.semi.defective.vo.DefectiveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectiveService {

    private final DefectiveMapper mapper;

    public List<DefectiveVo> getDefective() {
        return mapper.getDefective();
    }

    public List<DefectiveVo> getDefectiveDetail() {
        return mapper.getDefectiveDetail();
    }
}
