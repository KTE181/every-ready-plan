<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/index.css">
    <link rel="stylesheet" href="/css/finance/account/list.css">
    <link rel="stylesheet" href="/css/finance/account/detail.css">
    <link rel="stylesheet" href="/css/finance/account/edit.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/finance/account/list.js"></script>
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
                        <div class="menu-name">은행계좌관리</div>
                        <div>
                            <form action="" class="top-title-area-form">

                                <div class="search-bar">
                                    <label for="select">카테고리</label>
                                    <select name="" id="">
                                        <option value="1">은행코드</option>
                                        <option value="2">은행명</option>
                                        <option value="3">계좌명</option>
                                        <option value="4">계좌번호</option>
                                    </select>
                                </div>
                                <div class="search-bar"><label for="">내용검색&nbsp&nbsp</label><input
                                        type="search" id="longbar">
                                </div>
                                <div class="search-bar">
                                    <button class="crud-button-white">검색</button>
                                </div>
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
                                    <th>은행코드</th>
                                    <th>은행명</th>
                                    <th>계좌번호</th>
                                    <th>계좌별명</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach items="${accountVoList}" var="accountVo">
                                    <tr id="account-list" onclick="accountDetail('${accountVo.no}');">
                                        <td><input type="checkbox" name=""></td>
                                        <td>${accountVo.no}</td>
                                        <td>${accountVo.bankCode}</td>
                                        <td>${accountVo.bankName}</td>
                                        <td>${accountVo.accountNo}</td>
                                        <td>${accountVo.accountName}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Bottom Area -->
                    <div class="bottom-content-area">
                        <div>
                            <button class="crud-button-white">삭제</button>
                        </div>
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
                        <div>
                            <button class="crud-button-white" id="account-write-btn" onclick="accountWrite();">등록</button>
                        </div>
                    </div>
                </div>

                <!-- Write Modal -->
                <%@ include file="/WEB-INF/views/finance/account/write.jsp" %>

                <!-- Detail Modal -->

                <div id="account-detail">
                    <form action='/finance/account/delete' method="get" onsubmit="return confirm('삭제하시겠습니까?');">
                        <div class="detail-content">
                        </div>
                    </form>
                </div>

                <!-- Edit Modal -->
                <div id="account-edit">
                    <form id="account-edit-form" action='/finance/account/edit' method="post" onsubmit="return confirm('저장하시겠습니까?');" >
                        <div class="edit-content">

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>    
</html>