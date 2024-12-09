package com.kh.semi.hr.employeehr.service;

import com.kh.semi.hr.employeehr.mapper.EmployeeHrMapper;
import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeHrService {

    private final EmployeeHrMapper mapper;

    // 직원 수 조회 (검색 조건 포함)
    public int getEmployeeCount(String no, String name, String dname, String pname, String esname) {
        return mapper.selectEmployeeCount(no, name, dname, pname, esname);
    }

    // 직원 리스트 조회 (검색 조건 + 페이징)
    public List<EmployeeVo> searchEmployee(String no, String name, String dname, String pname, String esname, PageVo pageVo) {
        return mapper.selectEmployeeListWithSearch(no, name, dname, pname, esname, pageVo.getOffset(), pageVo.getBoardLimit());
    }

    // 삭제
    public void deleteEmployees(String ids) {
        mapper.updateDelYnToYes(ids);
    }

    // 사원 등록 및 사번 반환
    public String registerEmployee(EmployeeVo employeeVo) {
        mapper.insertEmployee(employeeVo);
        log.info("Inserted Employee No: {}", employeeVo.getNo());
        return employeeVo.getNo();
    }

    // 프로필 이미지 경로 업데이트
    public void updateEmployeeProfileImage(String employeeId, String profileImagePath) {
        mapper.updateProfileImage(employeeId, profileImagePath);
    }

    //상세조회
    public EmployeeVo getEmployeeDetail(String no) {
        return mapper.selectEmployeeDetail(no);
    }
    public void updateEmployee(EmployeeVo employeeVo) {
        mapper.updateEmployee(employeeVo);
    }

    public String getProfileImage(String employeeId) {
        String imagePath = "/img/employee/" + employeeId + ".jpg";
        File imageFile = new File("src/main/resources/static" + imagePath);
        if (imageFile.exists()) {
            return imagePath; // 사원 이미지 경로 반환
        }
        return "/img/employee/default.png"; // 기본 이미지 반환
    }





}
