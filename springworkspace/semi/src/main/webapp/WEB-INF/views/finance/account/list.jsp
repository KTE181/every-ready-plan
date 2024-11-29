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
                                    <!-- <div class="menu-name">은행계좌관리</div> -->
                                    <div>
                                        <form action="" class="top-title-area-form">
                                            <!-- <div class="search-bar"><label for="">날짜&nbsp&nbsp</label><input type="text" name="year" maxlength="4">년
                            <input type="text" name="month" maxlength="2">월
                            <input type="text" name="day" maxlength="2">일</div> -->

                                            <div class="search-bar">
                                                <label
                                                    for="select">&nbsp&nbsp&nbsp&nbsp&nbsp카테고리&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                                                <select name="" id="">
                                                    <option value="1">은행코드</option>
                                                    <option value="2">은행명</option>
                                                    <option value="3">계좌명</option>
                                                    <option value="4">계좌번호</option>
                                                </select>
                                            </div>
                                            <div class="search-bar"><label for="">내용검색&nbsp&nbsp</label><input
                                                    type="search" id="longbar"></div>
                                            <div class="search-bar"><button class="crud-button-white">검색</button></div>
                                        </form>


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
                                                        <tr id ="account-list" onclick="accountDetail('${accountVo.no}');">
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
                                    </div>
                                </div>
                                <!-- Bottom Area -->
                                <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

                            </div>

                            <!-- Modal Area -->

                            <!-- Small modal -->
                            <%@ include file="/WEB-INF/views/common/modal.jsp" %>


                    </div>
            </div>
        </body>

        </html>