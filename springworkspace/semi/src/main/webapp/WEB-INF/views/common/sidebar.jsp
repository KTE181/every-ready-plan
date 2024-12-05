<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>EVERY READY PLAN</title>
      <link rel="stylesheet" href="/css/common/sidebar.css">
      <script defer src="/js/common/sidebar.js"></script>
    </head>
    <body>
    <script >
      <c:if test="${not empty sessionScope.loginEmployeeVo}">
      document.addEventListener("DOMContentLoaded" , function(){
        disabledinsadelete(${loginEmployeeVo.deptCode});
      }); 
      </c:if>
    </script>

    

    <div class="sidebar">
      <div class="logo-area"><img src="/img/common/logo.png"></div>
      <div class="menu-item dropdown">
        <div class="title-menu">공용</div>
        <ul class="dropdown-menu">
          <li>회사 조직도</li>
          <li>회사 연혁도</li>
          <li>직원 검색</li>
        </ul>
      </div>
      <div class="menu-item dropdown1">
        <div class="title-menu">개인</div>
        <ul class="dropdown-menu1">
          <li>마이페이지</li>
          <li>휴가 내역 조회</li>
          <li>출·퇴근내역 조회</li>
          <li>급여명세서 조회</li>
        </ul>
      </div>
      <div class="menu-item dropdown2">
        <div class="title-menu">인사</div>
        <ul class="dropdown-menu2">
          <li class="disabledinsa">사원 관리</li>
          <li class="disabledinsa">근태 관리</li>
          <li class="disabledinsa">초과 근무 관리</li>
          <li class="disabledinsa">휴가 관리</li>
          <li class="disabledinsa">급여 관리</li>
        </ul>
      </div>
      <div class="menu-item dropdown3">
        <div class="title-menu">품질</div>
        <ul class="dropdown-menu3">
          <li>상품 관리</li>
          <li>재고 현황 조회</li>
          <li>불량상품 관리</li>
          <li>AS 요청 관리</li>
          <li>AS 작업 관리</li>
          <li>품질 관리</li>
          <li>담당자 관리</li>
          <li>불량코드 관리</li>
          <li>고장코드 관리</li>
        </ul>
      </div>
      <div class="menu-item dropdown4">
        <div class="title-menu">재무</div>
        <ul class="dropdown-menu4">
          <li>거래처 관리</li>
          <li>회사 계좌 관리</li>
          <li>매출 관리</li>
          <li>매입 관리</li>
          <li>경비 관리</li>
          <li>급여 지급 관리</li>
          <li>재무상태표 조회</li>
          <li>손익계산서 조회</li>
        </ul>
      </div>
    </div>
  </body>