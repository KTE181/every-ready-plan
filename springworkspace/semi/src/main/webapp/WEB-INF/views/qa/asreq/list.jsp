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
    <link rel="stylesheet" href="/css/qa/asreq/list.css">
    <link rel="stylesheet" href="/css/qa/asreq/modal.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/qa/asreq/list.js"></script>
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
                    <div class="menu-name">AS요청관리</div>
                    <div>
                        <form action="/qa/asreq/list" class="top-title-area-form">
                            <label for="select"></label>
                            <div class="search-bar">
                                <select name="area">
                                    <option value="">고객지역 전체</option>
                                    <option value="서울" <c:if test='${area == "서울"}'>selected</c:if>>서울</option>
                                    <option value="인천" <c:if test='${area == "인천"}'>selected</c:if>>인천</option>
                                    <option value="경기" <c:if test='${area == "경기"}'>selected</c:if>>경기</option>
                                    <option value="부산" <c:if test='${area == "부산"}'>selected</c:if>>부산</option>
                                    <option value="제주도" <c:if test='${area == "제주도"}'>selected</c:if>>제주도</option>
                                </select>
                            </div>

                            <label for="검색어"></label>
                            <div class="search-bar">
                                <select name="searchType" onchange="handleSearchType(this)">
                                    <option value="title" <c:if test='${searchType == "title"}'>selected</c:if>>AS요청제목</option>
                                    <option value="customerName" <c:if test='${searchType == "customerName"}'>selected</c:if>>고객명</option>
                                    <option value="serialNumber" <c:if test='${searchType == "serialNumber"}'>selected</c:if>>상품일련번호</option>
                                    <option value="productName" <c:if test='${searchType == "productName"}'>selected</c:if>>상품명</option>
                                </select>
                            </div>
                            <div class="search-bar"><input type="search" id="longbar" name="searchValue" value="${searchValue}"></div>

                            <div class="search-bar"><button class="button">검색</button></div>
                        </form>
                    </div>
                </div>


                <!-- List Area -->
                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                <th><input type="checkbox" name="th-checkbox" onclick="handelCheckbox(this)"></th>
                                <th>번호</th>
                                <th>상품일련번호</th>
                                <th>상품명</th>
                                <th>AS요청제목</th>
                                <th>고객명</th>
                                <th>고객지역</th>
                                <th>AS희망일자</th>
                                <th>등록일자</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${asreqVoList}" var="asreqVo">
                                <tr id="asreq-list" onclick="loadDetailModal('${asreqVo.no}');">
                                    <td onclick="event.stopPropagation();"><input type="checkbox" name="listCheckbox"></td>
                                    <td>${asreqVo.no}</td>
                                    <td>${asreqVo.serialNumber}</td>
                                    <td>${asreqVo.productName}</td>
                                    <td>${asreqVo.issueTitle}</td>
                                    <td>${asreqVo.customerName}</td>
                                    <td>${asreqVo.customerArea}</td>
                                    <td>${asreqVo.preferredServiceDate}</td>
                                    <td>${asreqVo.enrollDate}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white" onclick="asreqDeleteMultiple();">삭제</button></div>
                    <div>
                        <div class="pagination">
                            <c:if test="${pvo.startPage != 1}"> 
                                <a href="/qa/asreq/list?pno=${pvo.startPage-1}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button previous">&laquo;</a>
                            </c:if>
                            <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                <a href="/qa/asreq/list?&pno=${i}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button <c:if test='${i == pvo.currentPage}'>active</c:if>">${i}</a>
                            </c:forEach>
                            <c:if test="${pvo.endPage != pvo.maxPage}"> 
                                <a href="/qa/asreq/list?pno=${pvo.endPage+1}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button next">&raquo;</a>
                            </c:if>   
                         </div>
                    </div>
                    <div><button class="crud-button-white" id="asreq-write-btn" onclick="loadWriteModal();">등록</button></div>
                </div>
            </div>

            <!-- AS요청 모달 -->
            <div id="asreq-modal">
                <form id="asreq-form">
                    <div class="modal-content">
                        <span class="modal-close">&times;</span>
            
                        <div class="modal-title"></div>
                        <div class="required-text">* 는 필수입력사항입니다.</div>
                        <div class="title-text">AS요청정보</div>
            
                        <input type="hidden" name="no">
                        <input type="hidden" name="productNo">

                        <div class="modal-cont">
                            <div><span class="required-text">*</span> 상품일련번호</div>
                            <div>
                                <input type="text" name="serialNumber" disabled> 
                                <input type="button" id="search-button" onclick="productList();" value="상품검색">
                            </div>
                            
                        </div>
                        <div class="modal-cont">
                            <div><span class="required-text">*</span> 상품명</div>
                            <input type="text" name="name" disabled>
                        </div>
                        <div class="modal-cont" id="purchase-date">

                        </div>
                        <div class="modal-cont">
                            <div>무상가능여부</div>
                            <div id="warranty-yn">

                            </div>
                        </div>
            
                        <div class="modal-cont">
                            <div><span class="required-text">*</span> 고객명</div>
                            <input type="text" name="customerName">
                        </div>
                        <div class="modal-cont">
                            <div><span class="required-text">*</span> 고객주소</div>
                            <div>
                                <select name="customerArea">
                                    <option value="">- 선택 -</option>
                                    <option value="서울">서울</option>
                                    <option value="인천">인천</option>
                                    <option value="경기">경기</option>
                                    <option value="부산">부산</option>
                                    <option value="제주도">제주도</option>
                                </select>
                                <input type="text" name="customerAdress" placeholder="상세주소">
                            </div>
                        </div>
                        <div class="modal-cont">
                            <div><span class="required-text">*</span> 고객핸드폰번호</div>
                            <input type="number" name="customerPhone" placeholder="숫자만 입력하세요 (11자 이하)">
                        </div>
            
                        <div class="modal-cont" id="preferred-service-date">

                        </div>
                        
                        <div class="modal-cont long-textbox">
                            <div><span class="required-text">*</span> AS요청제목</div>
                            <input type="text" name="issueTitle">
                        </div>

                        <div class="modal-cont long-textbox">
                            <div><span class="required-text">*</span> AS요청내용(증상)</div>
                            <textarea name="issueDescription"></textarea>
                        </div>
                        
                        <div class="modal-cont" id="enroll-date">
                            <label for="">등록일자</label>
                            <input type="text" name="enrollDate" disabled>
                        </div>

                        <div class="modal-cont" id="modify-date">
                            <label for="">수정일자</label>
                            <input type="text" name="modifyDate" disabled>
                        </div>

                        <div></div>

                        <div class="button-container"></div>
                    </div>
                </form>
            </div>

             <!-- 상품검색 모달 -->
             <div id="product-modal">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>
                    <div class="modal-title">상품검색</div>
                    <div></div>

                    <div id="product-modal-search-area">
                        <form id="product-search-form">
                        <select name="productSearchType">
                            <option value="serialNumber">상품일련번호</option>
                            <option value="productName">상품명</option>
                        </select>
                        <input type="search" name="productSearchValue">
                        <input type="button" onclick="productData()" value="검색">
                        </form>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <th>선택</th>
                                <th>상품일련번호</th>
                                <th>상품명</th>
                                <th>가격</th>
                                <th>무상보증기간(년)</th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                    <div class="page-area"></div>
                    <div class="button-container"><input type="button" onclick="selectProduct();" value="선택"></div>
                </div>
            </div>
            

        </div>
    </div>
</body>
</html>
