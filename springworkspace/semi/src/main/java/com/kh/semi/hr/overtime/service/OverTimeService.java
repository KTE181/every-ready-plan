package com.kh.semi.hr.overtime.service;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.overtime.mapper.OverTimeMapper;
import com.kh.semi.hr.overtime.vo.OverTimeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OverTimeService {
    private final OverTimeMapper mapper;

    public List<OverTimeVo> selectAll() {
        return mapper.selectAll();
    }

    public List<EmployeeVo> empVoList() {
        return mapper.empVoList();
    }

    public int insert(OverTimeVo vo) {
        return mapper.insert(vo);
    }

    public EmployeeVo selectvolist(String empNo) {
        return mapper.selectvolist(empNo);
    }

    public List<OverTimeVo> list() {
        return mapper.selectAll();
    }

    public OverTimeVo detail(String no) {
        return mapper.detail(no);
    }

    public int edit(OverTimeVo alldata) {
        return mapper.edit(alldata);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int editAll(String[] dataArr) {
        return mapper.editAll(dataArr);
    }
}