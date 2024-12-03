package com.kh.semi.pv.mapper;

import com.kh.semi.attendance.vo.AttendanceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyAttendanceMapper {

    @Select("""
            SELECT *
            FROM attendance
            WHERE emp_no = #{empNo}
            AND del_yn = 'N'
            """)
    List<AttendanceVo> selectAttendanceList(@Param("empNo") String empNo);
}
