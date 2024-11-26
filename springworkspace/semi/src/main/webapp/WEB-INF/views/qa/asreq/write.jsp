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
	<h1>AS 요청 등록</h1>
    <form action="/qa/asreq/write" method="post">
        <h3>AS상품정보</h3>
        <input type="hidden" name="productNo" value="1">
        <label>상품일련번호</label>
        <input type="text" name="serialNumber" disabled>
        <button>상품검색</button>
        <br>
        <label>상품명</label>
        <input type="text" name="name" disabled>
        <br>
        <label>상품구매일자</label>
        <input type="text" name="purchaseDate">
        <br>
        <label>무상가능여부</label>
        <input type="radio" name="warrantyYn" value="Y"> Y
        <input type="radio" name="warrantyYn" value="N"> N

        <h3>AS요청정보</h3>
        <label>고객명</label>
        <input type="text" name="customerName">
        <br>
        <label>고객주소</label>
        <select name="customerArea">
            <option value="서울">서울</option>
            <option value="인천">인천</option>
            <option value="경기">경기</option>
            <option value="부산">부산</option>
            <option value="제주도">제주도</option>
        </select>
        <input type="text" name="customerAdress" placeholder="상세주소">
        <br>
        <label>고객핸드폰번호</label>
        <input type="text" name="customerPhone" placeholder="숫자만 입력하세요">
        <br>
        <label>AS요청제목</label>
        <input type="text" name="issueTitle">
        <br>
        <label>AS요청내용(증상)</label>
        <textarea name="issueDescription"></textarea>
        <br>
        <label>AS희망일자</label>
        <input type="text" name="preferredServiceDate">
        <br>
        <input type="submit" value="등록">
    </form>
</body>
</html>