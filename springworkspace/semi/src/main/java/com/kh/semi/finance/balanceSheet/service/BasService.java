package com.kh.semi.finance.balanceSheet.service;

import com.kh.semi.finance.balanceSheet.mapper.BasMapper;
import com.kh.semi.finance.balanceSheet.vo.BasVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasService {

    private final BasMapper mapper;

    public BasVo getBasByNo(String no) {
        return mapper.getBasByNo(no);
    }
}
