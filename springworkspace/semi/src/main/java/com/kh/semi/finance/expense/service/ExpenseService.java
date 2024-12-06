package com.kh.semi.finance.expense.service;

import com.kh.semi.finance.expense.mapper.ExpenseMapper;
import com.kh.semi.finance.expense.vo.ExpenseVo;
import com.kh.semi.pb.vo.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

    private final ExpenseMapper mapper;

    public int write(ExpenseVo vo) {
        return mapper.write(vo);
    }

//    public List<ExpenseVo> getExpenseList() {
//        return mapper.selectExpenseVoList();
//    }
//    public List<ExpenseVo> selectExpenseVoList(){
//        return mapper.selectExpenseVoList();
//    }

    public int getTotalExpenseCount() {
        return mapper.getTotalExpenseCount();
    }

    public int getExpenseListCnt(String area, String searchValue) {
        return mapper.getExpenseListCnt(area, searchValue);
    }

    public List<ExpenseVo> getAllExpenses(PageVo pageVo) {
        return mapper.getAllExpenses(pageVo);
    }

    public List<ExpenseVo> getExpenseList(PageVo pageVo, String area, String searchValue) {
        return mapper.getExpenseList(pageVo, area, searchValue);
    }

    public ExpenseVo getExpenseDetail(String no, Model model) {
        ExpenseVo vo = mapper.getExpenseDetail(no,model);
        return vo;
    }

    public int edit(ExpenseVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String no){
        return mapper.delete(no);

    }

}
