<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/product/write.css">
</head>


<body>

    <!-- 모달 오버레이 -->
    <div class="modal-detail" id="modalDetail">
        <div class="modal">
            <button id="closeDetailModal">×</button>
            <h2>불량코드 상세 조회</h2>
            <label for="code-number">불량유형번호</label>
            <input type="text" id="code-number" value="1" readonly>
            <label for="code-name">불량유형명</label>
            <input type="text" id="code-name" value="오염" readonly>
            <div class="modal-buttons">
                <button class="primary">수정</button>
                <button>삭제</button>
            </div>
        </div>
    </div>
</body>
</html>
    