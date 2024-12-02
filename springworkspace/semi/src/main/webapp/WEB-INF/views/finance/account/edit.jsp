<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

    <span class="edit-close" onclick="accountEditClose();">&items;</span>

    <div class="modal-title">회사 계좌 등록</div>
    <div id="required-text"> * 는 필수 입력 사항입니다.</div>

    <input type="hidden" name="no" value="${accountVo.no}">

    <div class="modal-cont">
        <label for="bankCode">은행코드</label>
        <input type="text" id="bankCode" /></div>
    <div class="modal-cont">
        <label for="bankname">은행명</label>
        <input type="text" id="bankname" />
    </div>
    <div class="modal-cont">
        <label for="accountNo">계좌번호</label>
        <input type="text" id="accountNo" />
    </div>
    <div class="modal-cont">
        <label for="accountName">계좌 별명</label>
        <input type="text" id="accountName" />
    </div>

        </div>
</div>
