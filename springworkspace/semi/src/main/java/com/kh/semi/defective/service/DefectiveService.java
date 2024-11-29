package com.kh.semi.defective.service;

import com.kh.semi.defective.mapper.DefectiveMapper;
import com.kh.semi.defective.vo.DefectiveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectiveService {

    private final DefectiveMapper mapper;

    public List<DefectiveVo> getDefective() {
        return mapper.getDefective();
    }

    public int write(DefectiveVo vo) {
        return mapper.write(vo);
    }


    public List<DefectiveVo> getDefectiveDetail(String bno, Model model) {
        return mapper.getDefectiveDetail(bno, model);
    }

    public int edit(DefectiveVo vo) {
        return mapper.edit(vo);
    }

    public int delete(List<String> defectiveNoList) {
        String x = String.join("," , defectiveNoList);
        System.out.println("x = " + x);
        return mapper.delete(x);
    }
}
