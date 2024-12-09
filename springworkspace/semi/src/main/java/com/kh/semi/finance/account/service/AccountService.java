package com.kh.semi.finance.account.service;

import com.kh.semi.finance.account.mapper.AccountMapper;
import com.kh.semi.finance.account.vo.AccountVo;
import com.kh.semi.pb.vo.PageVo;
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

//    public List<AccountVo> getAccountList() {
//        return mapper.selectAccountVoList();
//    }

//아래 2개는 페이징완료
//    public List<AccountVo> getAccountList(PageVo pageVo) {
//        return mapper.selectAccountVoList(pageVo);
//    }
//
//    public int getAccountListCnt() {
//        return mapper.getAccountListCnt();
//    }

    public List<AccountVo> getAccountList(PageVo pageVo, String area, String searchValue) {
        return mapper.selectAccountVoList(pageVo, area, searchValue);
    }

    public int getAccountListCnt(String area, String searchValue) {
        return mapper.getAccountListCnt(area, searchValue);
    }

    public int getTotalAccountCount() {
        return mapper.getTotalAccountCount();
    }

    public List<AccountVo> getAllAccounts(PageVo pageVo) {
        return mapper.getAllAccounts(pageVo);
    }

    public int del(String no) {
        return mapper.del(no);
    }

    public AccountVo getAccountDetail(String no, Model model) {

        AccountVo vo = mapper.getAccountDetail(no, model);

        return vo;
    }

    public int edit(AccountVo vo) {
        return mapper.edit(vo);
    }

//    public int deleteMultipleAccounts(List<String> accountIds) {
//        return mapper.deleteMultipleAccounts(accountIds);
//    }

    @Transactional
    public int deleteMultipleAccounts(List<String> accountIds) {
        // 디버깅 로그
        System.out.println("삭제할 Account IDs: " + accountIds);

        // 매퍼 호출
        return mapper.deleteMultipleAccounts(accountIds);
    }
}