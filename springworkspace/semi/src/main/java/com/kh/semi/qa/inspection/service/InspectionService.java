package com.kh.semi.qa.inspection.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asreq.vo.AsreqVo;
import com.kh.semi.qa.inspection.mapper.InspectionMapper;
import com.kh.semi.qa.inspection.vo.InspectionTypeVo;
import com.kh.semi.qa.inspection.vo.InspectionVo;
import com.kh.semi.qa.inspection.vo.InspectionStatusVo;
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

    public List<InspectionVo> getInspectionList(Model model, PageVo pvo, String inspectionType, String status, String passYn, String searchType, String searchValue) {

        List<InspectionVo> inspectionVoList = mapper.getInspectionList(model, pvo, inspectionType, status, passYn, searchType, searchValue);

        for (InspectionVo vo : inspectionVoList) {
            if(vo.getInspectionDate() != null) {
                vo.setInspectionDate(vo.getInspectionDate().substring(0,10));
            }
        }
        return inspectionVoList;
    }

    public int write(InspectionVo vo) {

        if(vo.getInspectionDate() != null) {
            vo.setInspectionDate(vo.getInspectionDate().replace("-", ""));
        }

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

    public List<InspectionStatusVo> getStatusList(Model model) {
        return mapper.getStatusList(model);
    }

    public List<InspectionTypeVo> getInspectionTypeList(Model model) {
        return mapper.getInspectionTypeList(model);
    }

    public int getInspectionListCnt(String inspectionType, String status, String passYn, String searchType, String searchValue) {
        return mapper.getInspectionListCnt(inspectionType, status, passYn, searchType, searchValue);
    }
}
