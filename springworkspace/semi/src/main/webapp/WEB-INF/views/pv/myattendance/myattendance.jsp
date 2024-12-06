<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="/css/common/bottom.css">
    <link rel="stylesheet" href="/css/pv/myattendance.css">
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
                <!-- 검색 및 타이틀 영역 -->
                <div class="top-title-area">
                    <div class="menu-name">출근내역조회</div>
                    <form action="/myattendance" method="get" class="top-title-area-form">
                        <input type="date" id="searchDate" name="searchDate" />
                        <button type="submit" class="button">검색</button>
                    </form>
                </div>

                <!-- 조회 결과 영역 -->
                <div class="result-section">
                    <table border="1" width="100%" cellspacing="0" cellpadding="5">
                        <thead>
                            <tr>
                                <th>NO</th>
                                <th>날짜</th>
                                <th>출근시간</th>
                                <th>퇴근시간</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${attendanceList}">
                                <tr>
                                    <td>${item.no}</td>
                                    <td>${fn:substring(item.date, 0, 10)}</td>
                                    <td>${fn:substring(item.ciTime, 11, 19)}</td>
                                    <td>${item.coTime != null ? fn:substring(item.coTime, 11, 19) : ''}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                  <!-- Bottom Area -->
                <div class="bottom-content-area">
                        <div>
                            <div class="pagination">
                                <c:if test="${pageVo.currentPage > 1}">
                                    <a href="/myattendance?page=${pageVo.currentPage - 1}&searchDate=${searchDate}" class="page-button">이전</a>
                                </c:if>

                                <c:forEach var="i" begin="${pageVo.startPage}" end="${pageVo.endPage}">
                                    <a href="/myattendance?page=${i}&searchDate=${searchDate}"
                                       class="page-button ${i == pageVo.currentPage ? 'active' : ''}">
                                       ${i}
                                    </a>
                                </c:forEach>

                                <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                    <a href="/myattendance?page=${pageVo.currentPage + 1}&searchDate=${searchDate}" class="page-button">다음</a>
                                </c:if>
                            </div>

                        </div>
                    </div>
            </div>





        </div>
    </div>
</body>
</html>
