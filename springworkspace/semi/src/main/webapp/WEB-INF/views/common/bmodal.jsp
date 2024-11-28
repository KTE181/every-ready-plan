<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/bmodal.css">
    <script defer src="/js/common/bmodal.js"></script>
</head>

<!-- 모달 구조 -->
<div id="modal" class="modal">
    <div class="modal-content">
        <span class="modal-close">&times;</span>
        <div class="modal-title">상품 등록</div>

        <div class="modal-profile">
            <div class="modal-profile-index">기본 이미지</div>
        </div>
        <div class="modal-cont">
            <label>품목코드</label>
            <input type="text" id="itemCode" />
        </div>
        <div class="modal-cont">
            <label>가격</label>
            <input type="text" id="price" />
        </div>
        <div class="modal-cont">
            <label>상품명</label>
            <input type="text" id="nameP" />
        </div>
        <div class="modal-cont">
            <label>수량</label>
            <input type="text" id="ea" />
        </div>
        <div class="modal-cont">
            <label>일련번호</label>
            <input type="text" id="serialNum" />
        </div>
        <div></div>
        <div class="modal-long">
            <label></label>생산공정명</label>
            <input type="text" id="factoryAdress" />
        </div>
        <div class="modal-long">
            <label></label>생산공정주소</label>
            <input type="text" id="adress" />
        </div>
        <div></div>
        <div class="modal-cont">
            <label>보증기간</label>
            <input type="text" id="warranty" />
        </div>
        <div></div>
        <div></div>
        <div class="modal-cont">
            <label>입고일</label>
            <input type="text" id="receivedDate" />
        </div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>

        <div class="button-container"><button>등록</button></div>
    </div>
</div>
