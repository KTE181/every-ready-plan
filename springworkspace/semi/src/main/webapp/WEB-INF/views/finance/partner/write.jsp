<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/finance/partner/write.css">
</head>


<div id = "partner-write">
    <form action="/finance/partner/write" method="post" onsubmit="return confrim('등록하시겠습니까?');">
    <div class="modal-content">
        <span class="write-close">&times;</span>

        <div class="modal-title">거래처 등록</div>
        <div id="required-text">* 는 필수입력사항입니다.</div>

        <div class="modal-cont">
            <label for="등록번호"></label>
            <div>
                <input type="text" name="no" disabled>
                <button class ="search-button" name="">