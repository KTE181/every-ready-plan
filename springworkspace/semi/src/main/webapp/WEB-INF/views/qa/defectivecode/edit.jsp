<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/product/defectivecode.css">
    <script defer src="/js/defectiveCode/list.js"></script>
</head>
<body>

    <div class="modal-edit" id="modalEdit">
        <div class="modal">
                <button id = "closeDefectiveCodeUpdateModal">×</button>
            <h2>불량코드 수정</h2>
            <label for="code-number">불량유형번호</label>
            <input type="text" id="edit-code-number" readonly>
            <label for="code-name">불량유형명</label>
            <input type="text" id="edit-code-name" >
            <div class="modal-buttons">
                <button class="primary">저장</button>
            </div>
        </div>
    </div>
</body>
</html>