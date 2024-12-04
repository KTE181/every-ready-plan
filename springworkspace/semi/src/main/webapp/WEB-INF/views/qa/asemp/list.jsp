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
                                <tr id="asemp-list" onclick="asempDetail('${asempVo.no}');">
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
                    <div><button class="crud-button-white" id="asemp-enroll-btn" onclick="asempEnroll();">등록</button></div>
                </div>
            </div>

            <!-- Enroll Modal -->
            <div id="asemp-enroll">
                <form action="/qa/asemp/enroll" method="post" onsubmit="return confirm('등록하시겠습니까?');">
                    <div class="modal-content">
                        <span class="enroll-close">&times;</span>
            
                        <div class="modal-title">AS 담당자 등록</div>
                        <div id="required-text">* 는 필수입력사항입니다.</div>
            
                        <div class="title-text">AS담당자정보</div>
            
                        <div class="modal-cont">
                            <label for="">사번</label>
                            <div>
                                <input type="text" name="no"> 
                                <input type="button" class="emp-search-button" value="사원검색">
                            </div>
                        </div>
                        <div class="modal-cont">
                            <label for="">사원명</label>
                            <input type="text" name="empName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">연락처</label>
                            <input type="text" name="phone" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">소속부서</label>
                            <input type="text" name="deptName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">직급</label>
                            <input type="text" name="positionName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label>AS담당지역</label>
                            <select name="area">
                                <option value="서울">서울</option>
                                <option value="인천">인천</option>
                                <option value="경기">경기</option>
                                <option value="부산">부산</option>
                                <option value="제주도">제주도</option>
                            </select>
                        </div>
                        <div></div>
                        <div class="button-container"><input type="submit" value="등록" ></div>
        
                    </div>
                </form>
            </div>

            <!-- Detail Modal -->
            <div id="asemp-detail">
                    <div class="detail-content">
                        <span class="detail-close">&times;</span>
            
                        <div class="modal-title">AS 담당자 상세</div>
                        <div id="required-text"></div>
            
                        <div class="title-text">AS담당자정보</div>
            
                        <div class="modal-cont">
                            <label for="">사번</label>
                            <div>
                                <input type="text" name="no" readonly> 
                            </div>
                        </div>
                        <div class="modal-cont">
                            <label for="">사원명</label>
                            <input type="text" name="empName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">연락처</label>
                            <input type="text" name="phone" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">소속부서</label>
                            <input type="text" name="deptName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">직급</label>
                            <input type="text" name="positionName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label>AS담당지역</label>
                            <select name="area" disabled>
                                <option value="서울">서울</option>
                                <option value="인천">인천</option>
                                <option value="경기">경기</option>
                                <option value="부산">부산</option>
                                <option value="제주도">제주도</option>
                            </select>
                        </div>
                        <div></div>
                        <div class="button-container">
                            <div><input id="asemp-edit-bnt" type="button" value="수정"></div>
                            <div><input id="asemp-delete-bnt" type="button" value="삭제"></div>
                        </div>
                    </div>
            </div>

            <!-- Edit Modal -->
            <div id="asemp-edit">
                <form action="/qa/asemp/edit" method="post" onsubmit="return confirm('저장하시겠습니까?');">
                    <div class="edit-content">
                        <span class="edit-close">&times;</span>
            
                        <div class="modal-title">AS 담당자 수정</div>
                        <div id="required-text"></div>
            
                        <div class="title-text">AS담당자정보</div>
            
                        <div class="modal-cont">
                            <label for="">사번</label>
                            <input type="text" name="no" readonly> 
                        </div>
                        <div class="modal-cont">
                            <label for="">사원명</label>
                            <input type="text" name="empName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">연락처</label>
                            <input type="text" name="phone" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">소속부서</label>
                            <input type="text" name="deptName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label for="">직급</label>
                            <input type="text" name="positionName" disabled>
                        </div>
                        <div class="modal-cont">
                            <label>AS담당지역</label>
                            <select name="area">
                                <option value="서울">서울</option>
                                <option value="인천">인천</option>
                                <option value="경기">경기</option>
                                <option value="부산">부산</option>
                                <option value="제주도">제주도</option>
                            </select>
                        </div>
                        <div></div>

                        <div class="button-container"><input type="submit" value="저장" ></div>
                    </div>
                </form>
            </div>

            <!-- SearchEmp Modal -->
            <div id="search-emp">
                <div class="modal-content">
                    <span class="modal-close">&times;</span>
        
                    <div class="modal-title">사원검색</div>
                    <div></div>

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
                        </tbody>
                    </table>

                    <div class="button-container"><input type="button" id="emp-select-btn" value="선택"></div>

                </div>
            </div>


        </div>
    </div>
</body>
</html>
