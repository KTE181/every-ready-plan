<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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


                            <!-- 모달 오버레이 -->
                            <div class="modal-detail" id="modalDetail">
                                <div class="modal">
                                    <button id="closeDetailModal">×</button>
                                    <h2>상품 상세 조회</h2>
                                    <div class="form-container">
                                        <div class="section-title">상품정보</div>
                        
                                        <div class="image-upload-container">
                                            <div id="image">제품 이미지</div>
                                        </div>
                        
                                        <div class="form-group">
                                            <label for="product-code">품목코드</label>
                                            <input type="text" id="item-code" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="product-name">상품명</label>
                                            <input type="text" id="product-name" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="product-price">가격</label>
                                            <input type="text" id="product-price" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="product-quantity">수량</label>
                                            <input type="text" id="product-quantity" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="serial-number">일련번호</label>
                                            <input type="text" id="serial-number" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="manufacturer">생산공정</label>
                                            <input type="text" id="factoryName"  readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="manufacturer-address">생산공장</label>
                                            <input type="text" id="factory-address"  readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="warranty">보증기간</label>
                                            <input type="text" id="warranty"  readonly>
                                        </div>
                                        <!-- <div class="form-group">
                                            <label for="import-date">입고일</label>
                                            <input type="text" id="import-date" readonly>
                                        </div> -->
                                    </div>
                                    <div class="modal-buttons">
                                        <button class="primary" id = "edit-button">수정</button>
                                        <button>삭제</button>
                                    </div>
                                </div>
                            </div>


                    </div>

            </div>
            </div>
        </body>

        </html>