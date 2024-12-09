<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>EVERY READY PLAN</title>
            <link rel="stylesheet" href="/css/common/index.css">
            <link rel="stylesheet" href="/css/finance/sale/list.css">
            <link rel="stylesheet" href="/css/finance/sale/write.css">
            <link rel="stylesheet" href="/css/finance/sale/detail.css">
            <link rel="stylesheet" href="/css/finance/sale/edit.css">
            <link rel="stylesheet" href="/css/common/bottom.css">
            <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
            <script defer src="/js/finance/sale/list.js"></script>
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
                                    <div class="menu-name">매출 관리</div>
                                    <div>
                                        <form action="/finance/sale/list" class="top-title-area-form">
                                            <label for="select">카테고리</label>
                                            <div class="search-bar">
                                                <select name="area" id="">
                                                    <option value="1" ${area == '1' ? 'selected' : ''}>거래처코드</option>
                                                    <option value="2" ${area == '2' ? 'selected' : ''}>거래항목코드</option>
                                                    <option value="3" ${area == '3' ? 'selected' : ''}>계좌코드</option>
                                                    <option value="4" ${area == '4' ? 'selected' : ''}>거래일자</option>
                                                </select>
                                            </div>
    
                                            <div class="search-bar">
                                                <label for="">내용검색</label>
                                                <input type="search" name="searchValue" id="longbar" value="${searchValue}">
                                            </div>
                                            <div class="search-bar">
                                                <button class="crud-button-white" type="submit">검색</button>
                                            </div>
                                        </form>
                                    </div>
                                    </div>
    

                                    <!-- List Area -->
                                    <div class="middle-content-area">
                                        <table class="list-area">
                                            <thead>
                                                <tr>
                                                    <th><input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)"></th>
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
                                                <c:forEach items="${saleVoList}" var="saleVo">
                                                    <tr id="sale-list" onclick="saleDetail('${saleVo.no}');">
                                                        <td><input type="checkbox" name="saleIds" value="${saleVo.no}" onclick="event.stopPropagation();"></td>
                                                        <td>${saleVo.no}</td>
                                                        <td>${saleVo.partnerCode}</td>
                                                        <td>${saleVo.transCode}</td>
                                                        <td>${saleVo.accountCode}</td>
                                                        <td>${saleVo.supplyAmount}</td>
                                                        <td>${saleVo.taxAmount}</td>
                                                        <td>${saleVo.transDate}</td>
                                                        <td>${saleVo.comments}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>


                                    <!-- Bottom Area -->
                                    <div class="bottom-content-area">
                                        <div>
                                            <button type="button" onclick="deleteSelectedSales()" class="crud-button-white">삭제</button>
                                        </div>
                                        <div>
                                            <div class="pagination">
                                                <c:if test="${pageVo.currentPage > 1}">
                                                    <a href="?pno=${pageVo.currentPage - 1}" class="page-button previous">&laquo;</a>
                                                </c:if>
                                                <c:forEach begin="${pageVo.startPage}" end="${pageVo.endPage}" var="i">
                                                    <a href="?pno=${i}" class="page-button ${i == pageVo.currentPage ? 'active' : ''}">${i}</a>
                                                </c:forEach>
                                                <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                                    <a href="?pno=${pageVo.currentPage + 1}" class="page-button next">&raquo;</a>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="crud-button-white" id="sale-write-btn"
                                                onclick="saleWrite();">등록</button>
                                        </div>
                                    </div>
                                </div>

                                <!-- Write Modal -->
                                <div id="sale-write">
                                    <form action="/finance/sale/write" method="post"
                                        onsubmit="return confirm('등록하시겠습니까?');">
                                        <div class="write-content">
                                            <span class="write-close" onclick="saleWriteclose();">&times;</span>
                                            <div class="modal-title">매출 등록</div>
                                            <div id="required-text"> * 는 필수 입력 사항입니다.</div>

                                            <div class="title-text"></div>

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

                                            <div class="modal-cont"></div>
                                            <div></div>
                                            <div class="button-container"><input type="submit" value="등록"></div>
                                        </div>
                                    </form>
                                </div>


                                <!-- Detail Modal -->
                                <div id="sale-detail">
                                    <form action='/finance/sale/delete' method="delete"
                                        onsubmit="return confirm('삭제하시겠습니까?')">
                                        <div class="detail-content">
                                            <span class="detail-close" onclick="saleDetailClose();">&times;</span>

                                            <div class="modal-title">매출 상세</div>
                                            <div id="required-text"></div>

                                            <div class="title-text"></div>

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
                                            <div class="button-container">
                                                <div><input id="sale-edit-btn" type="button" value="수정"></div>
                                                <div><input id="sale-delete-btn" type="button" value="삭제"></div>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <!-- Edit Modal -->
                                <div id="sale-edit">
                                    <form id="sale-edit-form" action="/finance/sale/edit" method="post"
                                        onsubmit="return confirm('저장하시겠습니까?');">
                                        <div class="edit-content">
                                            <span class="edit-close" onclick="saleEditClose();">&times;</span>

                                            <div class="modal-title">매출 수정</div>
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