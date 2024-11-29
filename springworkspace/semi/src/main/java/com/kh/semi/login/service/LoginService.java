package com.kh.semi.login.service;

import com.kh.semi.login.mapper.LoginMapper;
import com.kh.semi.login.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {
    private final LoginMapper mapper;


    public LoginVo login(LoginVo vo) {
        return mapper.login(vo);
    }


}
