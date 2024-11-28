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
    <link rel="stylesheet" href="/css/defective/defectivecode.css">
    <script defer src="/js/defectiveCode/defectiveCode.js"></script>
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


            <button onclick="openModal()">모달 열기</button>

                <!-- 모달 오버레이 -->
                <div class="modal-overlay" id="modalOverlay">
                    <div class="modal">
                        <button class="modal-close" onclick="closeModal()">×</button>
                        <h2>불량코드 정보</h2>
                        <label for="code-number">불량유형번호</label>
                        <input type="text" id="code-number" value="1" readonly>
                        <label for="code-name">불량유형명</label>
                        <input type="text" id="code-name" value="오염" readonly>
                        <div class="modal-buttons">
                            <button class="primary">수정</button>
                            <button>삭제</button>
                        </div>
                    </div>
                </div>


        </div>
    </div>
</body>
</html>
