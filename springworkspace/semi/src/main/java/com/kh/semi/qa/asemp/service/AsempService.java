package com.kh.semi.qa.asemp.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.qa.asemp.mapper.AsempMapper;
import com.kh.semi.qa.asemp.vo.AsempVo;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.qa.asemp.vo.DeptVo;

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

        // 해당 사원 기등록 여부 확인
        int check = mapper.checkAsemp(vo);

        int result;
        // 해당 사원이 기등록 되었으면 DEL_YN 을 N로 업데이트
        if (check == 1) {
            result = mapper.update(vo);
        }
        else {
            // 해당 사원이 등록된 적이 없다면 INSERT
            result = mapper.enroll(vo);
        }

        return result;
    }

    public AsempVo getAsempDetail(String no) {
        return mapper.getAsempDetail(no);
    }

    public int edit(AsempVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int getAsempListCnt(String area, String searchType, String searchValue) {
        return mapper.getAsempListCnt(area, searchType, searchValue);
    }

    public List<DeptVo> getDeptList(Model model) {
        return mapper.getDeptList(model);
    }

    public List<EmployeeVo> getEmpList(PageVo pvo, String dept, String empSearchType, String empSearchValue) {
        return mapper.getEmpList(pvo, dept, empSearchType, empSearchValue);
    }

    public int getEmpCnt(String dept, String empSearchType, String empSearchValue) {
        return mapper.getEmpCnt(dept, empSearchType, empSearchValue);
    }

    public int checkAsemp(AsempVo vo) {
        return mapper.checkAsemp(vo);
    }

    public int update(AsempVo vo) {
        return mapper.update(vo);
    }
}
