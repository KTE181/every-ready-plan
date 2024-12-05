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
    <link rel="stylesheet" href="/css/qa/faultcode/list.css">
    <link rel="stylesheet" href="/css/qa/faultcode/modal.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/qa/faultcode/list.js"></script>
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
                    <div class="menu-name">고장코드관리</div>
                    <div>
                        <form action="/qa/faultcode/list" class="top-title-area-form">
                            <label for="검색어"></label>
                            <div class="search-bar">
                                <select name="searchType" onchange="handleSearchType(this)">
                                    <option value="faultName" <c:if test='${searchType == "faultName"}'>selected</c:if>>고장유형명</option>
                                    <option value="faultCode" <c:if test='${searchType == "faultCode"}'>selected</c:if>>고장코드</option>
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
                                <th>고장코드</th>
                                <th>고장유형명</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${faultcodeVoList}" var="vo">
                                <tr onclick="loadDetailModal('${vo.no}');">
                                    <td onclick="event.stopPropagation();"><input type="checkbox" name="listCheckbox"></td>
                                    <td>${vo.no}</td>
                                    <td>${vo.faultName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white" onclick="deleteMultiple();">삭제</button></div>
                    <div>
                        <div class="pagination">
                            <c:if test="${pvo.startPage != 1}"> 
                                <a href="/qa/faultcode/list?pno=${pvo.startPage-1}&searchType=${searchType}&searchValue=${searchValue}" class="page-button previous">&laquo;</a>
                            </c:if>
                            <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                <a href="/qa/faultcode/list?&pno=${i}&searchType=${searchType}&searchValue=${searchValue}" class="page-button <c:if test='${i == pvo.currentPage}'>active</c:if>">${i}</a>
                            </c:forEach>
                            <c:if test="${pvo.endPage != pvo.maxPage}"> 
                                <a href="/qa/faultcode/list?pno=${pvo.endPage+1}&searchType=${searchType}&searchValue=${searchValue}" class="page-button next">&raquo;</a>
                            </c:if>   
                         </div>
                    </div>
                    <div><button class="crud-button-white" onclick="loadWriteModal();">등록</button></div>
                </div>
            </div>

            <!-- 고장코드 모달 -->
            <div id="faultcode-modal">
                <form id="faultcode-form">
                    <div class="modal-content">
                        <span class="modal-close">&times;</span>

                        <div class="modal-title"></div>
                        <div class="required-text">* 는 필수입력사항입니다.</div>
                        <div class="title-text"></div>

                        <div id="fault-code" class="modal-cont">
                            <div>고장코드</div>
                            <input type="text" name="no"> 
                        </div>

                        <div class="modal-cont">
                            <div><span class="required-text">*</span>고장유형명</div>
                            <input type="text" name="faultName"> 
                        </div>

                        <div></div>
                        
                        <div class="button-container"></div>
                    </div>
                </form>
            </div>
