package com.kh.semi.qa.inspection.service;

import com.kh.semi.qa.inspection.mapper.InspectionMapper;
import com.kh.semi.qa.inspection.vo.InspectionVo;
import com.kh.semi.qa.inspection.vo.StatusVo;
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
public class InspectionService {

    private final InspectionMapper mapper;

    public List<InspectionVo> getInspectionList(Model model) {
        return mapper.getInspectionList(model);
    }

    public int write(InspectionVo vo) {
        return mapper.write(vo);
    }

    public InspectionVo getinspectionDetail(String no, Model model) {
        return mapper.getinspectionDetail(no, model);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int edit(InspectionVo vo) {
        return mapper.edit(vo);
    }

    public StatusVo getStatusList(Model model) {
        return mapper.getStatusList(model);
    }
}
