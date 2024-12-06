
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
    <link rel="stylesheet" href="/css/common/bottom.css">
    <link rel="stylesheet" href="/css/common/search.css">
    <link rel="stylesheet" href="/css/pv/pvsalary.css">
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
             <!-- Contents Area -->
             <div class="content-area">

                <!-- Search Area -->
                <div class="top-title-area">
                    <div class="menu-name">휴가 내역 조회</div>
                    <div>
                        <form action="/pv/salary/list" method="get" class="top-title-area-form">
                            <!-- <label for="">연월일</label> -->
                            <!-- <div class="search-bar"><input type="text" name="year" maxlength="4" placeholder="연도"></div>
                            <div class="search-bar"><input type="text" name="month" maxlength="2" placeholder="월"></div>
                            <div class="search-bar"><input type="text" name="day" maxlength="2" placeholder="일"></div> -->
                            
                            <label for="연월 date"></label>
                            <div class="search-bar"></div>
            
                            <label for="연월일 date"></label>
                            <div class="search-bar"></div>
            
                            <label for="select"></label>
                            <div class="search-bar">
                               
                            </div> 
            
                            <label for="검색어"></label>
                            <div class="search-bar">
                            
                            </div> 
                            <div class="search-bar"><input type="month" name="date"></div>
                            <div class="search-bar"><button class="button">검색</button></div>
                        </form>
                    </div>
                </div>

                <!-- List Area -->
                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                
                                
                                <th>지급연월</th>
                                <th>기본급</th>
                                <th>식대</th>
                                <th>통신비</th>
                                <th>국민연금</th>
                                <th>건강보험</th>
                                <th>고용보험</th>
                                <th>장기요양</th>
                                <th>소득세</th>
                                <th>지방소득세</th>
                                <th>실수령액</th>
                                
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${voList}" var="vo">
                                <tr>
                                    <td>${vo.payYearmonth}</td>
                                    <td>${vo.basic}</td>
                                    <td>${vo.mealAllowance}</td>
                                    <td>${vo.communicationCost}</td>
                                    <td>${vo.nationalPension}</td>
                                    <td>${vo.healthInsurance}</td>
                                    <td>${vo.employmentInsurance}</td>
                                    <td>${vo.longtermCareInsurance}</td>
                                    <td>${vo.incomeTax}</td>
                                    <td>${vo.localTaxes}</td>
                                    <td>${vo.netPayment}</td>
                                  
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div>
                    
                    </div>

                    <!-- <div>
                                <button class="ok-button">확인</button>
                            </div>
                    
                            <div>
                                <button class="cancle-button">취소</button>
                            </div> -->

                            <div>
                                <div class="pagination">
                                    <!-- 이전 페이지 버튼 -->
                                   

                                    <!-- 페이지 번호 버튼들 -->
                                    <div class="pagination">
                                        <c:if test="${pvo.startPage != 1}">
                                            <a href="/pv/salary/list?pno=${pvo.startPage-1}" class="page-button previous">&laquo;</a>
                                        </c:if>
                                        <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                            <a href="/pv/salary/list?pno=${i}" class="page-button">${i}</a>
                                        </c:forEach>
                                        <c:if test="${pvo.endPage != pvo.maxPage}">
                                            <a href="/pv/salary/list?pno=${pvo.endPage+1}" class="page-button next">&raquo;</a>
                                        </c:if>
                                    </div>
                                   
                                </div>
                            </div>
            
                    <!-- <div class="bottom-content-area-5"><button class="crud-button-white">수정</button></div> -->
                     
            
                    <div></div>
                </div>
            
            </div>
            
            </div>

            <!-- Modal Area -->

            <!-- Small modal -->
            <%@ include file="/WEB-INF/views/common/modal.jsp" %> 


        </div>
    </div>
</body>
</html>
