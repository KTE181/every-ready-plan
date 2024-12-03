package com.kh.semi.login.service;

import com.kh.semi.login.mapper.AdminLoginMapper;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminLoginService {
    private final AdminLoginMapper mapper;

    public AdminLoginVo adminlogin(AdminLoginVo vo) {
        return mapper.adminlogin(vo);
    }
}
