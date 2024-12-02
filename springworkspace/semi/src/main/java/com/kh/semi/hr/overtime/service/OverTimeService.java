package com.kh.semi.hr.overtime.service;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.overtime.mapper.OverTimeMapper;
import com.kh.semi.hr.overtime.vo.OverTimeVo;
import com.kh.semi.pb.vo.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OverTimeService {
    private final OverTimeMapper mapper;



    public List<EmployeeVo> empVoList(PageVo pvo) {
        return mapper.empVoList(pvo);
    }

    public int insert(OverTimeVo vo) {
        return mapper.insert(vo);
    }

    public EmployeeVo selectvolist(String empNo) {
        return mapper.selectvolist(empNo);
    }

    public List<OverTimeVo> list(PageVo pvo) {
        return mapper.selectAll(pvo);
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

    public int getOverTimeCnt() {
        return mapper.getOverTimeCnt();
    }

    public int getEmpCnt() {
        return mapper.getEmpCnt();
    }

    public List<EmployeeVo> getEmplistdata(PageVo pvo) {
        return mapper.getEmplistdata(pvo);
    }
}