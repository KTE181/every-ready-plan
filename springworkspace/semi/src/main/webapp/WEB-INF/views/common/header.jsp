<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/common/header.css">
    <script defer src="/js/common/header.js"></script>
</head>

	<div class="top-bar">
        <button class="button">출근</button>
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
        
        <div class="display-none" id="profile-menu">
            <div onclick="redirectToMypage()">마이페이지</div>
            <div onclick="logout()">로그아웃</div>
        </div>
    </div>