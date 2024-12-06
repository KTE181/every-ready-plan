package com.kh.semi.hr;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HrService {

    private final HrMapper mapper;

    public List<EmployeeVo> getList() {
        return mapper.getList();
    }
}
