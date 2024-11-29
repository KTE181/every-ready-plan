package com.kh.semi.pb.vo;

import lombok.Data;

@Data
public class PageVo {
    private int listCount;      // 전체 데이터 수
    private int currentPage;    // 현재 페이지
    private int pageLimit;      // 하단 페이지 번호 개수
    private int boardLimit;     // 한 페이지에 보여줄 데이터 수

    private int maxPage;        // 가장 마지막 페이지 번호
    private int startPage;      // 하단 시작 페이지 번호
    private int endPage;        // 하단 마지막 페이지 번호
    private int offset;         // SQL OFFSET

    public PageVo(int listCount, int currentPage, int pageLimit, int boardLimit) {
        this.listCount = listCount;
        this.currentPage = currentPage;
        this.pageLimit = pageLimit;
        this.boardLimit = boardLimit;

        this.maxPage = (int) Math.ceil((double) listCount / boardLimit);
        this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
        this.endPage = Math.min(startPage + pageLimit - 1, maxPage);
        this.offset = (currentPage - 1) * boardLimit;
    }
}
