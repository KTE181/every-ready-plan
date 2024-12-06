
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/search.css">
    <link rel="stylesheet" href="/css/common/index.css">
    <link rel="stylesheet" href="/css/common/bottom.css">

    <link rel="stylesheet" href="/css/hr/attendancehr/attendancehr.css">
    <link rel="stylesheet" href="/css/hr/employeehr/modal.css">
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
                <div class="top-title-area">
                    <div class="menu-name">근태 관리</div>
                    <div>
                     <form action="/attendance" method="GET" class="top-title-area-form">
                        <!-- 소속부서 검색 -->
                        <div class="search-bar">
                            <select name="deptCode" id="deptCode">
                                <option value="">전체</option>
                                <option value="1">인사팀</option>
                                <option value="2">품질</option>
                                <option value="3">재무</option>
                                <option value="4">마케팅</option>
                                <option value="5">CS</option>
                                <option value="6">헬프데스크</option>
                                <option value="7">경영</option>
                                <option value="8">기획</option>
                                <option value="9">디자인</option>
                            </select>
                        </div>

                        <!-- 이름 검색 -->
                        <div class="search-bar">
                            <input type="text" name="name" id="name" placeholder="이름">
                        </div>

                        <!-- 사번 검색 -->
                        <div class="search-bar">
                            <input type="text" name="no" id="no" placeholder="사번">
                        </div>

                        <!-- 검색 버튼 -->
                        <div class="search-bar">
                            <button type="submit" class="button">검색</button>
                        </div>
                    </form>
                </div>
                </div>
                <div class="content-area">
                    <table border="1" class="attendance-table">
                        <thead>
                            <tr>
                                <th>순번</th>
                                <th>일자</th>
                                <th>사원번호</th>
                                <th>사원명</th>
                                <th>소속부서</th>
                                <th>직급</th>
                                <th>출근시간</th>
                                <th>퇴근시간</th>
                                <th>근무시간</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="attendance" items="${attendanceList}">
                                <tr>
                                    <td>${attendance.no}</td>
                                    <td>${attendance.date}</td>
                                    <td>${attendance.empNo}</td>
                                    <td>${attendance.name}</td>
                                    <td>${attendance.deptName}</td>
                                    <td>${attendance.position}</td>
                                    <td>${attendance.ciTime}</td>
                                    <td>${attendance.coTime}</td>
                                    <td>${attendance.workTime}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="bottom-content-area">
                                <div>
                                    <button class="crud-button-white" onclick="deleteEmployees('registerModal')">삭제</button>
                                </div>
                                <div></div>
                                <div></div>

                                <div class="pagination">
                                    <!-- 이전 버튼 -->
                                    <c:if test="${pageVo.currentPage > 1}">
                                        <a href="/employeehr?page=${pageVo.currentPage - 1}&name=${name}&dname=${dname}&pname=${pname}&esname=${esname}"
                                           class="page-button">이전</a>
                                    </c:if>

                                    <!-- 페이지 번호 -->
                                    <c:forEach var="i" begin="${pageVo.startPage}" end="${pageVo.endPage}">
                                        <a href="/employeehr?page=${i}&name=${name}&dname=${dname}&pname=${pname}&esname=${esname}"
                                           class="page-button ${i == pageVo.currentPage ? 'active' : ''}">
                                            ${i}
                                        </a>
                                    </c:forEach>

                                    <!-- 다음 버튼 -->
                                    <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                        <a href="/employeehr?page=${pageVo.currentPage + 1}&name=${name}&dname=${dname}&pname=${pname}&esname=${esname}"
                                           class="page-button">다음</a>
                                    </c:if>
                                </div>
                                <div></div>

                                    <div><button class="crud-button-white" id="create">등록</button></div>
                                </div>
            </div>
       </div>
    </div>
</body>
</html>
