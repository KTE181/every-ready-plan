<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    

    <span class="detail-close" onclick="asreqDetailClose();">&times;</span>
    
    <div class="modal-title">AS 요청 상세</div>
    <div id="required-text"></div>

    <div class="title-text">AS상품정보</div>
    <input type="hidden" name="productNo" value="${asreqVo.productNo}">

    <div class="modal-cont">
        <label for="">상품일련번호</label>
        <div>
            <input type="text" name="serialNumber" value="${asreqVo.serialNumber}" disabled>
        </div>

    </div>
    <div class="modal-cont">
        <label for="">상품명</label>
        <input type="text" name="name" value="${asreqVo.name}" disabled>
    </div>
    <div class="modal-cont">
        <label for="">상품구매일자</label>
        <input type="text" name="purchaseDate" value="${asreqVo.purchaseDate}" disabled>
    </div>
    <div class="modal-cont">
        <label for="">무상가능여부</label>
        <input type="text" name="purchaseDate" value="${asreqVo.warrantyYn}" disabled>
    </div>

    <div class="title-text">AS요청정보</div>

    <div class="modal-cont">
        <label for="">고객명</label>
        <input type="text" name="customerName" value="${asreqVo.customerName}" disabled>
    </div>
    <div class="modal-cont">
        <label>고객주소</label>
        <div>
            <select name="customerArea" disabled>
                <option value="서울">서울</option>
                <option value="인천">인천</option>
                <option value="경기">경기</option>
                <option value="부산">부산</option>
                <option value="제주도">제주도</option>
            </select>
            <input type="text" name="customerAdress" placeholder="상세주소" value="${asreqVo.customerAdress}" disabled>
        </div>
    </div>
    <div class="modal-cont">
        <label>고객핸드폰번호</label>
        <input type="text" name="customerPhone" placeholder="숫자만 입력하세요" value="${asreqVo.customerPhone}" disabled>
    </div>

    <div class="modal-cont">
        <label>AS희망일자</label>
        <input type="text" name="preferredServiceDate" value="${asreqVo.preferredServiceDate}" disabled>
    </div>

    <div class="modal-cont long-textbox">
        <label>AS요청제목</label>
        <input type="text" name="issueTitle" value="${asreqVo.issueTitle}" disabled>
    </div>
    <div class="modal-cont long-textbox">
        <label>AS요청내용(증상)</label>
        <textarea name="issueDescription" disabled>${asreqVo.issueDescription}</textarea>
    </div>

    <div></div>

    <div class="button-container"><input type="submit" value="수정"></div>
