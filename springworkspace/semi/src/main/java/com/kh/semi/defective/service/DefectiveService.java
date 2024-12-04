package com.kh.semi.defective.service;

import com.kh.semi.defective.mapper.DefectiveMapper;
import com.kh.semi.defective.vo.DefectiveCodeVo;
import com.kh.semi.defective.vo.DefectiveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectiveService {

    private final DefectiveMapper mapper;

    public int write(DefectiveVo vo) {
        return mapper.write(vo);
    }


    public int edit(DefectiveVo vo) {
        return mapper.edit(vo);
    }

    public int delete(List<String> defectiveNoList) {
        String x = String.join("," , defectiveNoList);
        return mapper.delete(x);
    }

    public DefectiveVo getDefectiveByNo(String defectiveNo) {
        return mapper.getDefectiveByNo(defectiveNo);
    }

    public List<DefectiveVo> getDefective(String searchValue, String searchValueError) {
        StringBuilder str = new StringBuilder();

        // 동적 조건 추가
        if (searchValue != null && !searchValue.isEmpty()) {
            str.append("AND NAME LIKE '%").append(searchValue).append("%' ");
        }
        if (searchValueError != null && !searchValueError.isEmpty()) {
            str.append("AND DEFECTIVE_CODE LIKE '%").append(searchValueError).append("%' ");
        }

        return mapper.getProductList(str.toString());


    }

    public DefectiveVo getDefectiveDetail(String defectiveNo) {
        return mapper.getDefectiveDetail(defectiveNo);
    }

    public List<DefectiveCodeVo> getdefectiveCodeVoList() {
        return mapper.getdefectiveCodeVoList();
    }

    public DefectiveCodeVo getDefectiveName(String defectiveCode) {
        return mapper.getDefectiveName(defectiveCode);
    }
}
