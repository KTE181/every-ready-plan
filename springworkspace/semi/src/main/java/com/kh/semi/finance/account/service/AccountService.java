package com.kh.semi.finance.account.service;

import com.kh.semi.finance.account.mapper.AccountMapper;
import com.kh.semi.finance.account.vo.AccountVo;
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
public class AccountService {

    private final AccountMapper mapper;

    public int write(AccountVo vo) {
        return mapper.write(vo);
    }

    public List<AccountVo> getAccountList() {
        return mapper.selectAccountVoList();
    }

    public int del(String no) {
        return mapper.del(no);
    }

    public AccountVo getAccountDetail(String no, Model model) {

        AccountVo vo = mapper.getAccountDetail(no ,model);

        return vo;
    }


    public int edit(AccountVo vo) {
        return mapper.edit(vo);
    }

    public int getAccountCnt() {
        return mapper.getAccountCnt();
    }
}
