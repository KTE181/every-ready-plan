<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>EVERY READY PLAN</title>
            <link rel="stylesheet" href="/css/common/index.css">
            <link rel="stylesheet" href="/css/finance/expense/list.css">
            <link rel="stylesheet" href="/css/finance/expense/write.css">
            <link rel="stylesheet" href="/css/finance/expense/detail.css">
            <link rel="stylesheet" href="/css/finance/expense/edit.css">
            <link rel="stylesheet" href="/css/common/bottom.css">
            <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
            <script defer src="/js/finance/expense/list.js"></script>
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
                                <div class="menu-name">경비관리</div>
                                <div>
                                    <form action="/finance/expense/list" class="top-title-area-form">
                                        <label for="select">카테고리</label>
                                        <div class="search-bar">
                                            <select name="" id="">
                                                <option value="1"></option>
                                                <option value="2"></option>
                                                <option value="3"></option>
                                                <option value="4"></option>
                                            </select>
                                        </div>

                                        <div class="search-bar"><label for="">내용검색</label>
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
                                                <th>순번</th>
                                                <th>거래처코드</th>
                                                <th>거래항목코드</th>
                                                <th>계좌코드</th>
                                                <th>공급가액</th>
                                                <th>부가세</th>
                                                <th>거래일자</th>
                                                <th>비고</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${expenseVoList}" var="expenseVo">
                                                <tr id="expense-list" onclick="expenseDetail('${expenseVo.no}');">
                                                    <td><input type="checkbox" name=""></td>
                                                    <td>${expenseVo.no}</td>
                                                    <td>${expenseVo.partnerCode}</td>
                                                    <td>${expenseVo.transCode}</td>
                                                    <td>${expenseVo.accountCode}</td>
                                                    <td>${expenseVo.supplyAmount}</td>
                                                    <td>${expenseVo.taxAmount}</td>
                                                    <td>${expenseVo.transDate}</td>
                                                    <td>${expenseVo.comments}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            

                            <!-- Bottom Area -->
                            <div class="bottom-content-area">
                                <div>
                                    <button class="crud-button-white">삭제</button>
                                </div>
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
                                <div>
                                    <button class="crud-button-white" id="expense-write-btn"
                                        onclick="expenseWrite();">등록</button>
                                </div>
                            </div>
                        </div>
                        <!-- Write Modal -->
                        <div id="expense-write">
                            <form action="/finance/expense/write" method="post" onsubmit="return confirm('등록하시겠습니까?');">
                                <div class="write-content">
                                    <span class="write-close" onclick="expenseWriteclose();">&times;</span>
                                    <div class="modal-title">경비 등록</div>
                                    <div id="required-text"> * 는 필수 입력 사항입니다.</div>

                                    <div class="title-text"></div>

                                    <div class="modal-cont" style="display:none;" >
                                        <label for="">번호</label>
                                        <div>
                                            <input type="text" name="no" style="display:none;">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래처코드</label>
                                        <div>
                                            <input type="text" name="partnerCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래항목코드</label>
                                        <div>
                                            <input type="text" name="transCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">계좌코드</label>
                                        <div>
                                            <input type="text" name="accountCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래일자</label>
                                        <div>
                                            <input type="text" name="transDate">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">공급가액</label>
                                        <div>
                                            <input type="text" name="supplyAmount">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">부가세</label>
                                        <div>
                                            <input type="text" name="taxAmount">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">증빙사진</label>
                                        <div>
                                            <input type="text" name="attachment">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">비고</label>
                                        <div>
                                            <input type="text" name="comments">
                                        </div>
                                    </div>

                                    <div class="modal-cont"></div>
                                    <div></div>
                                    <div class="button-container"><input type="submit" value="등록"></div>
                                </div>
                            </form>
                        </div>
                        <!-- Detail Modal -->
                        <div id="expense-detail">
                            <form action='/finance/expense/delete' method="post" onsubmit="return confirm('삭제하시겠습니까?')">
                                <div class="detail-content">
                                    <span class="detail-close" onclick="expenseDetailClose();">&times;</span>

                                    <div class="modal-title">경비 상세</div>
                                    <div id="required-text"></div>

                                    <div class="title-text"></div>

                                    <div class="modal-cont" style="display:none;" >
                                        <label for="">번호</label>
                                        <div>
                                            <input type="text" name="no" style="display:none;">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래처코드</label>
                                        <div>
                                            <input type="text" name="partnerCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래항목코드</label>
                                        <div>
                                            <input type="text" name="transCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">계좌코드</label>
                                        <div>
                                            <input type="text" name="accountCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래일자</label>
                                        <div>
                                            <input type="text" name="transDate">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">공급가액</label>
                                        <div>
                                            <input type="text" name="supplyAmount">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">부가세</label>
                                        <div>
                                            <input type="text" name="taxAmount">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">증빙사진</label>
                                        <div>
                                            <input type="text" name="attachment">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">비고</label>
                                        <div>
                                            <input type="text" name="comments">
                                        </div>
                                    </div>

                                    <div></div>
                                    <div class="button-container">
                                        <div><input id="expense-edit-btn" type="button" value="수정"></div>
                                        <div><input id="expense-delete-btn" type="button" value="삭제"></div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <!-- Edit Modal -->
                        <div id="expense-edit">
                            <form id="expense-edit-form" action="/finance/expense/edit" method="post" onsubmit="return confirm('저장하시겠습니까?');">
                                <div class="edit-content">
                                    <span class="edit-close" onclick="expenseEditClose();">&times;</span>

                                    <div class="modal-title">경비 수정</div>
                                    <div id="required-text">* 는 필수입력사항입니다.</div>

                                    <div class="modal-cont" style="display:none;">
                                        <label for="">번호</label>
                                        <div>
                                            <input type="text" name="no" style="display:none;">
                                        </div>
                                    </div>
                                    
                                    <div class="modal-cont">
                                        <label for="">거래처코드</label>
                                        <div>
                                            <input type="text" name="partnerCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래항목코드</label>
                                        <div>
                                            <input type="text" name="transCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">계좌코드</label>
                                        <div>
                                            <input type="text" name="accountCode">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">거래일자</label>
                                        <div>
                                            <input type="text" name="transDate">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">공급가액</label>
                                        <div>
                                            <input type="text" name="supplyAmount">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">부가세</label>
                                        <div>
                                            <input type="text" name="taxAmount">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">증빙사진</label>
                                        <div>
                                            <input type="text" name="attachment">
                                        </div>
                                    </div>
                                    <div class="modal-cont">
                                        <label for="">비고</label>
                                        <div>
                                            <input type="text" name="comments">
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