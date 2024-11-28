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
    <link rel="stylesheet" href="/css/defective/defective.css">
    <script defer src="/js/defective/defective.js"></script>
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

                <div class="modal-overlay" id="modalOverlay">
                    <div class="modal">
                        <button class="modal-close" onclick="closeModal()">×</button>
                        <h2>불량 상품 상세 조회</h2>
                        <div class="form-container">
                            <div class="section-title">상품정보</div>

                            <div class="image-upload-container">
                                <div class="image-placeholder">제품 이미지</div>
                            </div>

                            <div class="form-group">
                                <label for="serialno">일련번호</label>
                                <label>
                                    <select id = serialno>
                                        <option value>0001</option>
                                        <option value>0002</option>
                                        <option value>0003</option>
                                        <option value>0004</option>
                                    </select>
                                </label>
                            </div>
                            <div class="form-group">
                                <label for="productno">상품NO</label>
                                <input type="text" id="productno" value="0001" readonly>
                            </div>
                            <div class="form-group">
                                <label for="price">가격</label>
                                <input type="text" id="price" value="150000" readonly>
                            </div>
                            <div class="form-group">
                                <label for="product-name">상품명</label>
                                <input type="text" id="product-name" value="1500000" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defective-code">불량코드</label>
                                <input type="text" id="defective-code" value="ERR_01" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defective-name">불량명</label>
                                <input type="text" id="defective-name" value="오염" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defective-content">불량 내용</label>
                                <textarea class= "text-area" readonly></textarea>
                            </div>

                        </div>
                        <div class="modal-buttons">
                            <button class="primary">수정</button>
                            <button>삭제</button>
                        </div>
                    </div>
                </div>

            <!--
                <div class = "middle-content-area">
                    <table class = "list-area">
                            <thead>
                                <tr>
                                    <th>상품번호</th>
                                    <th>가격</th>
                                    <th>상품명</th>
                                    <th>불량코드</th>
                                    <th>일련번호</th>
                                    <th>불량명</th>
                                    <th>불량 내용</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items = "${voList}" var = "defective">
                                    <tr>
                                        <td>${defective.no}</td>
                                        <td>${defective.price}</td>
                                        <td>${defective.productName}</td>
                                        <td>${defective.defectiveCode}</td>
                                        <td>${defective.serialNumber}</td>
                                        <td>${defective.defectiveName}</td>
                                        <td>${defective.description}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                    </table>
                </div>

                -->

        </div>
    </div>
</body>
</html>
