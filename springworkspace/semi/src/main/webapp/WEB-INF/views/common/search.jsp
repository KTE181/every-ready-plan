<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/search.css">
</head>

    <div class="top-title-area">
        <form action="" class="top-title-area-form">

            <!-- <label for="">연월일</label> -->
            <!-- <div class="search-bar"><input type="text" name="year" maxlength="4" placeholder="연도"></div>
            <div class="search-bar"><input type="text" name="month" maxlength="2" placeholder="월"></div>
            <div class="search-bar"><input type="text" name="day" maxlength="2" placeholder="일"></div> -->
            
            <label for="연월 date"></label>
            <div class="search-bar"><input type="month" name=""></div>

            <label for="연월일 date"></label>
            <div class="search-bar"><input type="date" name=""></div>

            <label for="select"></label>
            <div class="search-bar">
                <select name="" id="">
                    <option value="1">소속부서 전체</option>
                    <option value="2">재무팀</option>
                    <option value="2">인사팀</option>
                </select>
            </div> 

            <label for="검색어"></label>
            <div class="search-bar">
                <select name="searchType" id="">
                    <option value="1">제목</option>
                    <option value="2">내용</option>
                    <option value="2">제목+내용</option>
                </select>
            </div> 
            <div class="search-bar"><input type="search" id="longbar"></div>
            <div class="search-bar"><button class="button">검색</button></div>
        </form>
    </div>