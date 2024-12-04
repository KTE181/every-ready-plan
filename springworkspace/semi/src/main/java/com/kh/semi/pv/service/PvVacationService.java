package com.kh.semi.pv.service;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.mapper.PvVacationMapper;
import com.kh.semi.pv.vo.PvVacationVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class PvVacationService {
    private final PvVacationMapper mapper;

    public List<PvVacationVo> getVacationList(String no, String date, PageVo pvo) {
        return mapper.getVacationList(no,date,pvo);
    }

    public PvVacationVo getVacationVo(String no) {
        return mapper.getVacationVo(no);
    }

    public int getVacationCnt(String no) {
        return mapper.gerVacationCnt(no);
    }
}
