<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>출근 등록</title>
    <link rel="stylesheet" href="/css/common/header.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/common/header.js"></script>
    <script>
        <c:if test="${not empty sessionScope.alertMsg}">
            alert('${sessionScope.alertMsg}');
        </c:if>
        <c:remove var="alertMsg" scope="session" />
    </script>
    
</head>
<body>
    
    <div class="top-bar">
        <button class="button" onclick="openAttendanceModal()">출근</button>
        <div id="attendance-modal" class="modal" style="display: none;">
            <div class="modal-content">
                <h3>출근 하시겠습니까?</h3>
                <div class="modal-buttons">
                    <button class="modal-button" onclick="submitAttendance()">확인</button>
                    <button class="modal-button" onclick="closeAttendanceModal()">취소</button>
                </div>
            </div>
        </div>

        <button class="buttonout" onclick="openAttendanceoutModal()">퇴근</button>
        <div id="attendanceout-modal" class="modal" style="display: none;">
            <div class="modal-content">
                <h3>퇴근 하시겠습니까?</h3>
                <div class="modal-buttons">
                    <button class="modal-button" onclick="submitAttendanceout()">확인</button>
                    <button class="modal-button" onclick="closeAttendanceoutModal()">취소</button>
                </div>
            </div>
        </div>

        <div class="welcome">${loginEmployeeVo.name}님 환영합니다</div>

        <div class="image-circle">
            <c:choose>
                <c:when test="${not empty loginEmployeeVo}">
                    <img src="${pageContext.request.contextPath}${loginEmployeeVo.profileImage}" alt="프로필 이미지">
                </c:when>
                <c:otherwise>
                    IMAGE
                </c:otherwise>
            </c:choose>
        </div>

        <div  class="display-none" id="profile-menu" >
            <div onclick="redirectToMypage()">마이페이지</div>
            <div onclick="logout()">로그아웃</div>
        </div>
    </div>

    <input type="hidden" id="empNo" value="${loginEmployeeVo != null ? loginEmployeeVo.no : ''}">

</body>
</html>
