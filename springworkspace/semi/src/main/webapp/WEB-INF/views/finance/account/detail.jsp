<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    

    <span class="detail-close" onclick="accountDetailClose();">&times;</span>

    <div class="modal-title">회사 계좌 등록</div>
    <div id="required-text"></div>

    <div class="modal-cont">
        <label for="no">번호</label>
        <input type = "text" name = "no" value = "${accountVo.no}">
    </div>
    <div class="modal-cont">
        <label for="bankCode">은행코드</label>
        <input type = "text" name = "bankCode" value = "${accountVo.bankCode}">
    </div>
    <div class="modal-cont">
        <label for="bankName">은행명</label>
        <input type = "text" name = "bankName" value = "${accountVo.bankName}">
    </div>
    <div class="modal-cont">
        <label for="accountNo">계좌번호</label>
        <input type = "text" name = "accountNo" value = "${accountVo.accountNo}">
    </div>
    <div class="modal-cont">
        <label for="accountName">계좌별명</label>
        <input type = "text" name = "accountName" value = "${accountVo.accountName}">
    </div>
    <div class="button-container">
        <div><input type="button" id="account-receive-button" value="접수하기"></div>
        <div><input type="button" value="수정"></div>
        <div><input type="submit" value="삭제"></div>
    </div>
    
    </div>