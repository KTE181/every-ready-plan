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
    <div class="modal-overlay" id="modalOverlay">
        <div class="modal">
            <div id = "closeModalBtn">
                <button onclick = "closeModal();">×</button>
            </div>
            <h2>상품 등록</h2>
            <div class="form-container">
                <div class="section-title">상품정보</div>

                <div class="image-upload-container">
                    <div class="image-placeholder">제품 이미지</div>
                </div>

                <div class="form-group">
                    <label for="product-code">품목코드</label>
                    <input type="text" id="product-code" value="0001">
                </div>
                <div class="form-group">
                    <label for="product-name">상품명</label>
                    <input type="text" id="product-name" value="LG트윈냉장고">
                </div>
                <div class="form-group">
                    <label for="product-price">가격</label>
                    <input type="text" id="product-price" value="1500000">
                </div>
                <div class="form-group">
                    <label for="product-quantity">수량</label>
                    <input type="text" id="product-quantity" value="32">
                </div>
                <div class="form-group">
                    <label for="serial-number">일련번호</label>
                    <input type="text" id="serial-number" value="자동부여">
                </div>
                <div class="form-group">
                    <label for="manufacturer">생산공정</label>
                    <input type="text" id="manufacturer" value="파주(주)">
                </div>
                <div class="form-group">
                    <label for="manufacturer-address">생산공장</label>
                    <input type="text" id="manufacturer-address" value="서울특별시 둘리구 호이동">
                </div>
                <div class="form-group">
                    <label for="warranty">보증기간</label>
                    <input type="text" id="warranty" value="3년">
                </div>
                <div class="form-group">
                    <label for="import-date">입고일</label>
                    <input type="date" id="import-date" value="2014-11-01">
                </div>
            </div>
            <div class="modal-buttons">
                <button class="primary">등록</button>
                <button>삭제</button>
            </div>
        </div>
    </div>

</body>
</html>
    