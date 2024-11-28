package com.kh.semi.hr.vacation.service;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.vacation.mapper.VacationMapper;
import com.kh.semi.hr.vacation.vo.VacationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationMapper mapper;

    public List<VacationVo> list() {
        return mapper.list();
    }

    public int insert(VacationVo vo) {
        return mapper.insert(vo);
    }

    public List<EmployeeVo> empVoList( ) {
        return mapper.empVoList();
    }

    public EmployeeVo selectvolist(String empNo) {
        return mapper.selectvolist(empNo);
    }

    public VacationVo getVacationVo(String selectNo) {
        return  mapper.getVacationVo(selectNo);
    }
}
