package com.kh.semi.finance.account.service;

import com.kh.semi.finance.account.mapper.AccountMapper;
import com.kh.semi.finance.account.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper mapper;

    public int write(AccountVo vo) {
        return mapper.write(vo);
    }

    public List<AccountVo> getAccountList() {
        return mapper.selectAccountVoList();
    }

    public int del(String bno) {
        return mapper.del(bno);
    }

    public AccountVo getAccountDetail(String accountNo, Model model) {
        return mapper.getAcccountDetail();
    }


}
