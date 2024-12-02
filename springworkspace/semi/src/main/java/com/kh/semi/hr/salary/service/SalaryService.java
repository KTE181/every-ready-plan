package com.kh.semi.hr.salary.service;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.salary.mapper.SalaryMapper;
import com.kh.semi.hr.salary.vo.SalaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final SalaryMapper mapper;


    public List<SalaryVo> listAll() {
        return mapper.listAll();
    }

    public EmployeeVo selectvolist(String empNo) {
        return mapper.selectvolist(empNo);
    }

    public List<EmployeeVo> empVoList() {
        return mapper.empVoList();
    }

    public int write(SalaryVo vo) {
        return mapper.write(vo);
    }

    public SalaryVo detail(String selectNo) {
        SalaryVo vo =mapper.detail(selectNo);

        String original = vo.getPayYearmonth();
        String year = original.substring(0, 4); // 첫 4자리 (연도)
        String month = original.substring(4, 6); // 마지막 2자리 (월)
        String finishData = year + "-" + month;
        vo.setPayYearmonth(finishData);
        return vo;
    }

    public int edit(HashMap<String, String> editdata) {
        return mapper.edit(editdata);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int editAll(String[] dataArr) {
        return mapper.editAll(dataArr);
    }
}
