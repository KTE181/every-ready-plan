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

    public int write(DefectiveCodeVo vo) {
        return mapper.write(vo);
    }


    public List<DefectiveCodeVo> getdcVoList(String bno, Model model) {
        return mapper.getdcVoList(bno, model);
    }

    public int edit(DefectiveCodeVo vo) {
        return mapper.edit(vo);
    }

    public int delete(List<String> defectiveCodeNoList) {
        String x = String.join("," , defectiveCodeNoList);
        return mapper.delete(x);
    }

    public DefectiveCodeVo getCodeByNo(String defectiveCodeNo) {
        return mapper.findByNo(defectiveCodeNo);
    }


    public List<DefectiveCodeVo> getDefectiveCodeList(String searchValue, String searchValueName) {

        StringBuilder str = new StringBuilder();

        // 동적 조건 추가
        if (searchValue != null && !searchValue.isEmpty()) {
            str.append("AND NO LIKE '%").append(searchValue).append("%' ");
        }
        if (searchValueName != null && !searchValueName.isEmpty()) {
            str.append("AND NAME LIKE '%").append(searchValueName).append("%' ");
        }

        return mapper.getDefectiveCodeList(str.toString());

    }
}
