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

    public String insert(VacationVo vo) {

        String errorMsg="";
        if(vo.getEmpNo()==null|vo.getEmpNo().equals("")){
            errorMsg="[ERROR_VACATION_001]사번을 선택해주세요";
            return errorMsg;
        }if(vo.getThisDate()==null|vo.getThisDate().equals("")){
            errorMsg="[ERROR_VACATION_002]일자를 입력해주세요";
            return errorMsg;
        }if(vo.getCode()==null|vo.getCode().equals("")){
            errorMsg="[ERROR_VACATION_003]휴가유형을 선택해주세요";
            return errorMsg;
        }if(vo.getReason()==null|vo.getReason().equals("")){
            errorMsg="[ERROR_VACATION_004]휴가사유를 입력해주세요";
            return errorMsg;
        }if(vo.getReason().length()<=3) {
            errorMsg = "[ERROR_VACATION_005]휴가사유는 4글자 이상 작성해주세요";
            return errorMsg;
        }
        int result = mapper.insert(vo);
            if(result ==1){
                return "1";
            }
        return "0";
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

    public String update(VacationVo alldata) {
        String errorMsg="";
        if(alldata.getThisDate()==null|alldata.getThisDate().equals("")){
            errorMsg="[ERROR_VACATION_EDIT_006]일자를 입력해주세요";
            return errorMsg;
        }if(alldata.getCode()==null|alldata.getCode().equals("")){
            errorMsg="[ERROR_VACATION_EDIT_007]휴가유형을 선택해주세요";
            return errorMsg;
        }if(alldata.getReason()==null|alldata.getReason().equals("")){
            errorMsg="[ERROR_VACATION_EDIT_008]휴가사유를 입력해주세요";
            return errorMsg;
        }if(alldata.getReason().length()<=3) {
            errorMsg = "[ERROR_VACATION_EDIT_009]휴가사유는 4글자 이상 작성해주세요";
            return errorMsg;
        }
        int result= mapper.update(alldata);

        if(result==1){
            return "1";
        }

        return "0";
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
