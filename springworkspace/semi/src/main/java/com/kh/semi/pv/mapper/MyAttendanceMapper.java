package com.kh.semi.pv.mapper;

import com.kh.semi.attendance.vo.AttendanceVo;
import com.kh.semi.pb.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyAttendanceMapper {

    // 전체 데이터 수 조회
    @Select("""
            <script>
                SELECT COUNT(*)
                FROM attendance
                WHERE emp_no = #{empNo}
                AND del_yn = 'N'
                <if test="searchDate != null and searchDate != ''">
                    AND TO_CHAR(ci_time, 'YYYY-MM-DD') = #{searchDate}
                </if>
            </script>
            """)
    int countAttendanceWithSearch(
            @Param("empNo") String empNo,
            @Param("searchDate") String searchDate
    );

    // 검색 조건 + 페이징 처리된 출근 내역 조회
    @Select("""
            <script>
                SELECT *
                FROM attendance
                WHERE emp_no = #{empNo}
                AND del_yn = 'N'
                <if test="searchDate != null and searchDate != ''">
                    AND TO_CHAR(ci_time, 'YYYY-MM-DD') = #{searchDate}
                </if>
                ORDER BY ci_time DESC
                OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
            </script>
            """)
    List<AttendanceVo> searchAttendanceWithPaging(
            @Param("empNo") String empNo,
            @Param("searchDate") String searchDate,
            @Param("offset") int offset,
            @Param("limit") int limit
    );
}
