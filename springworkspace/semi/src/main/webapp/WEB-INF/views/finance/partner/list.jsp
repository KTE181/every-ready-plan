
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
    <link rel="stylesheet" href="/css/finance/partner/list.css">
    <script defer src="/js/finance/partner/list.js"></script>
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
                    <form action="" class="top-title-area-form">
                    <!-- <div class="search-bar"><label for="">날짜&nbsp&nbsp</label><input type="text" name="year" maxlength="4">년
                        <input type="text" name="month" maxlength="2">월
                        <input type="text" name="day" maxlength="2">일</div> -->
                    <div class="search-bar" ><label for="">&nbsp&nbsp&nbsp&nbsp&nbsp카테고리&nbsp&nbsp&nbsp&nbsp&nbsp</label><select name="" id="">
                        <option value="1">회사명</option>
                        <option value="2">사업자등록번호</option>
                        <option value="3">사업자명</option>
                        <option value="4">업종</option>
                        </select>
                    </div>
                    <div class="search-bar"><label for="">내용검색&nbsp&nbsp</label><input type="search" id="longbar"></div>
                    <div class="search-bar"><button class="crud-button-white">검색</button></div>
                    </form>
                </div>


                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                <th><input type="checkbox" name=""></th>
                                <th>번호</th>
                                <th>업종코드</th>
                                <th>업종</th>
                                <th>회사명</th>
                                <th>사업자 등록 번호</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach begin="1" end="14">
                                <tr>
                                    <td><input type="checkbox" name=""></td>
                                    <td>111</td>
                                    <td>456</td>
                                    <td>정보통신업</td>
                                    <td>KH 정보 아카데미</td>
                                    <td>1234567890</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            <!-- Modal Area -->

            <!-- Small modal -->
            <%@ include file="/WEB-INF/views/common/modal.jsp" %> 


        </div>
    </div>
</body>
</html>
