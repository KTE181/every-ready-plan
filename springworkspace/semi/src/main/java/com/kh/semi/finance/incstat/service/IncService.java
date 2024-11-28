package com.kh.semi.finance.incstat.service;

import com.kh.semi.finance.incstat.mapper.IncMapper;
import com.kh.semi.finance.incstat.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncService {

    private final IncMapper mapper;

//    public List<IncVo> getIncVoList(){
//        List<IncVo> list = mapper.getIncVoList();
//        System.out.println("Fetched List: " + list);
//        return mapper.getIncVoList();
//    }

    public IncVo getIncByNo(String no) {
        return mapper.getIncByNo(no);
    }
}
