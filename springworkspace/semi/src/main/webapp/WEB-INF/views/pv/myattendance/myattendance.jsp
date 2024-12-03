
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/pb/pb.css">
    <link rel="stylesheet" href="/css/common/index.css">
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
                <div>
                <div class="modal-title">출근내역조회</div>
                <form>폼태그를 이용한(조건 : 일자,근무결과)검색영역 </form>
                </div>
                <div>
                <table border="1" width="100%" cellspacing="0" cellpadding="5">
                            <thead>
                                <tr>
                                    <th>NO</th>
                                    <th>날짜</th>
                                    <th>출근시간</th>
                                    <th>퇴근시간</th>
                                    <th>근무시간</th>
                                    <th>근무결과</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${attendanceList}">
                                    <tr>
                                        <td>${item.no}</td>
                                        <td>${item.date}</td>
                                        <td>${item.ciTime}</td>
                                        <td>${item.coTime}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </div>
                <div>페이징영역</div>
            </div>




        </div>
    </div>
</body>
</html>
