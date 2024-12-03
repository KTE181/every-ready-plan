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
                                <select name="searchType">
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
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${faultcodeVoList}" var="vo">
                                <tr onclick="faultCodeDetail('${vo.no}');">
                                    <td onclick="event.stopPropagation();"><input type="checkbox" name="listCheckbox"></td>
                                    <td>${vo.no}</td>
                                    <td>${vo.faultName}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
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
                    <div><button class="crud-button-white" onclick="faultcodeEnroll();">등록</button></div>
                </div>
            </div>

            <!-- Enroll Modal -->
            <div id="faultcode-enroll">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>
                    <div class="modal-title">고장코드 등록</div>
                    <div id="required-text">* 는 필수입력사항입니다.</div>
                    <input type="hidden" name="no">
                    <div class="modal-cont">
                        <label for="">고장유형명</label>
                        <input type="text" name="faultName"> 
                    </div>
                    <div></div>
                    <div></div>
                    <div class="button-container"><input type="button" id="faultcode-enroll-btn" value="등록"></div>
                </div>
            </div>

            <!-- Detail Modal -->
            <div id="faultcode-detail">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>
                    <div class="modal-title">고장코드 상세</div>
                    <div id="required-text"></div>
                    <input type="hidden" name="no">
                    <div class="modal-cont">
                        <label for="">고장유형명</label>
                        <input type="text" name="faultName" disabled> 
                    </div>
                    <div></div>
                    <div></div>
                    <div class="button-container">
                        <input type="button" id="faultcode-edit-btn" value="수정">
                        <input type="button" id="faultcode-delete-btn" value="삭제">
                    </div>
                </div>
            </div>

            <!-- Edit Modal -->
            <div id="faultcode-edit">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>
                    <div class="modal-title">고장코드 수정</div>
                    <div id="required-text"></div>
                    <input type="hidden" name="no">
                    <div class="modal-cont">
                        <label for="">고장유형명</label>
                        <input type="text" name="faultName"> 
                    </div>
                    <div></div>
                    <div></div>
                    <div class="button-container">
                        <input type="button" id="faultcode-save-btn" value="저장">
                    </div>
                </div>
            </div>

        </div>
    </div>
</body>
</html>
