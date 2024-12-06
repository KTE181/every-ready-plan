package com.kh.semi.hr.employeehr.service;

import com.kh.semi.hr.employeehr.mapper.EmployeeHrMapper;
import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeHrService {

    private final EmployeeHrMapper mapper;

    // 직원 수 조회 (검색 조건 포함)
    public int getEmployeeCount(String no, String name, String dname, String pname, String esname) {
        return mapper.selectEmployeeCount(no, name, dname, pname, esname);
    }

    // 직원 리스트 조회 (검색 조건 + 페이징)
    public List<EmployeeVo> searchEmployee(String no, String name, String dname, String pname, String esname, PageVo pageVo) {
        return mapper.selectEmployeeListWithSearch(no, name, dname, pname, esname, pageVo.getOffset(), pageVo.getBoardLimit());
    }

    // 삭제
    public void deleteEmployees(String ids) {
        mapper.updateDelYnToYes(ids);
    }



}
