<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>EVERY READY PLAN</title>
            <link rel="stylesheet" href="/css/common/index.css">
            <link rel="stylesheet" href="/css/finance/account/list.css">
            <link rel="stylesheet" href="/css/finance/account/write.css">
            <link rel="stylesheet" href="/css/finance/account/detail.css">
            <link rel="stylesheet" href="/css/finance/account/edit.css">
            <link rel="stylesheet" href="/css/common/bottom.css">
            <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
            <script defer src="/js/finance/account/list.js"></script>
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
                                    <div class="menu-name">은행계좌관리</div>
                                    <div>
                                    <form action="/finance/account/list" class="top-title-area-form">
                                        <label for="select">카테고리</label>
                                        <div class="search-bar">
                                            <select name="area">
                                                <option value="1">은행코드</option>
                                                <option value="2">은행명</option>
                                                <option value="3">계좌명</option>
                                                <option value="4">계좌번호</option>
                                            </select>
                                        </div>

                                        <div class="search-bar">
                                        <label for="">내용검색</label>
                                            <input type="search" id="longbar">
                                        </div>
                                        <div class="search-bar">
                                            <button class="crud-button-white">검색</button>
                                        </div>
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
                                                <th>은행코드</th>
                                                <th>은행명</th>
                                                <th>계좌번호</th>
                                                <th>계좌별명</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${accountVoList}" var="accountVo">
                                                <tr id="account-list" onclick="accountDetail('${accountVo.no}');">
                                                    <td><input type="checkbox" name=""></td>
                                                    <td>${accountVo.no}</td>
                                                    <td>${accountVo.bankCode}</td>
                                                    <td>${accountVo.bankName}</td>
                                                    <td>${accountVo.accountNo}</td>
                                                    <td>${accountVo.accountName}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            
                                finance/account
                            <!-- Bottom Area -->
                            <div class="bottom-content-area">
                                <div><button class="crud-button-white" onclick="accountDeleteMultiple();">삭제</button></div>
                                <div>
                                    <div>
                                        <div class="pagination">
                                            <div class="pagination">
                                                <c:if test="${pvo.startPage != 1}"> 
                                                    <a href="/finance/account/list?pno=${pvo.startPage-1}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button previous">&laquo;</a>
                                                </c:if>
                                                <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                                    <a href="/qa/asreq/list?&pno=${i}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button <c:if test='${i == pvo.currentPage}'>active</c:if>">${i}</a>
                                                </c:forEach>
                                                <c:if test="${pvo.endPage != pvo.maxPage}"> 
                                                    <a href="/qa/asreq/list?pno=${pvo.endPage+1}&area=${area}&searchType=${searchType}&searchValue=${searchValue}" class="page-button next">&raquo;</a>
                                                </c:if>  
                                         </div>
                                    </div>
                                </div>
                                <div>
                                    <button class="crud-button-white" id="account-write-btn"
                                        onclick="accountWrite();">등록</button>
                                </div>
                            </div>
                        </div>

                            <!-- Write Modal -->
                            <div id="account-write">
                                <form action="/finance/account/write" method="post" onsubmit="return confirm('등록하시겠습니까?');">
                                    <div class="write-content">
                                        <span class="write-close" onclick="accountWriteclose();">&times;</span>
                                        <div class="modal-title">회사 계좌 등록</div>
                                        <div id="required-text"> * 는 필수 입력 사항입니다.</div>

                                        <div class="title-text"></div>

                                        <!-- <div class="modal-cont">
                                            <label for="">은행코드</label>
                                            <div>
                                                <input type="text" name="bankCode">
                                            </div>
                                        </div> -->
                                        <!-- <div class="modal-cont">
                                            <label for="">은행명</label>
                                            <div>
                                                <input type="text" name="bankName" disabled>
                                            </div>
                                        </div> -->

                                        <div class="modal-cont">
                                            <label>은행명</label>
                                            <select name="bankCode">
                                                <option value="1">한국</option>
                                                <option value="2">산업</option>
                                                <option value="3">기업</option>
                                                <option value="4">국민</option>
                                                <option value="5">외환</option>
                                                <option value="7">수협</option>
                                                <option value="11">농협</option>
                                                <option value="20">우리</option>
                                                <option value="31">대구</option>
                                                <option value="32">부산</option>
                                                <option value="48">신협</option>
                                            </select>
                                        </div>

                                        <div></div>

                                        <div class="modal-cont">
                                            <label for="">계좌번호</label>
                                            <div>
                                                <input type="text" name="accountNo">
                                            </div>
                                        </div>
                                        <div class="modal-cont">
                                            <label for="">계좌별명</label>
                                            <div>
                                                <input type="text" name="accountName">
                                            </div>
                                        </div>

                                        <div class="modal-cont"></div>
                                        <div></div>
                                        <div class="button-container"><input type="submit" value="등록"></div>
                                    </div>
                                </form>
                            </div>


                            <!-- Detail Modal -->
                            <div id="account-detail">
                                <form action='/finance/account/delete' method="delete"
                                    onsubmit="return confirm('삭제하시겠습니까?')">
                                    <div class="detail-content">
                                        <span class="detail-close" onclick="accountDetailClose();">&times;</span>

                                        <div class="modal-title">회사 계좌 상세</div>
                                        <div id="required-text"></div>

                                        <div class="title-text"></div>

                                        <div class="modal-cont" style="display:none;" >
                                            <label for="">번호</label>
                                            <div>
                                                <input type="text" name="no" style="display:none;">
                                            </div>
                                        </div>

                                        <div class="modal-cont">
                                            <label for="">은행코드</label>
                                            <div>
                                                <input type="text" name="bankCode" disabled>
                                            </div>
                                        </div>

                                        <div class="modal-cont">
                                            <label for="">은행명</label>
                                            <div>
                                                <input type="text" name="bankName" disabled>
                                            </div>
                                        </div>

                                        <div class="modal-cont">
                                            <label for="">계좌번호</label>
                                            <div>
                                                <input type="text" name="accountNo" disabled>
                                            </div>
                                        </div>

                                        <div class="modal-cont">
                                            <label for="">계좌별명</label>
                                            <div>
                                                <input type="text" name="accountName" disabled>
                                            </div>
                                        </div>
                                        <div></div>
                                        <div class="button-container">
                                            <div><input id="account-edit-btn" type="button" value="수정"></div>
                                            <div><input id="account-delete-btn" type="button" value="삭제"></div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <!-- Edit Modal -->
                            <div id="account-edit">
                                <form id="account-edit-form" action="/finance/account/edit" method="post"
                                    onsubmit="return confirm('저장하시겠습니까?');">
                                    <div class="edit-content">
                                        <span class="edit-close" onclick="accountEditClose();">&times;</span>

                                        <div class="modal-title">은행 수정</div>
                                        <div id="required-text">* 는 필수입력사항입니다.</div>

                                        <div class="modal-cont" style="display:none;">
                                            <label for="">번호</label>
                                            <div>
                                                <input type="text" name="no" >
                                            </div>
                                        </div>

                                        <!-- <div class="modal-cont">
                                            <label>은행명</label>
                                            <select name="bankCode">
                                                <option value="1">한국</option>
                                                <option value="2">산업</option>
                                                <option value="3">기업</option>
                                                <option value="4">국민</option>
                                                <option value="5">외환</option>
                                                <option value="7">수협</option>
                                                <option value="11">농협</option>
                                                <option value="20">우리</option>
                                                <option value="31">대구</option>
                                                <option value="32">부산</option>
                                                <option value="48">신협</option>
                                            </select>
                                        </div> -->

                                        <div class="modal-cont">
                                            <label for="">은행코드</label>
                                            <div>
                                                <input type="text" name="bankCode" >
                                            </div>
                                        </div>

                                        <div class="modal-cont">
                                            <label for="">은행명</label>
                                            <div>
                                                <input type="text" name="bankName" >
                                            </div>
                                        </div>

                                        <div class="modal-cont">
                                            <label for="">계좌번호</label>
                                            <div>
                                                <input type="text" name="accountNo" >
                                            </div>
                                        </div>

                                        <div class="modal-cont">
                                            <label for="">계좌별명</label>
                                            <div>
                                                <input type="text" name="accountName" >
                                            </div>
                                        </div>

                                        <div></div>
                                        <div class="button-container"><input type="submit" value="저장"></div>
                                    </div>
                                </form>
                            </div>

                    </div>

            </div>
            </div>
        </body>

        </html>