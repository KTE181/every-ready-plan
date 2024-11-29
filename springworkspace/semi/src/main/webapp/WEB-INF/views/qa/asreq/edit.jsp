<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    

    <span class="edit-close" onclick="asreqEditClose();">&times;</span>
    
    <div class="modal-title">AS 요청 수정</div>
    <div id="required-text">* 는 필수입력사항입니다.</div>

    <div class="title-text">AS상품정보</div>
    <input type="hidden" name="no" value="${asreqVo.no}">
    <input type="hidden" name="productNo" value="${asreqVo.productNo}">

    <div class="modal-cont">
        <label for="">상품일련번호</label>
        <div>
            <input type="text" name="serialNumber" value="${asreqVo.serialNumber}" disabled>
            <button class="search-button" name="">상품검색</button>
        </div>

    </div>
    <div class="modal-cont">
        <label for="">상품명</label>
        <input type="text" name="name" id="" value="${asreqVo.name}" disabled>
    </div>
    <div class="modal-cont">
        <label for="">상품구매일자</label>
        <input type="date" name="purchaseDate" value="${asreqVo.purchaseDate}">
    </div>
    <div class="modal-cont">
        <label for="">무상가능여부</label>
        <div>
            <c:if test="${asreqVo.warrantyYn == 'Y'}">
                <input type="radio" name="warrantyYn" value="Y" checked> Y
                <input type="radio" name="warrantyYn" value="N"> N
            </c:if>
            <c:if test="${asreqVo.warrantyYn == 'N'}">
                <input type="radio" name="warrantyYn" value="Y"> Y
                <input type="radio" name="warrantyYn" value="N" checked> N
            </c:if>
        </div>
    </div>

    <div class="title-text">AS요청정보</div>

    <div class="modal-cont">
        <label for="">고객명</label>
        <input type="text" name="customerName" value="${asreqVo.customerName}">
    </div>
    <div class="modal-cont">
        <label>고객주소</label>
        <div>
            <select name="customerArea">
                <option value="서울" <c:if test="${asreqVo.customerArea == '서울'}">selected</c:if>>서울</option>
                <option value="인천" <c:if test="${asreqVo.customerArea == '인천'}">selected</c:if>>인천</option>
                <option value="경기" <c:if test="${asreqVo.customerArea == '경기'}">selected</c:if>>경기</option>
                <option value="부산" <c:if test="${asreqVo.customerArea == '부산'}">selected</c:if>>부산</option>
                <option value="제주도" <c:if test="${asreqVo.customerArea == '제주도'}">selected</c:if>>제주도</option>
            </select>
            <input type="text" name="customerAdress" placeholder="상세주소" value="${asreqVo.customerAdress}">
        </div>
    </div>
    <div class="modal-cont">
        <label>고객핸드폰번호</label>
        <input type="text" name="customerPhone" placeholder="숫자만 입력하세요" value="${asreqVo.customerPhone}">
    </div>

    <div class="modal-cont">
        <label>AS희망일자</label>
        <input type="date" name="preferredServiceDate" value="${asreqVo.preferredServiceDate}">
    </div>

    <div class="modal-cont long-textbox">
        <label>AS요청제목</label>
        <input type="text" name="issueTitle" value="${asreqVo.issueTitle}">
    </div>
    <div class="modal-cont long-textbox">
        <label>AS요청내용(증상)</label>
        <textarea name="issueDescription">${asreqVo.issueDescription}</textarea>
    </div>

    <div></div>

    <div class="button-container"><input type="submit" value="저장"></div>

