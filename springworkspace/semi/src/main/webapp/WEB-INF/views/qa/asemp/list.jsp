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
    <link rel="stylesheet" href="/css/qa/asemp/list.css">
    <link rel="stylesheet" href="/css/qa/asemp/modal.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/qa/asemp/list.js"></script>
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
                    <div class="menu-name">AS담당자관리</div>
                    <div>
                        <form action="/qa/asemp/list" class="top-title-area-form">
                            <label for="select"></label>
                            <div class="search-bar">
                                <select name="area">
                                    <option value="">담당지역 전체</option>
                                    <c:forEach items="${areaList}" var="areaName">
                                        <option value="${areaName}" <c:if test='${area == areaName}'>selected</c:if>>${areaName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <label for="검색어"></label>
                            <div class="search-bar">
                                <select name="searchType" onchange="handleSearchType(this)">
                                    <option value="empNo" <c:if test='${searchType == "empNo"}'>selected</c:if>>사번</option>
                                    <option value="empName" <c:if test='${searchType == "empName"}'>selected</c:if>>AS담당자명</option>
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
                                <th>사번</th>
                                <th>AS담당자명</th>
                                <th>담당지역</th>
                                <th>연락처</th>
                                <th>부서</th>
                                <th>직급</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${asempVoList}" var="asempVo">
                                <tr id="asemp-list" onclick="loadDetailModal('${asempVo.no}');">
                                    <td onclick="event.stopPropagation();"><input type="checkbox" name="listCheckbox"></td>
                                    <td>${asempVo.no}</td>
                                    <td>${asempVo.empName}</td>
                                    <td>${asempVo.area}</td>
                                    <td>${asempVo.phone}</td>
                                    <td>${asempVo.deptName}</td>
                                    <td>${asempVo.positionName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white" onclick="asempDeleteMultiple();">삭제</button></div>
                    <div>
                        <div class="pagination">
                            <c:if test="${pvo.startPage != 1}"> 
                                <a href="/qa/asemp/list?pno=${pvo.startPage-1}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button previous">&laquo;</a>
                            </c:if>
                            <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                <a href="/qa/asemp/list?&pno=${i}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button <c:if test='${i == pvo.currentPage}'>active</c:if>">${i}</a>
                            </c:forEach>
                            <c:if test="${pvo.endPage != pvo.maxPage}"> 
                                <a href="/qa/asemp/list?pno=${pvo.endPage+1}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button next">&raquo;</a>
                            </c:if>   
                         </div>
                    </div>
                    <div><button class="crud-button-white" id="asemp-enroll-btn" onclick="loadWriteModal();">등록</button></div>
                </div>
            </div>

            <!-- AS담당자 모달 -->
            <div id="asemp-modal">
                <form id="asemp-form">
                    <div class="modal-content">
                        <span class="modal-close">&times;</span>
            
                        <div class="modal-title"></div>
                        <div class="required-text">* 는 필수입력사항입니다.</div>
                        <div class="title-text">AS담당자정보</div>
            
                        <div class="modal-cont">
                            <div><span class="required-text">*</span>사번</div>
                            <div>
                                <input type="text" name="no" disabled> 
                                <input type="button" id="search-button" value="사원검색" onclick="empList();">
                            </div>
                        </div>
                        <div class="modal-cont">
                            <div>사원명</div>
                            <input type="text" name="empName" disabled>
                        </div>
                        <div class="modal-cont">
                            <div>연락처</div>
                            <input type="text" name="phone" disabled>
                        </div>
                        <div class="modal-cont">
                            <div>소속부서</div>
                            <input type="text" name="deptName" disabled>
                        </div>
                        <div class="modal-cont">
                            <div>직급</div>
                            <input type="text" name="positionName" disabled>
                        </div>
                        <div class="modal-cont">
                            <div><span class="required-text" id="required-text-area">*</span>AS담당지역</div>
                            <select name="area">
                                <option value="">-- 선택 --</option>
                                <c:forEach items="${areaList}" var="areaName">
                                    <option value="${areaName}">${areaName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div></div>

                        <div class="button-container"></div>
        
                    </div>
                </form>
            </div>

            <!-- 사원검색 모달 -->
            <div id="emp-modal">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>
                    <div class="modal-title">사원검색</div>
                    <div><span class="required-text">* 이미 AS담당자로 등록된 사원은 조회되지 않습니다.</span></div>

                    <div id="emp-modal-search-area">
                        <form id="emp-search-form">
                        <select name="dept">
                            <option value="">소속부서 전체</option>
                            <c:forEach items="${deptVoList}" var="vo">
                                <option value="${vo.no}">${vo.name}</option>
                            </c:forEach>
                        </select>
                        <select name="empSearchType">
                            <option value="empName">사원명</option>
                            <option value="empNo">사원번호</option>
                        </select>
                        <input type="search" name="empSearchValue">
                        <input type="button" onclick="empData()" value="검색">
                        </form>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <th>선택</th>
                                <th>사번</th>
                                <th>사원명</th>
                                <th>연락처</th>
                                <th>소속부서</th>
                                <th>직급</th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                    <div class="page-area"></div>
                    <div class="button-container"><input type="button" onclick="selectEmp();" value="선택"></div>
                </div>
            </div>


        </div>
    </div>
</body>
</html>
