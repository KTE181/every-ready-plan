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
    <link rel="stylesheet" href="/css/qa/asreq/list.css">
    <link rel="stylesheet" href="/css/qa/asreq/detail.css">
    <link rel="stylesheet" href="/css/qa/asreq/edit.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/qa/asreq/list.js"></script>

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
                    <div class="menu-name">AS요청관리</div>
                    <div>
                        <form action="" class="top-title-area-form">

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
                </div>


                <!-- List Area -->
                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                <th><input type="checkbox" name=""></th>
                                <th>번호</th>
                                <th>상품일련번호</th>
                                <th>상품명</th>
                                <th>AS요청제목</th>
                                <th>고객명</th>
                                <th>고객지역</th>
                                <th>AS희망일자</th>
                                <th>등록일자</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${asreqVoList}" var="asreqVo">
                                <tr id="asreq-list" onclick="asreqDetail('${asreqVo.no}');">
                                    <td><input type="checkbox" name=""></td>
                                    <td>${asreqVo.no}</td>
                                    <td>${asreqVo.serialNumber}</td>
                                    <td>${asreqVo.name}</td>
                                    <td>${asreqVo.issueTitle}</td>
                                    <td>${asreqVo.customerName}</td>
                                    <td>${asreqVo.customerArea}</td>
                                    <td>${asreqVo.preferredServiceDate}</td>
                                    <td>${asreqVo.enrollDate}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white">삭제</button></div>
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
                    <div><button class="crud-button-white" id="asreq-write-btn" onclick="asreqWrite();">등록</button></div>
                </div>
            </div>

            <!-- Write Modal -->
            <%@ include file="/WEB-INF/views/qa/asreq/write.jsp" %> 

            <!-- Detail Modal -->
            <div id="asreq-detail">
                <form action='/qa/asreq/delete' method="get" onsubmit="return confirm('삭제하시겠습니까?');">
                    <div class="detail-content">
                        

                    </div>
                </form>
            </div>

            <!-- Edit Modal -->
            <div id="asreq-edit">
                <form id="asreq-edit-form" action='/qa/asreq/edit' method="post" onsubmit="return confirm('저장하시겠습니까?');" >
                    <div class="edit-content">
                        

                    </div>
                </form>
            </div>

        </div>
    </div>
</body>
</html>
