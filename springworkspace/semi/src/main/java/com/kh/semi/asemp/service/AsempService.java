package com.kh.semi.asemp.service;

import com.kh.semi.asemp.mapper.AsempMapper;
import com.kh.semi.asemp.vo.AsempVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AsempService {

    private final AsempMapper mapper;

    public List<AsempVo> getAsempList(Model model) {
        return mapper.getAsempList(model);
    }

    public int enroll(AsempVo vo) {
        return mapper.enroll(vo);
    }

    public AsempVo getAsempDetail(String no, Model model) {
        return mapper.getAsempDetail(no, model);
    }
}
