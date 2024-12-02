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
    <link rel="stylesheet" href="/css/qa/inspection/list.css">
    <link rel="stylesheet" href="/css/qa/inspection/modal.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/qa/inspection/list.js"></script>
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
                    <div class="menu-name">품질검사관리</div>
                    <div>
                        <form action="" class="top-title-area-form">

                            <label for="select"></label>
                            <div class="search-bar">
                                <select name="inspection-list">
                                    <option>검사유형 전체</option>
                                </select>
                            </div>

                            <label for="select"></label>
                            <div class="search-bar">
                                <select name="" id="">
                                    <option>진행상태 전체</option>
                                    <option value="2">재무팀</option>
                                    <option value="2">인사팀</option>
                                </select>
                            </div>

                            <label for="select"></label>
                            <div class="search-bar">
                                <select name="" id="">
                                    <option>합격여부 전체</option>
                                    <option value="P">합격</option>
                                    <option value="F">불합격</option>
                                </select>
                            </div>

                            <label for="검색어"></label>
                            <div class="search-bar">
                                <select name="searchType" id="">
                                    <option value="1">상품일련번호</option>
                                    <option value="2">상품명</option>
                                </select>
                            </div>
                            <div class="search-bar"><input type="search" id="longbar"></div>
                            <div class="search-bar"><button class="button">검색</button></div>
                        </form>
                    </div>
                </div>


                <!-- List Area -->
                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                <th><input type="checkbox" name=""></th>
                                <th>번호</th>
                                <th>상품일련번호</th>
                                <th>상품명</th>
                                <th>검사유형</th>
                                <th>진행상태</th>
                                <th>검사일자</th>
                                <th>합격여부</th>
                                <th>등록일자</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${inspectionVoList}" var="inspectionVo">
                                <tr onclick="inspectionDetail('${inspectionVo.no}');">
                                    <td><input type="checkbox" name=""></td>
                                    <td>${inspectionVo.no}</td>
                                    <td>${inspectionVo.serialNumber}</td>
                                    <td>${inspectionVo.productName}</td>
                                    <td>${inspectionVo.inspectionName}</td>
                                    <td>${inspectionVo.statusName}</td>
                                    <td>${inspectionVo.inspectionDate}</td>
                                    <td>${inspectionVo.successYn}</td>
                                    <td>${inspectionVo.enrollDate}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white">삭제</button></div>
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
                    <div><button class="crud-button-white" onclick="inspectionWrite();">등록</button></div>
                </div>
            </div>

            <!-- Write Modal -->
            <div id="inspection-write">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>

                    <div class="modal-title">품질 검사 등록</div>
                    <div class="required-text">* 는 필수입력사항입니다.</div>

                    <div class="title-text">품질검사정보</div>

                    <input type="hidden" name="productNo" value="1">

                    <div class="modal-cont">
                        <label for="">상품일련번호</label>
                        <div>
                            <input type="text" name="serialNumber" disabled> 
                            <input type="button" class="product-search-button" value="상품검색">
                        </div>
                        
                    </div>
                    <div class="modal-cont">
                        <label for="">상품명</label>
                        <input type="text" name="productName" disabled>
                    </div>
                    <div class="modal-cont">
                        <label>검사유형</label>
                        <select name="inspectionCode">
                            <option value="1">내열성</option>
                            <option value="2">충격</option>
                        </select>
                    </div>
                    <div class="modal-cont">
                        <label>진행상태</label>
                        <select name="statusCode">
                            <option value="1">미검사</option>
                            <option value="2">검사완료</option>
                        </select>
                    </div>

                    <div class="modal-cont">
                        <label>검사일자</label>
                        <input type="text" name="inspectionDate">
                    </div>
                    
                    <div class="modal-cont">
                        <label for="">합격여부</label>
                        <div>
                            <input type="radio" name="successYn" value="P">PASS
                            <input type="radio" name="successYn" value="F">FAIL
                        </div>
                    </div>

                    <div></div>
                    <div class="button-container"><input type="button" id="inspection-write-btn" value="등록"></div>
                </div>
            </div>

            <!-- Detail Modal -->
            <div id="inspection-detail">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>

                    <div class="modal-title">품질 검사 상세</div>
                    <div id="required-text"></div>

                    <div class="title-text">품질검사정보</div>

                    <input type="hidden" name="no">
                    <input type="hidden" name="productNo">

                    <div class="modal-cont">
                        <label for="">상품일련번호</label>
                        <input type="text" name="serialNumber" disabled> 
                        
                    </div>
                    <div class="modal-cont">
                        <label for="">상품명</label>
                        <input type="text" name="productName" disabled>
                    </div>
                    <div class="modal-cont">
                        <label>검사유형</label>
                        <select name="inspectionCode" disabled>
                            <option value="1">내열성</option>
                            <option value="2">충격</option>
                        </select>
                    </div>
                    <div class="modal-cont">
                        <label>진행상태</label>
                        <select name="statusCode" disabled>
                            <option value="1">미검사</option>
                            <option value="2">검사완료</option>
                        </select>
                    </div>

                    <div class="modal-cont">
                        <label>검사일자</label>
                        <input type="text" name="inspectionDate" disabled>
                    </div>
                    
                    <div class="modal-cont">
                        <label for="">합격여부</label>
                        <input type="text" name="successYn" disabled>
                    </div>
                    <div></div>
                    <div class="button-container">
                        <div><input type="button" id="inspection-edit-btn" value="수정"></div>
                        <div><input type="button" id="inspection-delete-btn" value="삭제"></div>
                    </div>
                    </div>
                </div>
            </div>

            <!-- Edit Modal -->
            <div id="inspection-edit">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>

                    <div class="modal-title">품질 검사 수정</div>
                    <div class="required-text">* 는 필수입력사항입니다.</div>

                    <div class="title-text">품질검사정보</div>

                    <input type="hidden" name="no">
                    <input type="hidden" name="productNo">

                    <div class="modal-cont">
                        <label for="">상품일련번호</label>
                        <div>
                            <input type="text" name="serialNumber" disabled> 
                            <input type="button" class="product-search-button" value="상품검색">
                        </div>
                        
                    </div>
                    <div class="modal-cont">
                        <label for="">상품명</label>
                        <input type="text" name="productName" disabled>
                    </div>
                    <div class="modal-cont">
                        <label>검사유형</label>
                        <select name="inspectionCode">
                            <option value="1">내열성</option>
                            <option value="2">충격</option>
                        </select>
                    </div>
                    <div class="modal-cont">
                        <label>진행상태</label>
                        <select name="statusCode">
                            <option value="1">미검사</option>
                            <option value="2">검사완료</option>
                        </select>
                    </div>

                    <div class="modal-cont">
                        <label>검사일자</label>
                        <input type="text" name="inspectionDate">
                    </div>
                    
                    <div class="modal-cont">
                        <label for="">합격여부</label>
                        <div>
                            <input type="radio" name="successYn" value="P">PASS
                            <input type="radio" name="successYn" value="F">FAIL
                        </div>
                    </div>

                    <div></div>
                    <div class="button-container"><input type="button" id="inspection-save-btn" value="저장"></div>
                </div>
            </div>


        </div>
    </div>
</body>
</html>
