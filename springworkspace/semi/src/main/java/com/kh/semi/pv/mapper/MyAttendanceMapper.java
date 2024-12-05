package com.kh.semi.pv.mapper;

import com.kh.semi.attendance.vo.AttendanceVo;
import com.kh.semi.pb.vo.PageVo;
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

    @Select("""
            SELECT *
            FROM attendance
            WHERE emp_no = #{empNo}
            AND del_yn = 'N'
            AND TO_CHAR(ci_time, 'YYYY-MM-DD') = #{searchDate}
            """)
    List<AttendanceVo> selectAttendanceListByDate(@Param("empNo") String empNo, @Param("searchDate") String searchDate);

    @Select("""
        SELECT *
        FROM attendance
        WHERE emp_no = #{empNo}
        AND del_yn = 'N'
        <if test="searchDate != null and searchDate != ''">
            AND TO_CHAR(ci_time, 'YYYY-MM-DD') = #{searchDate}
        </if>
        ORDER BY ci_time DESC
        LIMIT #{boardLimit} OFFSET #{offset}
        """)
    List<AttendanceVo> selectAttendanceListWithPaging(
            @Param("empNo") String empNo,
            @Param("searchDate") String searchDate,
            @Param("boardLimit") int boardLimit,
            @Param("offset") int offset);


    // 전체 데이터 개수 조회
    @Select("""
            SELECT COUNT(*)
            FROM attendance
            WHERE emp_no = #{empNo}
            AND del_yn = 'N'
            <if test="searchDate != null and searchDate != ''">
                AND TO_CHAR(ci_time, 'YYYY-MM-DD') = #{searchDate}
            </if>
            """)
    int selectAttendanceCount(
            @Param("empNo") String empNo,
            @Param("searchDate") String searchDate);


}
