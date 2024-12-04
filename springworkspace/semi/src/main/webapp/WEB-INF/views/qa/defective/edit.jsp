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

    <div class="modal-defective-edit" id="modaldefectiveEdit">
        <div class="modal">
            <button id="closeDefectiveUpdateModal">×</button>
            <h2>불량 상품 수정</h2>
            <div class="form-container">
                <div class="section-title">상품정보</div>

                <div class="image-upload-container">
                    <div class="image-placeholder">제품 이미지</div>
                </div>

                <div class="form-group">
                    <label for="productno">상품번호</label>
                    <input type="text" id="edit-productno" readonly>
                </div>
                <div class="form-group">
                    <label for="price">가격</label>
                    <input type="text" id="edit-price" readonly>
                </div>
                <div class="form-group">
                    <label for="productname">상품명</label>
                    <input type="text" id="edit-productname" readonly>
                </div>
                <div class="form-group">
                    <label for="serialno">일련번호</label>
                    <input type="text" id="edit-serialNumber" readonly>
                </div>
                <div class="form-group">
                    <label for="defectivecode">불량코드</label>
                    <select id = defectivecode-select-edit>

                    </select>
                </div>
                
                <div class="form-group">
                    <label for="defectivename">불량명</label>
                    <input type="text" id="edit-defectivename" value = "" readonly>
                </div>
                <div class="form-group">
                    <label for="defectivecontent">불량 내용</label>
                    <textarea id= "edit-text-area"></textarea>
                </div>

            </div>
            <div class="modal-buttons">
                <button class="primary">저장</button>
            </div>
        </div>
    </div>

</body>
</html>