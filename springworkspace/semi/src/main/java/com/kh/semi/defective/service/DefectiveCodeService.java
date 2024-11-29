package com.kh.semi.defective.service;

import com.kh.semi.defective.mapper.DefectiveCodeMapper;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectiveCodeService {

    private final DefectiveCodeMapper mapper;

    public List<DefectiveCodeVo> getDefectiveCode() {

        return mapper.getDefectiveCode();
    }


    public int write(DefectiveCodeVo vo) {
        return mapper.write(vo);
    }

    public int delete(String bno) {
        return mapper.delete(bno);
    }

    public List<DefectiveCodeVo> getdcVoList(String bno, Model model) {
        return mapper.getdcVoList(bno, model);
    }

    public int edit(DefectiveCodeVo vo) {
        return mapper.edit(vo);
    }
}
