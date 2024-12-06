package com.kh.semi.pv.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.mapper.PvSalaryMapper;
import com.kh.semi.pv.vo.PvSalaryVo;
import com.kh.semi.pv.vo.PvVacationVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PvSalaryService {

    private final PvSalaryMapper mapper;

    public List<PvSalaryVo> getSalaryList(String no, String date, PageVo pvo) {
        return mapper.getSalaryList(no,date,pvo);
    }

    public int getSalaryCnt(String no) {
        return mapper.getSalaryCnt(no);
    }
}
