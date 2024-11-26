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

	<h1>AS 요청 상세</h1>
    <h3>AS상품정보</h3>
    <input type="hidden" name="pNo" value="${asreqVo.productNo}">
    <label>상품일련번호</label>
    <input type="text" name="serialNumber" value="${asreqVo.serialNumber}" disabled>
    <br>
    <label>상품명</label>
    <input type="text" name="name" value="${asreqVo.name}" disabled>
    <br>
    <label>상품구매일자</label>
    <input type="text" name="purchaseDate" value="${asreqVo.purchaseDate}" disabled>
    <br>
    <label>무상가능여부</label>
    <input type="text" name="warrantyYn" value="${asreqVo.warrantyYn}" disabled>

    <h3>AS요청정보</h3>
    <label>고객명</label>
    <input type="text" name="customerName" value="${asreqVo.customerName}" disabled>
    <br>
    <label>고객주소</label>
    <input type="text" name="customerArea" value="${asreqVo.customerArea}" disabled>
    <input type="text" name="customerAdress" value="${asreqVo.customerAdress}" disabled>
    <br>
    <label>고객핸드폰번호</label>
    <input type="text" name="customerPhone" value="${asreqVo.customerPhone}" disabled>
    <br>
    <label>AS요청제목</label>
    <input type="text" name="issueTitle" value="${asreqVo.issueTitle}" disabled>
    <br>
    <label>AS요청내용(증상)</label>
    <textarea name="issueDescription" disabled>${asreqVo.issueDescription}</textarea>
    <br>
    <label>AS희망일자</label>
    <input type="text" name="preferredServiceDate" value="${asreqVo.preferredServiceDate}" disabled>
    <br>

    <button>접수하기</button>
    <button onclick="location.href='/qa/asreq/edit?asreqNo=${asreqVo.no}'">수정</button>
    <button onclick="location.href='/qa/asreq/delete?asreqNo=${asreqVo.no}'">삭제</button>

</body>
</html>