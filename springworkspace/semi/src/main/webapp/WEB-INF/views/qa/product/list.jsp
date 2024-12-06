<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    
    <link rel="stylesheet" href="/css/common/bottom.css">
    <script defer src="/js/product/list.js"></script>

    
</head>
<body>


    <div class="container">

        <!-- Sidebar -->
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

        <!-- Main Content -->
		<div class="main-content">

            <!-- Header -->
            <%@ include file="/WEB-INF/views/qa/product/siheader.jsp" %>

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

                                <!-- <label for="연월 date"></label>
                                <div class="search-bar"><input type="month" name=""></div>

                                <label for="연월일 date"></label>
                                <div class="search-bar"><input type="date" name=""></div> -->

                                 <!-- <div class="search-bar"><input type="search" id="longbar"></div> 
                                 <label>상품명: <input type="text" id="searchName"></label>
                                <label>일련번호: <input type="text" id="searchSerialNumber"></label>
                                <div class="search-bar"><button id="searchButton">검색</button></div>  -->
                                <div class="search-bar">
                                    <form action="/qa/product/list">
                                        <label>상품이름 &nbsp;&nbsp; <input type="text" id = "longbar" name="searchValueName" value="${searchValueName}" placeholder="검색할 상품이름을 입력하세요"></label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label>일련번호 &nbsp;&nbsp; <input type="text" id = "longbar" name="searchValue" value="${searchValue}" placeholder="검색할 일련번호를 입력하세요"></label>
                                        <div class="search-bar"><button id="searchButton">검색</button></div>
                                    </form>
                                </div>
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

                    <tbody id="productTable">
                        <c:forEach items = "${productVo}" var = "product">
                            <tr>
                                <th class = "checkbox-td"><input type = "checkbox" name = "del"></th>
                                <td class="product-row" id = "producteditNo" data-product-no="${product.no}">${product.no}</td>
                                <td class="product-row" data-product-no="${product.no}">${product.itemCode}</td>
                                <td class="product-row" data-product-no="${product.no}">${product.name}</td>
                                <td class="product-row" data-product-no="${product.no}">${product.price}</td>
                                <td class="product-row" data-product-no="${product.no}">${product.serialNumber}</td>
                                <td class="product-row" data-product-no="${product.no}">${product.receivedDate}</td>
                                <td class="product-row" data-product-no="${product.no}">${product.factoryName}</td>
                                <td class="product-row" data-product-no="${product.no}">${product.enrollDate}</td>
                               
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


                

            
                <div class="modal-detail" id="modalDetail">
                    <div class="modal">
                        <button id="closeModal">×</button>
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
                            <div class="form-group">
                                <label for="import-date">입고일</label>
                                <input type="text" id="import-date" readonly>
                            </div>
                        </div>
                        <div class="modal-buttons">
                            <button class="primary" id = "edit-button">수정</button>
                            <button>삭제</button>
                        </div>
                    </div>
                </div>

            
            
                <div class="modal2" id="modalEdit">
                    <div class="modal-content">
                            <button id = "closeProductUpdateModal">×</button>
                        <h2>상품 수정</h2>

                        <div class="form-container">
                            <div class="section-title">상품정보</div>
            
                            <div class="image-upload-container">
                                <div class="image-placeholder">제품 이미지</div>
                            </div>
            
                            <div class="form-group">
                                <label for="product-code">품목코드</label>
                                <input type="text" id="edit-item-code">
                            </div>
                            <div class="form-group">
                                <label for="product-name">상품명</label>
                                <input type="text" id="edit-product-name">
                            </div>
                            <div class="form-group">
                                <label for="product-price">가격</label>
                                <input type="text" id="edit-product-price">
                            </div>
                            <!-- <div class="form-group">
                                <label for="product-quantity">수량</label>
                                <input type="text" id="product-quantity" value="${vo.price}">
                            </div> -->
                            <!-- <div class="form-group">
                                <label for="serial-number">일련번호</label>
                                <input type="text" id="serial-number" value="${vo.serialNumber}">
                            </div> -->
                            <div class="form-group">
                                <label for="manufacturer">생산공정</label>
                                <input type="text" id="edit-factory-name">
                            </div>
                            <div class="form-group">
                                <label for="manufacturer-address">생산공장</label>
                                <input type="text" id="edit-factory-address">
                            </div>
                            <div class="form-group">
                                <label for="warranty">보증기간</label>
                                <input type="text" id="edit-warranty-period">
                            </div>
                            <!-- <div class="form-group">
                                <label for="import-date">입고일</label>
                                <input type="date" id="import-date" value="2014-11-01">
                            </div> -->
                        </div>
                        <div class="modal-buttons">
                            <button class="primary">등록</button>
                            <button>삭제</button>
                        </div>
                    </div>
            </div>
           

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
                            <input type="text" id="item-code1">
                        </div>
                        <div class="form-group">
                            <label for="product-name">상품명</label>
                            <input type="text" id="product-name1">
                        </div>
                        <div class="form-group">
                            <label for="product-price">가격</label>
                            <input type="text" id="product-price1">
                        </div>
                        <div class="form-group">
                            <label for="manufacturer">생산공정</label>
                            <input type="text" id="manufacturer1">
                        </div>
                        <div class="form-group">
                            <label for="manufacturer-address">생산공장</label>
                            <input type="text" id="manufacturer-address1">
                        </div>
                        <div class="form-group">
                            <label for="warranty">보증기간</label>
                            <input type="text" id="warranty1">
                        </div>
                        <div class="form-group">
                            <label for="import-date">입고일</label>
                            <input type="date" id="import-date1">
                        </div>
                    </div>

                    <div class="modal-buttons">
                        <button class="primary" id ="registerProduct">등록</button>
                        <button>삭제</button>
                    </div>
                </div>
            </div>



            <div class="modal-itemCode" id="modalItemCode">
                <div class="modal">
                        <button id="closeModal">×</button>
                    <h2>품질코드 등록</h2>
                    <label for="code-number">상품명</label>
                    <input type="text" id="itemCodeName">
                    <div class="modal-buttons">
                        <button class="primary" id = "registerItemCode">등록</button>
                    </div>
                </div>
            </div>

            

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                                    <div><button class="crud-button-white" onclick = "delProduct()">삭제</button></div>
                                    <div>
                                        <div class="pagination">
                                            <c:if test="${pageVo.currentPage > 1}">
                                                <a href="/qa/product/list?pno=${pageVo.currentPage - 1}" class="page-button" data-page="${pageVo.currentPage - 1}">이전</a>
                                            </c:if>
                                        
                                            <c:forEach begin="${pageVo.startPage}" end="${pageVo.endPage}" var="page">
                                                <a href="/qa/product/list?pno=${page}" class="page-link ${pageVo.currentPage == page ? 'active' : ''}" id = page-button-middle data-page="${page}">${page}</a>
                                            </c:forEach>
                                        
                                            <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                                <a href="/qa/product/list?pno=${pageVo.currentPage + 1}" class="page-button" data-page="${pageVo.currentPage + 1}">다음</a>
                                            </c:if>
                                        </div>
                                        
                                    </div>
                                    <div><button class="crud-button-white" id="openModalBtn" onclick= "openItemCodeModal();">품질코드 등록</button></div>
                                    <div><button class="crud-button-white" id="openModalBtn" onclick= "openModal();">상품 등록</button></div>
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


<link rel="stylesheet" href="/css/common/index.css">
<link rel="stylesheet" href="/css/product/product.css">
<link rel="stylesheet" href="/css/product/write.css">
<link rel="stylesheet" href="/css/product/detail.css">
<link rel="stylesheet" href="/css/product/update.css">
<link rel="stylesheet" href="/css/product/bottompage.css">