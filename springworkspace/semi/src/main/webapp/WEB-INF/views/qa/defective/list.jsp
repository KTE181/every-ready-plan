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
    <link rel="stylesheet" href="/css/defective/write.css">
    <link rel="stylesheet" href="/css/defective/update.css">
    <link rel="stylesheet" href="/css/defective/detail.css">
    <link rel="stylesheet" href="/css/defective/bottompagedefective.css">
    <script defer src="/js/defective/list.js"></script>

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
                        <div class="menu-name">불량 상품</div>
                        <div>
                            <form action="" class="top-title-area-form">
                                <!-- <label for="">연월일</label> -->
                                <!-- <div class="search-bar"><input type="text" name="year" maxlength="4" placeholder="연도"></div>
                                <div class="search-bar"><input type="text" name="month" maxlength="2" placeholder="월"></div>
                                <div class="search-bar"><input type="text" name="day" maxlength="2" placeholder="일"></div> -->

                                <!-- <label for="연월 date"></label>
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
                                <div class="search-bar"><button class="button">검색</button></div> -->
                                <div class="search-bar">
                                    <form action="/qa/defective/list">
                                        <label>상품이름 &nbsp;&nbsp; <input type="text" id = "longbar" name="searchValue" value="${searchValue}" placeholder="검색할 상품이름을 입력하세요"></label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label>불량코드 &nbsp;&nbsp; <input type="text" id = "longbar" name="searchValueError" value="${searchValueError}" placeholder="불량코드를 입력하세요"></label>
                                        <div class="search-bar"><button id="searchButton">검색</button></div>
                                    </form>
                                </div>
                            </form>
                        </div>
                    </div>


                <div class = "middle-content-area">
                    <table class = "list-area">
                            <thead>
                                <tr>
                                    <th><input type = "checkbox" onclick ="handleCheckBox(this);"></th>
                                    <th>상품번호</th>
                                    <th>품목코드</th>
                                    <th>상품명</th>
                                    <th>일련번호</th>
                                    <th>불량코드</th>
                                    <th>입고일</th>
                                    <th>생산공정</th>
                                    <th>등록일자</th>
                                </tr>
                            </thead>
    
                            <tbody id="defectiveTable">
                                <c:forEach items = "${defectiveVo}" var = "defective">
                                    <tr>
                                        <th class = "checkbox-td"><input type = "checkbox" name = "del"></th>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.no}</td>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.itemCode}</td>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.productName}</td>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.serialNumber}</td>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.defectiveCode}</td>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.receivedDate}</td>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.factoryLocation}</td>
                                        <td class="product-row" data-product-no="${defective.no}">${defective.enrollDate}</td>
    
                                    </tr>
                                </c:forEach>
                            </tbody>
                    </table>
                </div>
                
            <!-- <form action = "/qa/defective/write" method = "post"> -->
                <div class="modal-overlay" id="modalOverlay">
                    <div class="modal">
                        <h2>불량 상품 등록</h2>
                        <div class = "modal-close">
                            <button onclick = "closeModal();">×</button>
                        </div>
       
                        <div class="form-container">
                            <div class="section-title">상품정보</div>
            
                            <div class="image-upload-container">
                                <div class="image-placeholder">제품 이미지</div>
                            </div>
                            
                            <div class="form-group">
                                <label for="serialno">일련번호</label>
                                <input type="text" id="dfserialno">
                            </div>
                            <div class="form-group">
                                <label for="product-name">상품명</label>
                                <input type="text" id="dfproduct-name">
                            </div>
                            <div class="form-group">
                                <label for="price">가격</label>
                                <input type="text" id="dfprice">
                            </div>
                            <div class="form-group">
                                <label for="productno">상품번호</label>
                                <input type="text" id="dfproductno">
                            </div>
                            <div class="form-group">
                                <label for="defective-code">불량코드</label>
                                <select id = "defectivecode-select">
                                    
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="defective-name">불량명</label>
                                <input type="text" id="dfdefective-name" value = "" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defective-content">불량 내용</label>
                                <textarea id= "dftext-area"></textarea>
                            </div>
            
                        </div>
                        <div class="modal-buttons">
                            <button class="primary" id ="registerDefectiveProduct">등록</button>
                            <button>삭제</button>
                        </div>
                    </div>
                </div>
            <!-- </form> -->

                <div class="modal-defectivedetail" id="modalDetail">
                    <div class="modal">
                        <button id="closeDetailModal">×</button>
                        <h2>불량 상품 상세 조회</h2>
                        <div class="form-container">
                            <div class="section-title">상품정보</div>
            
                            <div class="image-upload-container">
                                <div class="image-placeholder">제품 이미지</div>
                            </div>
                            <div class="form-group">
                                <label for="productno">상품NO</label>
                                <input type="text" id="productno1" readonly>
                            </div>
                            <div class="form-group">
                                <label for="price">가격</label>
                                <input type="text" id="price1" readonly>
                            </div>
                            <div class="form-group">
                                <label for="product-name">상품명</label>
                                <input type="text" id="product-name1" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defective-code">불량코드</label>
                                <input type="text" id="defective-code1" readonly>
                            </div>
                            <div class="form-group">
                                <label for="serialno">일련번호</label>
                                <input type="text" id="serial-no1" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defective-name">불량명</label>
                                <input type="text" id="defective-name1" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defective-content">불량 내용</label>
                                <textarea class= "text-area" id = "textarea1"></textarea>
                            </div>
            
                        </div>
                        <div class="modal-buttons">
                            <button class="primary" id = "edit-button">수정</button>
                            <button>삭제</button>
                        </div>
                    </div>
                </div>



                <div class="modal-defective-edit" id="modaldefectiveEdit">
                    <div class="modal">
                        <button id="closeDefectiveUpdateModal">×</button>
                        <h2>불량 상품 수정</h2>
                        <div class="form-container">
                            <div class="section-title">상품정보</div>
            
                            <div class="image-upload-container">
                                <div class="image-placeholder">제품 이미지</div>
                            </div>
            
                            <div class="form-group">
                                <label for="productno">상품번호</label>
                                <input type="text" id="edit-productno" readonly>
                            </div>
                            <div class="form-group">
                                <label for="price">가격</label>
                                <input type="text" id="edit-price" readonly>
                            </div>
                            <div class="form-group">
                                <label for="productname">상품명</label>
                                <input type="text" id="edit-productname" readonly>
                            </div>
                            <div class="form-group">
                                <label for="serialno">일련번호</label>
                                <input type="text" id="edit-serialNumber" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defectivecode">불량코드</label>
                                <select id = defectivecode-select-edit>

                                </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="defectivename">불량명</label>
                                <input type="text" id="edit-defectivename" value = "" readonly>
                            </div>
                            <div class="form-group">
                                <label for="defectivecontent">불량 내용</label>
                                <textarea id= "edit-text-area"></textarea>
                            </div>
            
                        </div>
                        <div class="modal-buttons">
                            <button class="primary">저장</button>
                        </div>
                    </div>
                </div>



                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white" onclick = "delDefective();">삭제</button></div>
                    <div>
                        <div class="pagination">
                            <c:if test="${pageVo.currentPage > 1}">
                                <a href="/qa/defective/list?pno=${pageVo.currentPage - 1}" class="page-button" data-page="${pageVo.currentPage - 1}">이전</a>
                            </c:if>
                        
                            <c:forEach begin="${pageVo.startPage}" end="${pageVo.endPage}" var="page">
                                <a href="/qa/defective/list?pno=${page}" class="page-link ${pageVo.currentPage == page ? 'active' : ''}" id = page-button-middle data-page="${page}">${page}</a>
                            </c:forEach>
                        
                            <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                <a href="/qa/defective/list?pno=${pageVo.currentPage + 1}" class="page-button" data-page="${pageVo.currentPage + 1}">다음</a>
                            </c:if>
                        </div>
                    </div>
                    <div><button class="crud-button-white" id="openModalBtn" onclick= "openModal();">등록</button></div>
                </div>
            </div>

            </div>
        </div>
        </div>
    </div>
</body>
</html>
