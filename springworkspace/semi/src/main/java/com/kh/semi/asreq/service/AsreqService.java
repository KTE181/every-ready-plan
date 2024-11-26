package com.kh.semi.asreq.service;

import com.kh.semi.asreq.asreq.vo.AsreqVo;
import com.kh.semi.asreq.mapper.AsreqMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsreqService {

    private final AsreqMapper mapper;

    public int write(AsreqVo vo) {
        return mapper.write(vo);
    }

    public List<AsreqVo> getAsreqList(Model model) {
        return mapper.getAsreqList(model);
    }

    public AsreqVo getAsreqDetail(String asreqNo, Model model) {
        return mapper.getAsreqDetail(asreqNo, model);
    }

    public int edit(AsreqVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String asreqNo) {
        return mapper.delete(asreqNo);
    }
}
