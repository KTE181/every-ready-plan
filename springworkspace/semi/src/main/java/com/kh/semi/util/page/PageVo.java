package com.kh.semi.util.page;

import lombok.Data;

@Data
public class PageVo {

    private int listCount;
    private int currentPage;
    private int pageLimit;
    private int boardLimit;

    private int maxPage;
    private int startPage;
    private int endPage;

    private int offset;

    public PageVo(int listCount, int currentPage, int pageLimit, int boardLimit) {
        this.listCount = listCount;
        this.currentPage = currentPage;
        this.pageLimit = pageLimit;
        this.boardLimit = boardLimit;

        this.maxPage = (int)Math.ceil((double)listCount / boardLimit);
        this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
        this.endPage = startPage + pageLimit - 1;
        if(endPage > maxPage) {
            endPage = maxPage;
        }
        this.offset = boardLimit * (currentPage-1);
    }
}

