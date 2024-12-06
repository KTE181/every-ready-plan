package com.kh.semi.pv.mapper;

import com.kh.semi.pb.vo.PageVo;
import com.kh.semi.pv.vo.PvSalaryVo;
import com.kh.semi.pv.vo.PvVacationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PvSalaryMapper {
    List<PvSalaryVo> getSalaryList(String no, String date, PageVo pvo);

    @Select("""
            SELECT COUNT(NO) FROM SALARY
            SALARY WHERE EMP_NO =#{no}
            AND DEL_YN ='N'
            """)
    int getSalaryCnt(String no);
}
