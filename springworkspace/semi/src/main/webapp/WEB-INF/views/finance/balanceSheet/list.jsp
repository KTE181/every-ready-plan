<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMI</title>

</head>

<body>
    <main>

        <h1 align="center">BALNCESHEET</h1>

        <div class="table-area">
            <table border="1">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>연도</th>
                        <th>보통예금/th>
                        <th>부가세대급금/th>
                        <th>상품</th>
                        <th>원재료</th>
                        <th>예수금</th>
                        <th>부가세예수금</th>
                        <th>자본금</th>
                        </tr>
                </thead>
                <tbody>
                    <c:forEach var="vo" items="${balanceSheetVoList}">
                        <tr>
                            <td>${vo.no}</td>
                            <td>${vo.year}</td>
                            <td>${vo.deposit}</td>
                            <td>${vo.prepaidTax}</td>
                            <td>${vo.merchadise}</td>
                            <td>${vo.rawMaterial}</td>
                            <td>${vo.withholdings}</td>
                            <td>${vo.withheldTax}</td>
                            <td>${vo.capitalStock}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>

        <div class="page-area">

        </div>
    </main>
</body>
</html>