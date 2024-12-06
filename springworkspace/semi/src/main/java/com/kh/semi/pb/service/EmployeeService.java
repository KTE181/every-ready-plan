package com.kh.semi.pb.service;

import com.kh.semi.pb.mapper.EmployeeMapper;
import com.kh.semi.pb.vo.EmployeeVo;
import com.kh.semi.pb.vo.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeService {

    private final EmployeeMapper mapper;

    // 검색 조건 포함 직원 수 조회
    public int getEmployeeCountWithSearch(String department, String position, String name) {
        return mapper.countEmployeesWithSearch(department, position, name);
    }

    // 검색 조건 + 페이징 처리된 직원 목록 조회
    public List<EmployeeVo> searchEmployeesWithPaging(String department, String position, String name, PageVo pageVo) {
        return mapper.searchEmployeesWithPaging(
                department,
                position,
                name,
                pageVo.getOffset(),
                pageVo.getBoardLimit()
        );
    }


}

