<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/modal.css">
    <script defer src="/js/common/modal.js"></script>
</head>
<body>
<div>
    <div class="test">TEST</div>
</div>

<!-- 모달 구조 -->
<div id="modal" class="modal">
    <div class="modal-content">
        <span class="modal-close">&times;</span>
        <div class="modal-title">담당자 등록</div>
        <div></div>
        <div class="modal-cont">
            <label for="employeeId">사번</label>
            <input type="text" id="employeeId" /></div>
        <div class="modal-cont">
            <label for="name">담당자명</label>
            <input type="text" id="name" />
        </div>
        <div class="modal-cont">
            <label for="position">직급</label>
            <input type="text" id="position" />
        </div>
        <div class="modal-cont">
            <label for="phone">전화번호</label>
            <input type="text" id="phone" />
        </div>
        <div class="modal-cont">
            <label for="region">담당지역</label>
            <input type="text" id="region" />
        </div>
        <div class="modal-cont"></div>
        <div></div>
        <div class="button-container"><button>등록</button></div>
    </div>
</div>
</body>
</html>
