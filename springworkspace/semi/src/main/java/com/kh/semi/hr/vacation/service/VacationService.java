package com.kh.semi.hr.vacation.service;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.employee.vo.SearchVo;
import com.kh.semi.hr.vacation.mapper.VacationMapper;
import com.kh.semi.hr.vacation.vo.VacationVo;
import com.kh.semi.pb.vo.PageVo;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.regexp.RE;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationMapper mapper;

    public List<VacationVo> list(PageVo pvo, SearchVo searchVo) {
        return mapper.list(pvo,searchVo);
    }

    public int insert(VacationVo vo) {
        return mapper.insert(vo);
    }

    public List<EmployeeVo> empVoList(PageVo pvo2) {
        return mapper.empVoList(pvo2);
    }

    public EmployeeVo selectvolist(String empNo) {
        return mapper.selectvolist(empNo);
    }

    public VacationVo getVacationVo(String selectNo) {
        return  mapper.getVacationVo(selectNo);
    }

    public int update(VacationVo alldata) {
        return mapper.update(alldata);
    }

    public int delete(String no) {
        return mapper.delete(no);
    }

    public int editAll(String[] dataArr) {
        return mapper.editAll(dataArr);
    }

    public int getVacationCnt() {
        return mapper.getVacationCnt();
    }

    public int getEmpCnt() {
        return mapper.getEmpCnt();
    }

    public List<EmployeeVo> getEmplistdata(PageVo pvo) {
        return mapper.getEmplistdata(pvo);
    }

    public EmployeeVo selectEmpVo(String searchEmpNo, String searchEname) {
        return mapper.selectEmpVo(searchEmpNo,searchEname);
    }
}
