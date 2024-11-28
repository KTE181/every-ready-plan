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
    <link rel="stylesheet" href="/css/finance/balanceSheet/detail.css">
    <script defer src="/js/finance/balanceSheet/detail.js"></script>
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

               <h1 align="center">재무 상태표</h1>

                  <div class="table-area">
                      <table border="1">
                          <thead>
                           <tr>
                               <th></th>
                               <th></th>
                               <th></th>
                               <th>조회연도</th>
                               <th>${vo.year}</th>
                           </tr>
                           <tr>
                               <th>계정과목</th>
                               <th colspan="2">당기</th>
                               <th colspan="2">전기</th>
                           </tr>
                           <tr>
                               <th></th>
                               <th colspan="2">금액</th>
                               <th colspan="2">금액</th>
                           </tr>
                          </thead>

                          <tbody>

                          <tr class="highlight">
                           <td>자산</td>
                           <td></td>
                           <td></td>
                           <td></td>
                           <td></td>
                          </tr>
                            <tr class="highlight">
                              <td>1. 유동자산</td>
                              <td></td>
                              <td>당좌자산+재고자산</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>당좌자산</td>
                              <td></td>
                              <td>보통예금+부가세대급금</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr class="highlight">
                              <td>보통예금</td>
                              <td>${vo.deposit}</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                            </tr>
                            <tr class="highlight">
                              <td>부가세대급금</td>
                              <td>${vo.prepaidTax}</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                            </tr>
                            <tr>
                              <td>(2) 재고자산</td>
                              <td></td>
                              <td>상품+원재료</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>상품</td>
                              <td>${vo.merchadise}</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                            </tr>
                            <tr>
                              <td>원재료</td>
                              <td>${vo.rawMaterial}</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                            </tr>
                            <tr>
                               <td>2. 비유동자산</td>
                               <td></td>
                               <td>-</td>
                               <td></td>
                               <td>-</td>
                             </tr>
                            <tr>
                              <td>[자산총계]</td>
                              <td></td>
                              <td>유동자산+비유동자산인데 비유동은 없어</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>부채</td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td>1. 유동부채</td>
                              <td></td>
                              <td>예수금+부가세예수금</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr class="highlight">
                              <td>예수금</td>
                              <td>${vo.withholdings}</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                            </tr>
                            <tr class="highlight">
                              <td>부가세예수금</td>
                              <td>${vo.withheldTax}</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                            </tr>
                            <tr class="highlight">
                              <td>2. 비유동부채</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr class="highlight">
                              <td>[부채 총계]</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>자본</td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                            </tr>
                            <tr>
                              <td>1. 자본금</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>자본금</td>
                              <td>${vo.capitalStock}</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                            </tr>
                            <tr>
                              <td>2. 자본잉여금</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>3.자본조정</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>4. 기타포괄손익누계액</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>5. 이익잉여금</td>
                              <td></td>
                              <td>-</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>[자본총계]</td>
                              <td></td>
                              <td>자본금들고오기</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                            <tr>
                              <td>[부채와자본총계]</td>
                              <td></td>
                              <td>부채와자본총계</td>
                              <td></td>
                              <td>-</td>
                            </tr>
                          </tbody>
                        </table>
        </div>
    </div>
</body>
</html>
