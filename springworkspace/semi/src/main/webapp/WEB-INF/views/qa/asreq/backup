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
                                <select name="searchType">
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
                                <tr id="asreq-list" onclick="asreqDetail('${asreqVo.no}');">
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
                    <div><button class="crud-button-white" id="asreq-write-btn" onclick="asreqWrite();">등록</button></div>
                </div>
            </div>

            <!-- Write Modal -->
            <div id="asreq-write">
                <form action="/qa/asreq/write" method="post" onsubmit="return confirm('등록하시겠습니까?');">
                    <div class="write-content">
                        <span class="write-close">&times;</span>
            
                        <div class="modal-title">AS 요청 등록</div>
                        <div id="required-text">* 는 필수입력사항입니다.</div>
            
                        <div class="title-text">AS상품정보</div>
            
                        <input type="hidden" name="productNo">
                        <div class="modal-cont">
                            <label for="">상품일련번호</label>
                            <div>
                                <input type="text" name="serialNumber" disabled> 
                                <input type="button" class="product-search-button" id="asreq-write-search-btn" value="상품검색">
                            </div>
                            
                        </div>
                        <div class="modal-cont">
                            <label for="">상품명</label>
                            <input type="text" name="name" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">상품구매일자</label>
                            <input type="date" name="purchaseDate">
                        </div>
                        <div class="modal-cont">
                            <label for="">무상가능여부</label>
                            <div>
                                <input type="radio" name="warrantyYn" value="Y"> Y
                                <input type="radio" name="warrantyYn" value="N"> N
                            </div>
                        </div>
            
                        <div class="title-text">AS요청정보</div>
            
                        <div class="modal-cont">
                            <label for="">고객명</label>
                            <input type="text" name="customerName">
                        </div>
                        <div class="modal-cont">
                            <label>고객주소</label>
                            <div>
                                <select name="customerArea">
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
                            <label>고객핸드폰번호</label>
                            <input type="text" name="customerPhone" placeholder="숫자만 입력하세요">
                        </div>
            
                        <div class="modal-cont">
                            <label>AS희망일자</label>
                            <input type="date" name="preferredServiceDate">
                        </div>
                        
                        <div class="modal-cont long-textbox">
                            <label>AS요청제목</label>
                            <input type="text" name="issueTitle">
                        </div>
                        <div class="modal-cont long-textbox">
                            <label>AS요청내용(증상)</label>
                            <textarea name="issueDescription"></textarea>
                        </div>
                        <div></div>
                        <div class="button-container"><input type="submit" value="등록"></div>
                    </div>
                </form>
            </div>


            <!-- Detail Modal -->
            <div id="asreq-detail">
                <form action='/qa/asreq/delete' method="get" onsubmit="return confirm('삭제하시겠습니까?');">
                    <div class="detail-content">
                        <span class="detail-close" onclick="asreqDetailClose();">&times;</span>
    
                        <div class="modal-title">AS 요청 상세</div>
                        <div id="required-text"></div>

                        <div class="title-text">AS상품정보</div>
                        <input type="hidden" name="no"">
                        <input type="hidden" name="productNo">

                        <div class="modal-cont">
                            <label for="">상품일련번호</label>
                            <div>
                                <input type="text" name="serialNumber" disabled>
                            </div>

                        </div>
                        <div class="modal-cont">
                            <label for="">상품명</label>
                            <input type="text" name="productName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">상품구매일자</label>
                            <input type="text" name="purchaseDate" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">무상가능여부</label>
                            <input type="text" name="warrantyYn" disabled>
                        </div>

                        <div class="title-text">AS요청정보</div>

                        <div class="modal-cont">
                            <label for="">고객명</label>
                            <input type="text" name="customerName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label>고객주소</label>
                            <div>
                                <select name="customerArea" disabled>
                                    <option value="서울">서울</option>
                                    <option value="인천">인천</option>
                                    <option value="경기">경기</option>
                                    <option value="부산">부산</option>
                                    <option value="제주도">제주도</option>
                                </select>
                                <input type="text" name="customerAdress" placeholder="상세주소" disabled>
                            </div>
                        </div>
                        <div class="modal-cont">
                            <label>고객핸드폰번호</label>
                            <input type="text" name="customerPhone" placeholder="숫자만 입력하세요" disabled>
                        </div>

                        <div class="modal-cont">
                            <label>AS희망일자</label>
                            <input type="text" name="preferredServiceDate" disabled>
                        </div>

                        <div class="modal-cont long-textbox">
                            <label>AS요청제목</label>
                            <input type="text" name="issueTitle" disabled>
                        </div>
                        <div class="modal-cont long-textbox">
                            <label>AS요청내용(증상)</label>
                            <textarea name="issueDescription" disabled></textarea>
                        </div>

                        <div></div>

                        <div class="button-container">
                            <div><input type="button" id="asreq-receive-button" value="접수하기"></div>
                            <div><input type="button" id="asreq-edit-button" value="수정"></div>
                            <div><input type="button" id="asreq-delete-button" value="삭제"></div>
                        </div>
                        </div>

                    </div>
                </form>
            </div>


            <!-- Edit Modal -->
            <div id="asreq-edit">
                <form id="asreq-edit-form" action='/qa/asreq/edit' method="post" onsubmit="return confirm('저장하시겠습니까?');" >
                    <div class="edit-content">
                        <span class="edit-close" onclick="asreqEditClose();">&times;</span>
    
                        <div class="modal-title">AS 요청 수정</div>
                        <div id="required-text">* 는 필수입력사항입니다.</div>

                        <div class="title-text">AS상품정보</div>
                        <input type="hidden" name="no">
                        <input type="hidden" name="productNo">

                        <div class="modal-cont">
                            <label for="">상품일련번호</label>
                            <div>
                                <input type="text" name="serialNumber" disabled>
                                <input type="button" class="product-search-button" id="asreq-edit-search-btn" value="상품검색">
                            </div>

                        </div>
                        <div class="modal-cont">
                            <label for="">상품명</label>
                            <input type="text" name="productName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">상품구매일자</label>
                            <input type="date" name="purchaseDate">
                        </div>
                        <div class="modal-cont">
                            <label for="">무상가능여부</label>
                            <div>
                                <input type="radio" name="warrantyYn" value="Y"> Y
                                <input type="radio" name="warrantyYn" value="N"> N
                            </div>
                        </div>

                        <div class="title-text">AS요청정보</div>

                        <div class="modal-cont">
                            <label for="">고객명</label>
                            <input type="text" name="customerName">
                        </div>
                        <div class="modal-cont">
                            <label>고객주소</label>
                            <div>
                                <select name="customerArea">
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
                            <label>고객핸드폰번호</label>
                            <input type="text" name="customerPhone" placeholder="숫자만 입력하세요">
                        </div>

                        <div class="modal-cont">
                            <label>AS희망일자</label>
                            <input type="date" name="preferredServiceDate">
                        </div>

                        <div class="modal-cont long-textbox">
                            <label>AS요청제목</label>
                            <input type="text" name="issueTitle">
                        </div>
                        <div class="modal-cont long-textbox">
                            <label>AS요청내용(증상)</label>
                            <textarea name="issueDescription"></textarea>
                        </div>

                        <div></div>

                        <div class="button-container"><input type="submit" value="저장"></div>

                    </div>
                </form>
            </div>

            <!-- SearchProduct Modal -->
            <div id="search-product">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>
        
                    <div class="modal-title">상품검색</div>
                    <div></div>

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
                        </tbody>
                    </table>
                    <div class="button-container"><input type="button" id="product-select-btn" value="선택"></div>
                </div>
            </div>
            

        </div>
    </div>
</body>
</html>
