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
    <link rel="stylesheet" href="/css/product/write.css">
    <script defer src="/js/product/list.js"></script>
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

                <!-- Search Area -->
                <div class="top-title-area">
                        <div class="menu-name">상품 조회</div>
                        <div>
                            <form action="" class="top-title-area-form">
                                <!-- <label for="">연월일</label> -->
                                <!-- <div class="search-bar"><input type="text" name="year" maxlength="4" placeholder="연도"></div>
                                <div class="search-bar"><input type="text" name="month" maxlength="2" placeholder="월"></div>
                                <div class="search-bar"><input type="text" name="day" maxlength="2" placeholder="일"></div> -->

                                <label for="연월 date"></label>
                                <div class="search-bar"><input type="month" name=""></div>

                                <label for="연월일 date"></label>
                                <div class="search-bar"><input type="date" name=""></div>

                                <label for="select"></label>
                                <div class="search-bar">
                                    <select name="" id="">
                                        <option value="1">소속부서 전체</option>
                                        <option value="2">재무팀</option>
                                        <option value="2">인사팀</option>
                                    </select>
                                </div>

                                <label for="검색어"></label>
                                <div class="search-bar">
                                    <select name="searchType" id="">
                                        <option value="1">제목</option>
                                        <option value="2">내용</option>
                                        <option value="2">제목+내용</option>
                                    </select>
                                </div>
                                <div class="search-bar"><input type="search" id="longbar"></div>
                                <div class="search-bar"><button class="button">검색</button></div>
                            </form>
                        </div>
                    </div>


            

                <!--<h1>Board List</h1> -->

            <div class = "middle-content-area">

                <table class = "list-area">
                    <thead>
                        <tr>
                            <th><input type = "checkbox" onclick ="handleCheckBox(this);"></th>
                            <th>번호</th>
                            <th>품목코드</th>
                            <th>상품명</th>
                            <th>가격</th>
                            <th>일련번호</th>
                            <th>입고일</th>
                            <th>생산공정</th>
                            <th>등록일자</th>

                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items = "${productVo}" var = "product">
                            <tr>
                                <td class = "checkbox-td"><input type = "checkbox" name = "del"></td>
                                <td>${product.no}</td>
                                <td>${product.itemCode}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                                <td>${product.serialNumber}</td>
                                <td>${product.receivedDate}</td>
                                <td>${product.factoryName}</td>
                                <td>${product.enrollDate}</td>
 
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="modal-overlay" id="modalOverlay">
                <div class="modal">
                    <h2>상품 등록</h2>
                    <div id = "closeModalBtn">
                        <button onclick = "closeModal();">×</button>
                    </div>

                    <div class="form-container">
                        <div class="section-title">상품정보</div>
        
                        <div class="image-upload-container">
                            <div class="image-placeholder">제품 이미지</div>
                        </div>
        
                        <div class="form-group">
                            <label for="product-code">품목코드</label>
                            <input type="text" id="product-code" value="0001">
                        </div>
                        <div class="form-group">
                            <label for="product-name">상품명</label>
                            <input type="text" id="product-name" value="LG트윈냉장고">
                        </div>
                        <div class="form-group">
                            <label for="product-price">가격</label>
                            <input type="text" id="product-price" value="1500000">
                        </div>
                        <div class="form-group">
                            <label for="product-quantity">수량</label>
                            <input type="text" id="product-quantity" value="32">
                        </div>
                        <div class="form-group">
                            <label for="serial-number">일련번호</label>
                            <input type="text" id="serial-number" value="자동부여">
                        </div>
                        <div class="form-group">
                            <label for="manufacturer">생산공정</label>
                            <input type="text" id="manufacturer" value="파주(주)">
                        </div>
                        <div class="form-group">
                            <label for="manufacturer-address">생산공장</label>
                            <input type="text" id="manufacturer-address" value="서울특별시 둘리구 호이동">
                        </div>
                        <div class="form-group">
                            <label for="warranty">보증기간</label>
                            <input type="text" id="warranty" value="3년">
                        </div>
                        <div class="form-group">
                            <label for="import-date">입고일</label>
                            <input type="date" id="import-date" value="2014-11-01">
                        </div>
                    </div>
                    <div class="modal-buttons">
                        <button class="primary">등록</button>
                        <button>삭제</button>
                    </div>
                </div>
            </div>
                <!-- Bottom Area -->
                <div class="bottom-content-area">
                                    <div><button class="crud-button-white" onclick = "delProduct()">삭제</button></div>
                                    <div>
                                        <div class="pagination">
                                            <!-- 이전 페이지 버튼 -->
                                            <a href="#" class="page-button previous">&laquo;</a>

                                            <!-- 페이지 번호 버튼들 -->
                                            <a href="#" class="page-button active">1</a>
                                            <a href="#" class="page-button">2</a>
                                            <a href="#" class="page-button">3</a>
                                            <a href="#" class="page-button">4</a>
                                            <a href="#" class="page-button">5</a>
                                            <a href="#" class="page-button">6</a>
                                            <a href="#" class="page-button">7</a>
                                            <a href="#" class="page-button">8</a>
                                            <a href="#" class="page-button">9</a>
                                            <a href="#" class="page-button">10</a>
                                            <!-- 다음 페이지 버튼 -->
                                            <a href="#" class="page-button next">&raquo;</a>
                                         </div>
                                    </div>
                                    <div><button class="crud-button-white" id="openModalBtn" onclick= "openModal();">등록</button></div>
                                </div>
                            </div>

                </div>
                <div id="product-detail">
                    <form action="/qa/product/write" method="post">
                        <div class="detail-content">
                            
    
                        </div>
                    </form>
                </div>
    </div>
</body>
</html>
