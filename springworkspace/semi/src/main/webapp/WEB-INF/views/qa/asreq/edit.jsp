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
	<h1>AS 요청 수정</h1>
    <form action="/qa/asreq/edit" method="post">
        <input type="hidden" name="no" value="${asreqVo.no}">
        <h3>AS상품정보</h3>
        <input type="hidden" name="productNo" value="${asreqVo.productNo}">
        <label>상품일련번호</label>
        <input type="text" name="serialNumber" value="${asreqVo.serialNumber}" disabled>
        <button>상품검색</button>
        <br>
        <label>상품명</label>
        <input type="text" name="name" value="${asreqVo.name}" disabled>
        <br>
        <label>상품구매일자</label>
        <input type="text" name="purchaseDate" value="${asreqVo.purchaseDate}">
        <br>
        <label>무상가능여부</label>
        <c:if test="${asreqVo.warrantyYn == 'Y'}">
            <input type="radio" name="warrantyYn" value="Y" checked> Y
            <input type="radio" name="warrantyYn" value="N"> N
        </c:if>
        <c:if test="${asreqVo.warrantyYn == 'N'}">
            <input type="radio" name="warrantyYn" value="Y"> Y
            <input type="radio" name="warrantyYn" value="N" checked> N
        </c:if>

        <h3>AS요청정보</h3>
        <label>고객명</label>
        <input type="text" name="customerName" value="${asreqVo.customerName}">
        <br>
        <label>고객주소</label>
        <select name="customerArea">
                <option value="서울" ${asreqVo.customerArea == '서울' ? 'selected' : ''}>서울</option>
                <option value="인천" ${asreqVo.customerArea == '인천' ? 'selected' : ''}>인천</option>
                <option value="경기" ${asreqVo.customerArea == '경기' ? 'selected' : ''}>경기</option>
                <option value="부산" ${asreqVo.customerArea == '부산' ? 'selected' : ''}>부산</option>
                <option value="제주도" ${asreqVo.customerArea == '제주도' ? 'selected' : ''}>제주도</option>
        </select>

        <input type="text" name="customerAdress" placeholder="상세주소" value="${asreqVo.customerAdress}">
        <br>
        <label>고객핸드폰번호</label>
        <input type="text" name="customerPhone" placeholder="숫자만 입력하세요" value="${asreqVo.customerPhone}">
        <br>
        <label>AS요청제목</label>
        <input type="text" name="issueTitle" value="${asreqVo.issueTitle}">
        <br>
        <label>AS요청내용(증상)</label>
        <textarea name="issueDescription">${asreqVo.issueDescription}</textarea>
        <br>
        <label>AS희망일자</label>
        <input type="text" name="preferredServiceDate" value="${asreqVo.preferredServiceDate}">
        <br>
        <input type="submit" value="저장">
    </form>
</body>
</html>