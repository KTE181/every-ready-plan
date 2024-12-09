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
    <link rel="stylesheet" href="/css/defective/write.css">
    <link rel="stylesheet" href="/css/defective/detail.css">
    <link rel="stylesheet" href="/css/defective/update.css">
    <link rel="stylesheet" href="/css/defective/bottompagedefectivecode.css">
    <script defer src="/js/defectiveCode/list.js"></script>

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
                        <div class="menu-name">불량코드</div>
                        <div>
                            <form action="" class="top-title-area-form">
                                <div class="search-bar">
                                    <form action="/qa/defectivecode/list">
                                        <label>불량코드 &nbsp;&nbsp; <input type="text" id = "longbar" name="searchValue" value="${searchValue}" placeholder="불량코드를 입력하세요"></label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label>불량유형 &nbsp;&nbsp; <input type="text" id = "longbar" name="searchValueName" value="${searchValueName}" placeholder="불량유형을 입력하세요"></label>
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
                                    <th>불량코드</th>
                                    <th>불량유형</th>
                                </tr>
                            </thead>
    
                            <tbody id="defectiveCodeTable">
                                <c:forEach items = "${defectiveCodeVo}" var = "defective">
                                    <tr>
                                        <td class = "checkbox-td"><input type = "checkbox" name = "del"></td>
                                        <td class="defectivecode-row" data-product-no="${defective.no}">${defective.no}</td>
                                        <td class="defectivecode-row" data-product-no="${defective.no}">${defective.name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                    </table>

                </div>
                
                <div class="modal-overlay" id="modalOverlay">
                    <div class="modal">
                            <button id="closeModal">×</button>
                        <h2>불량코드 등록</h2>
                        <label for="code-number">불량유형명</label>
                        <input type="text" id="defectivename">
                        <div class="modal-buttons">
                            <button class="primary" id = "registerDefectiveCode">등록</button>
                        </div>
                    </div>
                </div>

                
                <div class="modal-detail" id="modalDetail">
                    <div class="modal">
                        <button id="closeDefectiveCodeDetailModal">×</button>
                        <h2>불량코드 상세 조회</h2>
                        <label for="code-number">불량유형번호</label>
                        <input type="text" id="code-number" value="1" readonly>
                        <label for="code-name">불량유형명</label>
                        <input type="text" id="code-name" value="오염" readonly>
                        <div class="modal-buttons">
                            <button class="primary" id = "edit-button-defectivecode">수정</button>
                            <button>삭제</button>
                        </div>
                    </div>
                </div>


                <div class="modal-edit" id="modalEdit">
                    <div class="modal">
                            <button id = "closeDefectiveCodeUpdateModal">×</button>
                        <h2>불량코드 수정</h2>
                        <label for="code-number">불량유형번호</label>
                        <input type="text" id="edit-code-number" readonly>
                        <label for="code-name">불량유형명</label>
                        <input type="text" id="edit-code-name" >
                        <div class="modal-buttons">
                            <button class="primary">저장</button>
                        </div>
                    </div>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white" onclick = "delDefectiveCode();">삭제</button></div>
                    <div>
                        <div class="pagination">
                            <c:if test="${pageVo.currentPage > 1}">
                                <a href="/qa/defectivecode/list?pno=${pageVo.currentPage - 1}" class="page-button" data-page="${pageVo.currentPage - 1}">이전</a>
                            </c:if>
                        
                            <c:forEach begin="${pageVo.startPage}" end="${pageVo.endPage}" var="page">
                                <a href="/qa/defectivecode/list?pno=${page}" class="page-link ${pageVo.currentPage == page ? 'active' : ''}" id = page-button-middle data-page="${page}">${page}</a>
                            </c:forEach>
                        
                            <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                <a href="/qa/defectivecode/list?pno=${pageVo.currentPage + 1}" class="page-button" data-page="${pageVo.currentPage + 1}">다음</a>
                            </c:if>
                        </div>
                    </div>
                    <div><button class="crud-button-white" id="openModalBtn" onclick= "openModal();">등록</button></div>
                </div>
            </div>

        </div>
    </div>
</body>
</html>
