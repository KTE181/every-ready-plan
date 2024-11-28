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
    <link rel="stylesheet" href="/css/finance/incStat/list.css">
    <script defer src="/js/finance/incStat/list.js"></script>
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
                           <table>
                               <thead>
                                 <tr>
                                   <th>계정과목</th>
                                   <th>당기</th>
                                   <th>금액</th>
                                   <th>전기</th>
                                   <th>금액</th>
                                 </tr>
                               </thead>
                               <tbody>
                                 <tr class="highlight">
                                   <td>1. 매출액</td>
                                   <td>상품매출</td>
                                   <td>${vo.salesMerchandise}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr>
                                   <td></td>
                                   <td>제품매출</td>
                                   <td>${vo.salesGoods}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>2. 매출원가</td>
                                   <td></td>
                                   <td>-</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>3. 매출총이익</td>
                                   <td></td>
                                   <td>25,000,000</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>4. 판매비와 관리비</td>
                                   <td>직원급여</td>
                                   <td>${vo.salariesExpense}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr>
                                   <td></td>
                                   <td>여비교통비</td>
                                   <td>${vo.salariesExpense}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr>
                                   <td></td>
                                   <td>세금과공과</td>
                                   <td>${vo.taxesDues}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr>
                                   <td></td>
                                   <td>사무용품비</td>
                                   <td>${vo.suppliesExpense}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr>
                                   <td></td>
                                   <td>광고선전비</td>
                                   <td>${vo.advertising}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr>
                                   <td></td>
                                   <td>소모품비</td>
                                   <td>${vo.suppliesExpense}</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>5. 영업손익</td>
                                   <td></td>
                                   <td>15,150,000</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>6. 영업외수익</td>
                                   <td></td>
                                   <td>-</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>7. 영업외비용</td>
                                   <td></td>
                                   <td>-</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>8. 법인세비용차감전순손익</td>
                                   <td></td>
                                   <td>-</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                                 <tr class="highlight">
                                   <td>9. 당기순이익</td>
                                   <td></td>
                                   <td class="bold">15050505</td>
                                   <td>-</td>
                                   <td>-</td>
                                 </tr>
                               </tbody>
                             </table>

        </div>
    </div>
</body>
</html>
