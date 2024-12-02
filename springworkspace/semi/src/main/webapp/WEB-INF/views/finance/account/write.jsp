<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/finance/account/write.css">
</head>

    <div class="account-write">
    <form action="/finance/account/write" method="post" onsubmit="return confirm('등록하시겠습니까?');">
        <div class="modal-content">
            <span class="write-close">&times;</span>

            <div class="modal-title">회사 계좌 등록</div>
            <div id="required-text"> * 는 필수 입력 사항입니다.</div>

            <input type="hidden" name="productNo" value="1">

            <div>
            </div>

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

        <div class="modal-cont"></div>
        <div></div>
        <div class="modal-cont"></div>
        <div class="button-container"><input type="submit" value="등록" ></div>


        </div>
    </form>
</div>
