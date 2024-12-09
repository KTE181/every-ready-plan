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
    <link rel="stylesheet" href="/css/product/productcnt.css">
    <link rel="stylesheet" href="/css/product/bottompagecnt.css">
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
                        <div class="menu-name">재고 현황 조회</div>
                        <div>
                            <form action="" class="top-title-area-form">
                                <div class="search-bar">
                                    <form action="/qa/productcnt/list">
                                        <label>상품이름 &nbsp;&nbsp; <input type="text" id = "longbar" name="searchValue" value="${searchValue}" placeholder="검색할 상품이름을 입력하세요"></label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label>품목코드 &nbsp;&nbsp;  <input type="text" id = "longbar" name="searchValueCode" value="${searchValueCode}" placeholder="검색할 품목코드를 입력하세요"></label>
                                        <div class="search-bar"><button id="searchButton">검색</button></div>
                                    </form>
                                </div>
                            </form>
                        </div>
                    </div>

                
                <div class = "middle-content-area">

                    <table class = "listcnt-area">
                            <thead>
                                <tr>
                                    <th>품목코드</th>
                                    <th>상품명</th>
                                    <th>가격</th>
                                    <th>재고</th>
                                    <th>불량품개수</th>
                                </tr>
                            </thead>
    
                            <tbody>
                                <c:forEach items = "${productcntVo}" var = "product">
                                    <tr>
                                        <td>${product.itemCode}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                        <td>${product.totalCount}</td>
                                        <td>${product.defectiveCnt}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                     
                           

                </div>

                

                <div class="bottom-content-area">
                    <div>
                        <div class="pagination">
                            <c:if test="${pageVo.currentPage > 1}">
                                <a href="/qa/productcnt/list?pno=${pageVo.currentPage - 1}" class="page-button" data-page="${pageVo.currentPage - 1}">이전</a>
                            </c:if>
                        
                            <c:forEach begin="${pageVo.startPage}" end="${pageVo.endPage}" var="page">
                                <a href="/qa/productcnt/list?pno=${page}" class="page-link ${pageVo.currentPage == page ? 'active' : ''}" id = page-button-middle data-page="${page}">${page}</a>
                            </c:forEach>
                        
                            <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                                <a href="/qa/productcnt/list?pno=${pageVo.currentPage + 1}" class="page-button" data-page="${pageVo.currentPage + 1}">다음</a>
                            </c:if>
                        </div>
                    </div>
                </div> 

        </div>
    </div>
</body>
</html>
