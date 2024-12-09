
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/index.css">
    <link rel="stylesheet" href="/css/common/list.css">
    <link rel="stylesheet" href="/css/common/search.css">
    <link rel="stylesheet" href="/css/common/bottom.css">
    <script defer src="/js/pv/notion.js"></script>
</head>
<body>
    <div class="container">
        
        <!-- Sidebar -->
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

        <!-- Main Content -->
		<div class="main-content">

            <!-- Header -->
            <%@ include file="/WEB-INF/views/common/header.jsp" %>

            <!-- Contents Area -->
            <div class="content-area">

                <!-- Search Area -->
                <div class="top-title-area">
                    <div class="menu-name">공지사항</div>
                    <div>
                        <form action="" class="top-title-area-form">
                            <!-- <label for="">연월일</label> -->
                            <!-- <div class="search-bar"><input type="text" name="year" maxlength="4" placeholder="연도"></div>
                            <div class="search-bar"><input type="text" name="month" maxlength="2" placeholder="월"></div>
                            <div class="search-bar"><input type="text" name="day" maxlength="2" placeholder="일"></div> -->
                            
                            <label for="연월 date"></label>
                            <div class="search-bar"></div>
            
                            <label for="연월일 date"></label>
                            <div class="search-bar"></div>
            
                            <label for="select"></label>
                            <div class="search-bar">
                               
                            </div> 
            
                            <label for="검색어"></label>
                            <div class="search-bar">
                               
                            </div> 
                            <div class="search-bar"></div>
                            <div class="search-bar"></div>
                        </form>
                    </div>
                </div>

                <!-- List Area -->
                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                <th>경조사 내용</th>
                                <th>위치</th>
                            </tr>
                        </thead>
                        <tbody id="tbodyarea">
                               
                        
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
               
    <div class="bottom-content-area">
        <div>
            
        </div>

        <!-- <div>
            <button class="ok-button">확인</button>
        </div>

        <div>
            <button class="cancle-button">취소</button>
        </div> -->

        <div>
            <div class="pagination">
                <!-- 이전 페이지 버튼 -->
                <a href="#" class="page-button previous">&laquo;</a>

                <!-- 페이지 번호 버튼들 -->
                <a href="#" class="page-button active">1</a>
                <a href="#" class="page-button">2</a>
                <a href="#" class="page-button">3</a>
                <a href="#" class="page-button">4</a>
                <a href="#" class="page-button">5</a>
                <a href="#" class="page-button">6</a>
                <a href="#" class="page-button">7</a>
                <a href="#" class="page-button">8</a>
                <a href="#" class="page-button">9</a>
                <a href="#" class="page-button">10</a>
                <!-- 다음 페이지 버튼 -->
                <a href="#" class="page-button next">&raquo;</a>
             </div>
        </div>

        <!-- <div class="bottom-content-area-5"><button class="crud-button-white">수정</button></div> -->
         

        <div><button class="crud-button-white" id="create" onclick="location.href='https://www.notion.so/154fef1413d78073be10d53428a768bb?v=bf51e1fa443843f28d86b63e174244d9'">등록</button></div>
    </div>
            
            </div>

            <!-- Modal Area -->

            <!-- Small modal -->
            <%@ include file="/WEB-INF/views/common/modal.jsp" %> 


        </div>
    </div>
</body>
</html>
