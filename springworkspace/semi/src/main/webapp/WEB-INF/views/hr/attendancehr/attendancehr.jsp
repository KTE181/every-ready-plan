
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
    <link rel="stylesheet" href="/css/hr/attendancehr/modal.css">
    <script defer src="/js/hr/attendancehr/attendancehr.js"></script>
    <script defer src="/js/hr/attendancehr/modal.js"></script>

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
                     <form action="/attendancehr" method="GET" class="top-title-area-form">
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
                            <input type="text" name="empNo" id="empNo" placeholder="사번">
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
                                <th><input type="checkbox" onclick="toggleAll(this)" /></th>
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
                                    <td><input type="checkbox" name="attendanceCheck" value="${attendance.no}" /></td>
                                    <td>${attendance.no}</td>
                                    <td>${attendance.attendanceDate}</td>
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
                                    <button class="crud-button-white" onclick="deleteSelected()">삭제</button>
                                </div>
                                <div></div>
                                <div></div>

                                <div class="pagination">
                                    <c:if test="${pageVo.currentPage > 1}">
                                        <a href="/attendancehr?page=${pageVo.currentPage - 1}&deptCode=${deptCode}&name=${name}&empNo=${empNo}"class="page-button">이전</a>
                                    </c:if>
                                    <c:forEach var="i" begin="${pageVo.startPage}" end="${pageVo.endPage}">
                                        <a href="?page=${i}&deptCode=${param.deptCode}&name=${param.name}&empNo=${param.empNo}"
                                           class="page-button ${i == pageVo.currentPage ? 'active' : ''}">
                                            ${i}
                                        </a>
                                    </c:forEach>
                                    <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                        <a href="/attendancehr?page=${pageVo.currentPage + 1}&deptCode=${deptCode}&name=${name}&empNo=${empNo}"class="page-button">다음</a>
                                    </c:if>
                                </div>
                                <div></div>

                                    <div><button id="openModal" class="crud-button-white">등록</button></div>
                </div>

                <!-- 모달 창 -->
                <div id="attendanceModal" class="modal">
                    <div class="modal-content">
                        <span class="close" id="closeModal">&times;</span>
                        <h3>근태 등록</h3>
                        <form id="attendanceForm">
                                <div class="form-group">
                                    <label for="attendanceDate">일자</label>
                                    <input type="date" id="attendanceDate" name="attendanceDate">
                                </div>
                                <div class="form-group">
                                    <label for="registerEmpNo">사번</label>
                                    <input type="text" id="registerEmpNo" name="registerEmpNo" placeholder="사번 입력">
                                    <button type="button" id="searchEmployeeButton">검색</button>
                                </div>

                                <div class="form-group">
                                    <label for="employeeName">사원명</label>
                                    <input type="text" id="employeeName" name="employeeName" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="deptName">소속부서</label>
                                    <input type="text" id="deptName" name="deptName" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="position">직급</label>
                                    <input type="text" id="position" name="position" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="ciTime">출근시간</label>
                                    <input type="time" id="ciTime" name="ciTime" >
                                </div>
                                <div class="form-group">
                                    <label for="coTime">퇴근시간</label>
                                    <input type="time" id="coTime" name="coTime">
                                </div>
                                <div class="form-group">
                                    <label for="workTime">근무시간</label>
                                    <input type="text" id="workTime" name="workTime" disabled>
                                </div>
                                <div class="modal-submit-wrapper">
                                    <button type="submit" class="crud-button-white">등록</button>
                                </div>
                            </form>
                    </div>
                </div>


            </div>
       </div>
    </div>
</body>
</html>
