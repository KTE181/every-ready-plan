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
    <link rel="stylesheet" href="/css/finance/account/list.css">
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
                        <option value="1">은행코드</option>
                        <option value="2">은행명</option>
                        <option value="3">계좌명</option>
                        <option value="4">계좌번호</option>
                        </select>
                    </div>
                    <div class="search-bar"><label for="">내용검색&nbsp&nbsp</label><input type="search" id="longbar"></div>
                    <div class="search-bar"><button class="crud-button-white">검색</button></div>
                    </form>
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
                                <c:forEach begin="1" end="14">
                                    <tr>
                                        <td><input type="checkbox" name=""></td>
                                        <td>1</td>
                                        <td>10</td>
                                        <td>국민</td>
                                        <td>1234567890</td>
                                        <td>국민계좌1</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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