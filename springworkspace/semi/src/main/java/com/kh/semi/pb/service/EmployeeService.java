package com.kh.semi.pb.service;

import com.kh.semi.pb.mapper.EmployeeMapper;
import com.kh.semi.pb.vo.EmployeeVo;
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

    // 검색 기능 추가
    public List<EmployeeVo> searchEmployees(String department, String position, String name) {
        return mapper.searchEmployees(department, position, name);
    }
}
