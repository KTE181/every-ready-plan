<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/pb/employee.css">
    <link rel="stylesheet" href="/css/common/search.css">
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
                    <div class="menu-name">직원검색</div>
                    <div>
                        <form action="/employee" method="GET" class="top-title-area-form">
                            <!-- 부서 검색 -->
                            <label for="department"></label>
                            <div class="search-bar">
                                <select name="department" id="department">
                                    <option value="">부서명</option>
                                    <option value="인사팀">인사팀</option>
                                    <option value="품질">품질</option>
                                    <option value="재무">재무</option>
                                    <option value="마케팅">마케팅</option>
                                    <option value="CS">CS</option>
                                    <option value="헬프데스크">헬프데스크</option>
                                    <option value="경영">경영</option>
                                    <option value="기획">기획</option>
                                    <option value="디자인">디자인</option>
                                </select>
                            </div>

                            <!-- 직급 검색 -->
                            <label for="position"></label>
                            <div class="search-bar">
                                <select name="position" id="position">
                                    <option value="">직급</option>
                                    <option value="인턴">인턴</option>
                                    <option value="사원">사원</option>
                                    <option value="대리">대리</option>
                                    <option value="과장">과장</option>
                                    <option value="차장">차장</option>
                                    <option value="부장">부장</option>
                                    <option value="상무">상무</option>
                                    <option value="사장">사장</option>
                                </select>
                            </div>

                            <!-- 이름 검색 -->
                            <div class="search-bar">
                                <input type="text" name="name" id="name" placeholder="이름">
                            </div>

                            <!-- 검색 버튼 -->
                            <div class="search-bar">
                                <button type="submit" class="button">검색</button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- 검색 결과 -->
                <table border=1>
                    <thead>
                        <tr>
                            <th>사번</th>
                            <th>이름</th>
                            <th>직급</th>
                            <th>부서</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                            <th>생년월일</th>
                            <th>성별</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="employee" items="${employeeVoList}">
                            <tr>
                                <td>${employee.no}</td>
                                <td>${employee.name}</td>
                                <td>${employee.position}</td>
                                <td>${employee.department}</td>
                                <td>${employee.phoneNumber}</td>
                                <td>${employee.email}</td>
                                <td>${employee.birth}</td>
                                <td>${employee.gender}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="pagination">
                    <c:if test="${pageVo.currentPage > 1}">
                        <a href="/employee?page=${pageVo.currentPage - 1}&department=${department}&position=${position}&name=${name}" class="page-button">이전</a>
                    </c:if>

                    <c:forEach var="i" begin="${pageVo.startPage}" end="${pageVo.endPage}">
                        <a href="/employee?page=${i}&department=${department}&position=${position}&name=${name}"
                           class="page-button ${i == pageVo.currentPage ? 'active' : ''}">
                           ${i}
                        </a>
                    </c:forEach>

                    <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                        <a href="/employee?page=${pageVo.currentPage + 1}&department=${department}&position=${position}&name=${name}" class="page-button">다음</a>
                    </c:if>
                </div>


            </div>
        </div>
    </div>
</body>
</html>