<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬로월드</title>
<style>
    table, th, tr, td{
        border: 1px solid black;
        border-collapse: collapse;
    }
</style>
</head>
<body>
	<h1>AS 요청 목록</h1>
    <table>
        <thead>
            <tr>
                <th>NO</th>
                <th>상품번호</th>
                <th>고객명</th>
                <th>고객지역</th>
                <th>고객주소</th>
                <th>고객핸드폰번호</th>
                <th>구매일자</th>
                <th>무상가능여부</th>
                <th>AS요청제목</th>
                <th>AS요청내용(증상)</th>
                <th>AS희망일자</th>
                <th>상태코드</th>
                <th>등록일자</th>
                <th>수정일자</th>
                <th>삭제여부</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${asreqVoList}" var="asreqVo">
                <tr onclick="location.href='/qa/asreq/detail?asreqNo=${asreqVo.no}'">
                    <td>${asreqVo.no}</td>
                    <td>${asreqVo.productNo}</td>
                    <td>${asreqVo.customerName}</td>
                    <td>${asreqVo.customerArea}</td>
                    <td>${asreqVo.customerAdress}</td>
                    <td>${asreqVo.customerPhone}</td>
                    <td>${asreqVo.purchaseDate}</td>
                    <td>${asreqVo.warrantyYn}</td>
                    <td>${asreqVo.issueTitle}</td>
                    <td>${asreqVo.issueDescription}</td>
                    <td>${asreqVo.preferredServiceDate}</td>
                    <td>${asreqVo.statusCode}</td>
                    <td>${asreqVo.enrollDate}</td>
                    <td>${asreqVo.modifyDate}</td>
                    <td>${asreqVo.delYn}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>