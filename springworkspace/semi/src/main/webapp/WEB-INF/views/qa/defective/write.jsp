<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/defective/write.css">
</head>


<body>

    <div class="modal-overlay" id="modalOverlay">
        <div class="modal">
            <h2>불량 상품 등록</h2>
            <div class = "modal-close">s
                <button onclick = "closeModal();">×</button>
            </div>

            <div class="form-container">
                <div class="section-title">상품정보</div>

                <div class="image-upload-container">
                    <div class="image-placeholder">제품 이미지</div>
                </div>
                
                <div class="form-group">
                    <label for="serialno">일련번호</label>
                    <input type="text" id="dfserialno">
                </div>
                <div class="form-group">
                    <label for="product-name">상품명</label>
                    <input type="text" id="dfproduct-name">
                </div>
                <div class="form-group">
                    <label for="price">가격</label>
                    <input type="text" id="dfprice">
                </div>
                <div class="form-group">
                    <label for="productno">상품번호</label>
                    <input type="text" id="dfproductno">
                </div>
                <div class="form-group">
                    <label for="defective-code">불량코드</label>
                    <input type="text" id="dfdefective-code">
                </div>
               
                <div class="form-group">
                    <label for="defective-name">불량명</label>
                    <input type="text" id="dfdefective-name">
                </div>
                <div class="form-group">
                    <label for="defective-content">불량 내용</label>
                    <textarea id= "dftext-area"></textarea>
                </div>

            </div>
            <div class="modal-buttons">
                <button class="primary" onclick = "openModal();">등록</button>
                <button>삭제</button>
            </div>
        </div>
    </div>

</body>
</html>
    