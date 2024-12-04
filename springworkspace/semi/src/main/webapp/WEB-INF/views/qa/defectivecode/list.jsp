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
                                    <form action="/qa/defectivecode/list">
                                        <label>불량코드 : <input type="text" id = "longbar" name="searchValue" value="${searchValue}" placeholder="불량코드를 입력하세요"></label>
                                        <label>불량유형 : <input type="text" id = "longbar" name="searchValueName" value="${searchValueName}" placeholder="불량유형을 입력하세요"></label>
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
    </div>
</body>
</html>
