package com.kh.semi.hr.overtime.service;

import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.hr.employeehr.vo.SearchVo;
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

    public String insert(OverTimeVo vo) {
        String errorMsg = "";
        if(vo.getEmpNo()==null||vo.getEmpNo().equals("")){
            errorMsg="[ERROR_OVERTIME_001]사번을 선택하지 않았습니다";
            return errorMsg;
        }
        if(vo.getThisDate()==null||vo.getThisDate().equals("")){
            errorMsg="[ERROR_OVERTIME_002]초과근무 일자를 선택하지 않았습니다";
            return errorMsg;
        }
        if(vo.getType()==null||vo.getType().equals("")){
            errorMsg="[ERROR_OVERTIME_003]초과근무구분을 선택하지 않았습니다";
            return errorMsg;
        }
        if(vo.getWorkHour()==null||vo.getWorkHour().equals("00:00")){
            errorMsg="[ERROR_OVERTIME_004]초과근무시간을 선택하지 않았습니다";
            return errorMsg;
        }
        int result = mapper.insert(vo);


        if(result == 1){
            return "1";
        }else{
            return "0";
        }

    }

    public EmployeeVo selectvolist(String empNo) {
        return mapper.selectvolist(empNo);
    }

    public List<OverTimeVo> list(PageVo pvo, SearchVo searchVo) {
        return mapper.selectAll(pvo,searchVo);
    }

    public OverTimeVo detail(String no) {
        return mapper.detail(no);
    }

    public String edit(OverTimeVo alldata) {
        String errorMsg = "";
        if(alldata.getThisDate()==null||alldata.getThisDate().equals("")){
            errorMsg="[ERROR_OVERTIME_002]초과근무 일자를 선택하지 않았습니다";
            return errorMsg;
        }
        if(alldata.getType()==null){
            errorMsg="[ERROR_OVERTIME_003]초과근무구분을 선택하지 않았습니다";
            return errorMsg;
        }
        if(alldata.getWorkHour()==null||alldata.getWorkHour().equals("00:00")){
            errorMsg="[ERROR_OVERTIME_004]초과근무시간을 선택하지 않았습니다";
            return errorMsg;
        }
        int result = mapper.edit(alldata);


        if(result == 1){
            return "1";
        }else{
            return "0";
        }

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

    public EmployeeVo selectEmpVo(String searchEmpNo, String searchEname) {
        return mapper.selectEmpVo(searchEmpNo,searchEname);
    }
}