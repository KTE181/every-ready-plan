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
    <link rel="stylesheet" href="/css/product/product.css">
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
                        <div class="menu-name">상품 조회</div>
                        <div>
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
                    </div>


            

                <!--<h1>Board List</h1> -->

            <div class = "middle-content-area">

                <table class = "list-area">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>품목코드</th>
                            <th>상품명</th>
                            <th>가격</th>
                            <th>일련번호</th>
                            <th>입고일</th>
                            <th>생산공정</th>
                            <th>등록일자</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items = "${productVo}" var = "product">
                            <tr>
                                <td>${product.no}</td>
                                <td>${product.itemCode}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                                <td>${product.serialNumber}</td>
                                <td>${product.receivedDate}</td>
                                <td>${product.factoryName}</td>
                                <td>${product.enrollDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
                <!-- Bottom Area -->
                <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

        </div>
    </div>
</body>
</html>
