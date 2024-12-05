package com.kh.semi.defective.service;

import com.kh.semi.defective.mapper.DefectiveCodeMapper;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.util.page.PageVo;
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


    public List<DefectiveCodeVo> getDefectiveCodeList(String searchValue, String searchValueName, PageVo pageVo) {

        StringBuilder str = new StringBuilder();

        // 동적 조건 추가
        if (searchValue != null && !searchValue.isEmpty()) {
            str.append("AND NO LIKE '%").append(searchValue).append("%' ");
        }
        if (searchValueName != null && !searchValueName.isEmpty()) {
            str.append("AND NAME LIKE '%").append(searchValueName).append("%' ");
        }

        return mapper.getDefectiveCodeList(str.toString(), pageVo);

    }

    public DefectiveCodeVo getDefectiveCodeDetail(String defectiveCodeNo) {
        return mapper.getDefectiveCodeDetail(defectiveCodeNo);
    }

    public int getDefectiveCodeCnt() {
        return mapper.getDefectiveCodeCnt();
    }
}
