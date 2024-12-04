
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    
    <link rel="stylesheet" href="/css/common/bottom.css">
    <link rel="stylesheet" href="/css/pv/pvvacation.css">
    <link rel="stylesheet" href="/css/common/search.css">
    <script defer src="/js/pv/pvvacation.js"></script>
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
                    <div class="menu-name">휴가 내역 조회</div>
                    <div>
                        <form action="/pv/vacation" method="get" class="top-title-area-form">
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
                            <div class="search-bar"><input type="date" name="date"></div>
                            <div class="search-bar"><button class="button">검색</button></div>
                        </form>
                    </div>
                </div>
            

                <!-- List Area -->
                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                
                                
                                <th>일자</th>
                                <th>휴가유형</th>
                                <th>총휴가 일 수</th>
                                <th>사용휴가 일 수</th>
                                
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${voList}" var="vo">
                                <tr>
                                    <td>${vo.thisDate}</td>
                                    <td>${vo.type}</td>
                                    <td>${vo.allVacation}</td>
                                    <td>${vo.useVacation}</td>
                                  
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- 총휴가일수 div -->
                 <div class="allvacationdata">
                    <div>남은휴가 일 수</div>
                    <div>${vacationVo.vacation}</div>
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
            
                    <!-- <div class="bottom-content-area-5"><button class="crud-button-white">수정</button></div> -->
                     
            
                    <div></div>
                </div>
            
            </div>

            <!-- Modal Area -->

            <!-- Small modal -->
            <%@ include file="/WEB-INF/views/common/modal.jsp" %> 


        </div>
    </div>
</body>
</html>
