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
    <link rel="stylesheet" href="/css/qa/aswork/list.css">
    <link rel="stylesheet" href="/css/qa/aswork/modal.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/qa/aswork/list.js"></script>
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
                    <div class="menu-name">AS작업관리</div>
                    <div>
                        <form action="/qa/aswork/list" class="top-title-area-form">
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

                            <label for="select"></label>
                            <div class="search-bar">
                                <select name="status">
                                    <option value="">AS진행상태 전체</option>
                                    <c:forEach items="${statusVoList}" var="vo">
                                        <option value="${vo.no}" <c:if test='${status == vo.no}'>selected</c:if>>${vo.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <label for="select"></label>
                            <div class="search-bar">
                                <select name="type">
                                    <option value="">고장유형 전체</option>
                                    <c:forEach items="${typeVoList}" var="vo">
                                        <option value="${vo.no}" <c:if test='${type == vo.no}'>selected</c:if>>${vo.faultName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <label for="검색어"></label>
                            <div class="search-bar">
                                <select name="searchType">
                                    <option value="title" <c:if test='${searchType == "title"}'>selected</c:if>>AS요청제목</option>
                                    <option value="customerName" <c:if test='${searchType == "customerName"}'>selected</c:if>>고객명</option>
                                    <option value="empName" <c:if test='${searchType == "customerName"}'>selected</c:if>>AS담당자</option>
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
                                <th>AS진행상태</th>
                                <th>AS담당자</th>
                                <th>수리일자</th>
                                <th>고장유형</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${asworkVoList}" var="asworkVo">
                                <tr id="aswork-list" onclick="asworkDetail('${asworkVo.no}');">
                                    <td onclick="event.stopPropagation();"><input type="checkbox" name="listCheckbox"></td>
                                    <td>${asworkVo.no}</td>
                                    <td>${asworkVo.serialNumber}</td>
                                    <td>${asworkVo.productName}</td>
                                    <td>${asworkVo.issueTitle}</td>
                                    <td>${asworkVo.customerName}</td>
                                    <td>${asworkVo.customerArea}</td>
                                    <td>${asworkVo.statusName}</td>
                                    <td>${asworkVo.empName}</td>
                                    <td>${asworkVo.repairDate}</td>
                                    <td>${asworkVo.faultName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white" onclick="asworkDeleteMultiple();">삭제</button></div>
                    <div>
                        <div class="pagination">
                            <c:if test="${pvo.startPage != 1}"> 
                                <a href="/qa/aswork/list?pno=${pvo.startPage-1}&area=${area}&status=${status}&type=${type}&searchType=${searchType}&searchValue=${searchValue}" class="page-button previous">&laquo;</a>
                            </c:if>
                            <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                <a href="/qa/aswork/list?&pno=${i}&area=${area}&status=${status}&type=${type}&searchType=${searchType}&searchValue=${searchValue}" class="page-button <c:if test='${i == pvo.currentPage}'>active</c:if>">${i}</a>
                            </c:forEach>
                            <c:if test="${pvo.endPage != pvo.maxPage}"> 
                                <a href="/qa/aswork/list?pno=${pvo.endPage+1}&area=${area}&status=${status}&type=${type}&searchType=${searchType}&searchValue=${searchValue}" class="page-button next">&raquo;</a>
                            </c:if>   
                         </div>
                    </div>
                    <div></div>
                </div>
            </div>

            <!-- Detail Modal -->
            <div id="aswork-detail">
                <form action='/qa/aswork/delete' method="post" onsubmit="return confirm('삭제하시겠습니까?');">
                    <div class="detail-content">
                        <span class="detail-close" onclick="asworkDetailClose();">&times;</span>
                        
                        <div class="modal-title">AS 작업 상세</div>
                        <div id="required-text"></div>

                        <div class="title-text">AS요청정보</div>
                        <input type="hidden" name="no">
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

                        <div class="title-text">AS작업정보</div>
                        <div class="modal-cont">
                            <label>AS진행상태</label>
                            <select name="statusCode" disabled>
                                <option value="2">접수</option>
                                <option value="3">처리중</option>
                                <option value="4">처리완료</option>
                            </select>
                        </div>

                        <div class="modal-cont">
                            <label>AS담당자</label>
                            <select name="empNo" disabled>
                                <option value="1">홍길동</option>
                                <option value="2">박철수</option>
                                <option value="3">김아무개</option>
                            </select>
                        </div>

                        <div class="modal-cont">
                            <label for="">수리일자</label>
                            <input type="date" name="repairDate" disabled>
                        </div>

                        <div class="modal-cont">
                            <label>고장유형</label>
                            <select name="faultCode" disabled>
                                <option value="1">침수</option>
                                <option value="2">파손</option>
                                <option value="3">전기누수</option>
                            </select>
                        </div>

                        <div class="modal-cont long-textbox">
                            <label>수리내용</label>
                            <textarea name="repairDetalis" disabled></textarea>
                        </div>

                        <div></div>

                        <div class="button-container">
                            <div><input id="aswork-edit-bnt" type="button" value="수정"></div>
                            <div><input type="submit" value="삭제"></div>
                        </div>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Edit Modal -->
            <div id="aswork-edit">
                <form id="aswork-edit-form" action='/qa/aswork/edit' method="post" onsubmit="return confirm('저장하시겠습니까?');" >
                    <div class="edit-content">

                        <span class="edit-close" onclick="asworkEditClose();">&times;</span>
                        
                        <div class="modal-title">AS 작업 수정</div>
                        <div id="required-text">* 는 필수입력사항입니다.</div>

                        <div class="title-text">AS요청정보</div>
                        <input type="hidden" name="no">
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

                        <div class="title-text">AS작업정보</div>
                        <div class="modal-cont">
                            <label>AS진행상태</label>
                            <select name="statusCode">
                                <option value="2">접수</option>
                                <option value="3">처리중</option>
                                <option value="4">처리완료</option>
                            </select>
                        </div>

                        <div class="modal-cont">
                            <label>AS담당자</label>
                            <select name="empNo">
                                <option>-- 선택 --</option>
                                <option value="1">홍길동</option>
                                <option value="2">박철수</option>
                                <option value="3">김아무개</option>
                            </select>
                        </div>

                        <div class="modal-cont">
                            <label for="">수리일자</label>
                            <input type="date" name="repairDate">
                        </div>

                        <div class="modal-cont">
                            <label>고장유형</label>
                            <select name="faultCode">
                                <option>-- 선택 --</option>
                                <option value="1">침수</option>
                                <option value="2">파손</option>
                                <option value="3">전기누수</option>
                            </select>
                        </div>

                        <div class="modal-cont long-textbox">
                            <label>수리내용</label>
                            <textarea name="repairDetalis"></textarea>
                        </div>

                        <div></div>

                        <div class="button-container">
                            <div class="button-container"><input type="submit" value="저장"></div>
                        </div>
                        </div>

                    </div>
                </form>
            </div>

        </div>
    </div>
</body>
</html>
