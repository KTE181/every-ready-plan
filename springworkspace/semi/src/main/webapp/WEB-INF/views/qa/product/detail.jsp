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
    <link rel="stylesheet" href="/css/product/product.css">
    <script defer src="/js/product/product.js"></script>
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
            <h2>상품 상세 조회</h2>
            <div class="form-container">
                <div class="section-title">상품정보</div>

                <div class="image-upload-container">
                    <div class="image-placeholder">제품 이미지</div>
                </div>

                <div class="form-group">
                    <label for="product-code">품목코드</label>
                    <input type="text" id="product-code" value="0001" readonly>
                </div>
                <div class="form-group">
                    <label for="product-name">상품명</label>
                    <input type="text" id="product-name" value="LG트윈냉장고" readonly>
                </div>
                <div class="form-group">
                    <label for="product-price">가격</label>
                    <input type="text" id="product-price" value="1500000" readonly>
                </div>
                <div class="form-group">
                    <label for="product-quantity">수량</label>
                    <input type="text" id="product-quantity" value="32" readonly>
                </div>
                <div class="form-group">
                    <label for="serial-number">일련번호</label>
                    <input type="text" id="serial-number" value="자동부여" readonly>
                </div>
                <div class="form-group">
                    <label for="manufacturer">생산공정</label>
                    <input type="text" id="manufacturer" value="파주(주)" readonly>
                </div>
                <div class="form-group">
                    <label for="manufacturer-address">생산공장</label>
                    <input type="text" id="manufacturer-address" value="서울특별시 둘리구 호이동" readonly>
                </div>
                <div class="form-group">
                    <label for="warranty">보증기간</label>
                    <input type="text" id="warranty" value="3년" readonly>
                </div>
                <div class="form-group">
                    <label for="import-date">입고일</label>
                    <input type="date" id="import-date" value="2014-11-01" readonly>
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
                            <th>품목코드</th>
                            <th>가격</th>
                            <th>제품명</th>
                            <th>일련번호</th>
                            <th>생산공정</th>
                            <th>생산공정 위치</th>
                            <th>보증기간</th>
                            <th>입고일</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items = "${productVo}" var = "product">
                            <tr>
                                <td>${product.itemCode}</td>
                                <td>${product.price}</td>
                                <td>${product.name}</td>
                                <td>${product.serialNumber}</td>
                                <td>${product.factoryName}</td>
                                <td>${product.factoryLocation}</td>
                                <td>${product.warrantyPeriod}</td>
                                <td>${product.enrollDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                     -->
            </div>

        </div>
    </div>
</body>
</html>
