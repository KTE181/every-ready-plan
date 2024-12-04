package com.kh.semi.hr.salary.service;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import com.kh.semi.hr.employee.vo.SearchVo;
import com.kh.semi.hr.salary.mapper.SalaryMapper;
import com.kh.semi.hr.salary.vo.SalaryVo;
import com.kh.semi.pb.vo.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final SalaryMapper mapper;


    public List<SalaryVo> listAll(PageVo pvo, SearchVo searchVo) {


        return mapper.listAll(pvo,searchVo);
    }

    public EmployeeVo selectvolist(String empNo) {
        return mapper.selectvolist(empNo);
    }

    public List<EmployeeVo> empVoList(PageVo pvo2) {
        return mapper.empVoList(pvo2);
    }

    public String write(SalaryVo vo) {
        String errorMsg="";
        if(vo.getEmpNo()==null||vo.getEmpNo().isEmpty()){
            errorMsg="[ERROR_SALARY_001]사번을 선택하지 않았습니다";
            return errorMsg;
        }
        if(vo.getPayYearmonth()==null||vo.getPayYearmonth().isEmpty()){
            errorMsg="[ERROR_SALARY_002]지급연월을 선택하지 않았습니다";
            return errorMsg;
        }

         int result = mapper.write(vo);
        if(result == 1){
            return "1";
        }else{
            return "0";
        }

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

    public int getSalaryCnt() {
        return mapper.getSalaryCnt();
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
