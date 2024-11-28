<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/qa/asreq/write.css">
</head>



<div id="asreq-write">
    <form action="/qa/asreq/write" method="post">
        <div class="modal-content">
            <span class="write-close">&times;</span>

            <div class="modal-title">AS 요청 등록</div>
            <div id="required-text">* 는 필수입력사항입니다.</div>

            <div class="title-text">AS상품정보</div>

            <input type="hidden" name="productNo" value="1">
            <div class="modal-cont">
                <label for="">상품일련번호</label>
                <div>
                    <input type="text" name="serialNumber" disabled> 
                    <button class="search-button" name="">상품검색</button>
                </div>
                
            </div>
            <div class="modal-cont">
                <label for="">상품명</label>
                <input type="text" name="name" disabled>
            </div>
            <div class="modal-cont">
                <label for="">상품구매일자</label>
                <input type="text" name="purchaseDate">
            </div>
            <div class="modal-cont">
                <label for="">무상가능여부</label>
                <div>
                    <input type="radio" name="warrantyYn" value="Y"> Y
                    <input type="radio" name="warrantyYn" value="N"> N
                </div>
            </div>

            <div class="title-text">AS요청정보</div>

            <div class="modal-cont">
                <label for="">고객명</label>
                <input type="text" name="customerName">
            </div>
            <div class="modal-cont">
                <label>고객주소</label>
                <div>
                    <select name="customerArea">
                        <option value="서울">서울</option>
                        <option value="인천">인천</option>
                        <option value="경기">경기</option>
                        <option value="부산">부산</option>
                        <option value="제주도">제주도</option>
                    </select>
                    <input type="text" name="customerAdress" placeholder="상세주소">
                </div>
            </div>
            <div class="modal-cont">
                <label>고객핸드폰번호</label>
                <input type="text" name="customerPhone" placeholder="숫자만 입력하세요">
            </div>

            <div class="modal-cont">
                <label>AS희망일자</label>
                <input type="text" name="preferredServiceDate">
            </div>
            
            <div class="modal-cont long-textbox">
                <label>AS요청제목</label>
                <input type="text" name="issueTitle">
            </div>
            <div class="modal-cont long-textbox">
                <label>AS요청내용(증상)</label>
                <textarea name="issueDescription"></textarea>
            </div>

            <div></div>

            <div class="button-container"><input type="submit" value="등록"></div>

        </div>
    </form>
</div>
