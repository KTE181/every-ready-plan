// 등록 모달
function loadWriteModal() {

    const modal = document.querySelector("#asreq-modal");
    const modalForm = document.querySelector("#asreq-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 요청 등록";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'block';
    }
    document.querySelector("#search-button").setAttribute("type", "button");
    document.querySelector("#purchase-date").innerHTML = `<div>상품구매일자</div><input type="date" name="purchaseDate">`
    document.querySelector("#warranty-yn").innerHTML = `<input type="radio" name="warrantyYn" value="Y">Y <input type="radio" name="warrantyYn" value="N">N `;
    document.querySelector("input[name=customerName]").removeAttribute("disabled");
    document.querySelector("input[name=customerPhone]").removeAttribute("disabled");
    document.querySelector("select[name=customerArea]").removeAttribute("disabled");
    document.querySelector("input[name=customerAdress]").removeAttribute("disabled");
    document.querySelector("#preferred-service-date").innerHTML = `<div>AS희망일자</div><input type="date" name="preferredServiceDate">`;
    document.querySelector("input[name=issueTitle]").removeAttribute("disabled");
    document.querySelector("textarea[name=issueDescription]").removeAttribute("disabled");
    document.querySelector("#enroll-date").style.display = 'none';
    document.querySelector("#modify-date").style.display = 'none';
    document.querySelector(".button-container").innerHTML = `<input type="button" id="asreq-write-button" value="등록">`;
   
    // 모달 데이터 초기화 
    document.querySelector('#asreq-modal input[name=productNo]').value = "";

    // 등록 버튼 클릭 시 동작 
    const writeButton = document.querySelector("#asreq-write-button");
    writeButton.addEventListener("click", function () {
        write();
    });

}

// 등록 처리
function write() {

    const productNo = document.querySelector('#asreq-modal input[name=productNo]').value;
    const purchaseDateElement = document.querySelector('#asreq-modal input[name=purchaseDate]');
    const purchaseDate = purchaseDateElement ? purchaseDateElement.value : null;
    const warrantyYnElement = document.querySelector('#asreq-modal input[name=warrantyYn]:checked');
    const warrantyYn = warrantyYnElement ? warrantyYnElement.value : null;
    const customerName = document.querySelector('#asreq-modal input[name=customerName]').value;
    const customerArea = document.querySelector('#asreq-modal select[name=customerArea]').value;
    const customerAdress = document.querySelector('#asreq-modal input[name=customerAdress]').value;
    const customerPhone = document.querySelector('#asreq-modal input[name=customerPhone]').value;
    const preferredServiceDateElement = document.querySelector('#asreq-modal input[name=preferredServiceDate]');
    const preferredServiceDate = preferredServiceDateElement ? preferredServiceDateElement.value : null;
    const issueTitle = document.querySelector('#asreq-modal input[name=issueTitle]').value;
    const issueDescription = document.querySelector('#asreq-modal textarea[name=issueDescription]').value;

    if (!productNo) {
        alert("상품을 선택해주세요.");
        return;
    }
    if (!customerName) {
        alert("고객명을 입력해주세요.");
        return;
    }
    if (customerArea == "") {
        alert("고객 지역을 선택해주세요.");
        return;
    }
    if (!customerAdress) {
        alert("고객 상세주소를 입력해주세요.");
        return;
    }
    if (!customerPhone) {
        alert("고객 핸드폰번호를 입력해주세요.");
        return;
    }
    if (customerPhone.length > 11) {
        alert("고객 핸드폰번호를 숫자 11자 이하로 입력해주세요.");
        return;
    }
    if (!issueTitle) {
        alert("AS요청제목을 입력해주세요.");
        return;
    }
    if (!issueDescription) {
        alert("AS요청내용을 입력해주세요.");
        return;
    }

    const result = confirm("등록하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asreq/write",
        method: "POST",
        data : {
            productNo ,
            purchaseDate ,
            warrantyYn ,
            customerName ,
            customerArea ,
            customerAdress ,
            customerPhone ,
            preferredServiceDate ,
            issueTitle ,
            issueDescription
        },

        success: function(result) {
            if(result == 1) {
                alert("등록되었습니다.");
            }
            else {
                alert("등록실패... 관리자에게 문의해주세요.");
            }
            location.href = "/qa/asreq/list";
        },
        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
            location.href = "/qa/asreq/list";
        }
    });
}

// 상품 검색 모달
function productList() {

    const productModal = document.querySelector("#product-modal");
    const searchForm = document.querySelector("#product-search-form");
    const closeModal = document.querySelector("#product-modal .modal-close");
    searchForm.reset();
    productModal.style.display = 'block'; 

    closeModal.addEventListener('click', () => {
        productModal.style.display = 'none';
    } , { once: true });

    const pno = 1;
    
    productData(pno);
    
}

// 상품 검색 데이터 요청 
function productData(pno) {

    const tbodyTag = document.querySelector("#product-modal table>tbody");
    const productSearchType = document.querySelector("#product-modal select[name=productSearchType]").value;
    const productSearchValue = document.querySelector("#product-modal input[name=productSearchValue]").value;

    $.ajax({
        url : "/qa/asreq/productlist",
        data : {
            pno ,
            productSearchType ,
            productSearchValue
        } ,
        method : "GET" ,
        success : function(m){

            const productVoList = m.a;
            const pvo = m.b;
            paintPageArea(pvo);

            tbodyTag.innerHTML = ""; 

            for(const vo of productVoList) {

                const trTag = document.createElement("tr");
                tbodyTag.appendChild(trTag); 

                const tdTag = document.createElement("td");
                const radioTag = document.createElement("input");
                radioTag.setAttribute("type", "radio");
                radioTag.setAttribute("name", "product-radio-btn");
                const noTag = document.createElement("input");
                noTag.setAttribute("type", "hidden");
                noTag.value = vo.no;

                trTag.appendChild(tdTag);
                tdTag.appendChild(radioTag);
                tdTag.appendChild(noTag);

                const tdTag1 = document.createElement("td");
                tdTag1.innerText = vo.itemCode;
                trTag.appendChild(tdTag1);

                const tdTag2 = document.createElement("td");
                tdTag2.innerText = vo.itemName;
                trTag.appendChild(tdTag2);
                
                const tdTag3 = document.createElement("td");
                tdTag3.innerText = vo.serialNumber;
                trTag.appendChild(tdTag3);

                const tdTag4 = document.createElement("td");
                tdTag4.innerText = vo.name;
                trTag.appendChild(tdTag4);

                const tdTag5 = document.createElement("td");
                tdTag5.innerText = vo.price;
                trTag.appendChild(tdTag5);

                const tdTag6 = document.createElement("td");
                tdTag6.innerText = vo.warrantyPeriod;
                trTag.appendChild(tdTag6);
            }
        } , 
        error : function(){
            alert("조회 실패... 관리자에게 문의해주세요.")
        }
    })

}

// 상품 검색 모달 페이징
function paintPageArea(pvo){
    const pageArea = document.querySelector(".page-area");
    pageArea.innerHTML = "";

    // 이전 버튼
    if(pvo.startPage != 1) {
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `productData(${pvo.startPage-1});`);
        spanTag.innerText = "이전";
        pageArea.appendChild(spanTag);
    }

    // 페이지 버튼
    for(let i=pvo.startPage; i<=pvo.endPage; i++){
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `productData(${i});`);
        spanTag.innerText = i;
        pageArea.appendChild(spanTag);
    }

    // 다음 버튼
    if(pvo.endPage != pvo.maxPage){
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `productData(${pvo.endPage+1});`);
        spanTag.innerText = "다음";
        pageArea.appendChild(spanTag);
    }
}

// 상품 선택 처리
function selectProduct() {

    const selectedProduct = document.querySelector("#product-modal input[type=radio]:checked");
    if (selectedProduct === null) {
        alert("상품이 선택되지 않았습니다.");
        return;
    }

    const no = selectedProduct.nextSibling.value;
    const itemCode = selectedProduct.parentNode.parentNode.children[1].innerText;
    const itemName = selectedProduct.parentNode.parentNode.children[2].innerText;
    const serialNumber = selectedProduct.parentNode.parentNode.children[3].innerText;
    const productName = selectedProduct.parentNode.parentNode.children[4].innerText;

    document.querySelector("#asreq-modal input[name=productNo]").value = no;
    document.querySelector("#asreq-modal input[name=itemCode]").value = itemCode;
    document.querySelector("#asreq-modal input[name=itemName]").value = itemName;
    document.querySelector("#asreq-modal input[name=serialNumber]").value = serialNumber;
    document.querySelector("#asreq-modal input[name=name]").value = productName;

    const productModal = document.querySelector("#product-modal");
    productModal.style.display = 'none';

}

// 상세 모달
function loadDetailModal(asreqNo) {

    const modal = document.querySelector("#asreq-modal");
    const modalForm = document.querySelector("#asreq-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 요청 상세";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'none';
    }
    document.querySelector("#search-button").setAttribute("type", "hidden");
    document.querySelector("#purchase-date").innerHTML = `<div>상품구매일자</div><input type="text" name="purchaseDate" disabled>`
    document.querySelector("#warranty-yn").innerHTML = `<input type="text" name="warrantyYn" disabled>`;
    document.querySelector("input[name=customerName]").setAttribute("disabled", "true");
    document.querySelector("input[name=customerPhone]").setAttribute("disabled", "true");
    document.querySelector("select[name=customerArea]").setAttribute("disabled", "true");
    document.querySelector("input[name=customerAdress]").setAttribute("disabled", "true");
    document.querySelector("input[name=issueTitle]").setAttribute("disabled", "true");
    document.querySelector("textarea[name=issueDescription]").setAttribute("disabled", "true");
    document.querySelector("#preferred-service-date").innerHTML = `<div>AS희망일자</div><input type="text" name="preferredServiceDate" disabled>`
    document.querySelector("#enroll-date").style.display = 'flex';
    document.querySelector("#modify-date").style.display = 'flex';
    document.querySelector(".button-container").innerHTML =         
        `<input type="button" id="asreq-receive-button" value="접수하기">
        <input type="button" id="asreq-edit-button" value="수정">
        <input type="button" id="asreq-delete-button" value="삭제">`;
    

    $.ajax({
        url: "/qa/asreq/detail",
        method: "get",
        data: {
            asreqNo : asreqNo 
        } ,
        success: function(asreqVo) {

            document.querySelector("#asreq-modal input[name=no]").value = asreqVo.no;
            document.querySelector("#asreq-modal input[name=itemCode]").value = asreqVo.itemCode;
            document.querySelector("#asreq-modal input[name=itemName]").value = asreqVo.itemName;
            document.querySelector("#asreq-modal input[name=productNo]").value = asreqVo.productNo;
            document.querySelector("#asreq-modal input[name=serialNumber]").value = asreqVo.serialNumber;
            document.querySelector("#asreq-modal input[name=name]").value = asreqVo.productName;
            document.querySelector("#asreq-modal input[name=purchaseDate]").value = asreqVo.purchaseDate;
            document.querySelector("#asreq-modal input[name=warrantyYn]").value = asreqVo.warrantyYn;
            document.querySelector("#asreq-modal input[name=customerName]").value = asreqVo.customerName;
            document.querySelector("#asreq-modal select[name=customerArea]").value = asreqVo.customerArea;
            document.querySelector("#asreq-modal input[name=customerAdress]").value = asreqVo.customerAdress;
            document.querySelector("#asreq-modal input[name=customerPhone]").value = asreqVo.customerPhone;
            document.querySelector("#asreq-modal input[name=preferredServiceDate]").value = asreqVo.preferredServiceDate;
            document.querySelector("#asreq-modal input[name=issueTitle]").value = asreqVo.issueTitle;
            document.querySelector("#asreq-modal textarea[name=issueDescription]").value = asreqVo.issueDescription;
            document.querySelector("#asreq-modal input[name=enrollDate]").value = asreqVo.enrollDate;
            document.querySelector("#asreq-modal input[name=modifyDate]").value = asreqVo.modifyDate;
            
            const receiveButton = document.querySelector("#asreq-receive-button");
            const editButton = document.querySelector("#asreq-edit-button");
            const deleteButton = document.querySelector("#asreq-delete-button");

            receiveButton.addEventListener("click", function () {
                asreqReceive(asreqVo.no);
            });

            editButton.addEventListener("click", function () {
                loadEditModal();
            });

            deleteButton.addEventListener("click", function () {
                asreqDelete(asreqVo.no);
            });

        },

        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
        }
    });
}

// 수정 모달
function loadEditModal() {

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 요청 수정";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'block';
    }
    document.querySelector("#search-button").setAttribute("type", "button");
    document.querySelector("input[name=purchaseDate]").setAttribute("type", "date");
    document.querySelector("input[name=purchaseDate]").removeAttribute("disabled");
    const warrantyYn = document.querySelector("#asreq-modal input[name=warrantyYn]").value;

    if (warrantyYn == 'Y') {
        document.querySelector("#warranty-yn").innerHTML = `<input type="radio" name="warrantyYn" value="Y" checked>Y <input type="radio" name="warrantyYn" value="N">N`;
    }
    else if (warrantyYn == 'N') {
        document.querySelector("#warranty-yn").innerHTML = `<input type="radio" name="warrantyYn" value="Y">Y <input type="radio" name="warrantyYn" value="N" checked>N`;
    }
    else {
        document.querySelector("#warranty-yn").innerHTML = `<input type="radio" name="warrantyYn" value="Y">Y <input type="radio" name="warrantyYn" value="N">N`;
    }

    document.querySelector("input[name=customerName]").removeAttribute("disabled");
    document.querySelector("input[name=customerPhone]").removeAttribute("disabled");
    document.querySelector("select[name=customerArea]").removeAttribute("disabled");
    document.querySelector("input[name=customerAdress]").removeAttribute("disabled");
    document.querySelector("input[name=preferredServiceDate]").setAttribute("type", "date");
    document.querySelector("input[name=preferredServiceDate]").removeAttribute("disabled");
    document.querySelector("input[name=issueTitle]").removeAttribute("disabled");
    document.querySelector("textarea[name=issueDescription]").removeAttribute("disabled");
    document.querySelector("#enroll-date").style.display = 'none';
    document.querySelector("#modify-date").style.display = 'none';
    document.querySelector(".button-container").innerHTML = `<input type="button" id="asreq-save-button" value="저장">`;
    
    // 저장 버튼 클릭 시 동작 
    const saveButton = document.querySelector("#asreq-save-button");
    saveButton.addEventListener("click", function () {
        asreqEditSave();
    });
}

// 수정 처리
function asreqEditSave() {

    const no = document.querySelector("#asreq-modal input[name=no]").value;
    const productNo = document.querySelector('#asreq-modal input[name=productNo]').value;
    const purchaseDateElement = document.querySelector('#asreq-modal input[name=purchaseDate]');
    const purchaseDate = purchaseDateElement ? purchaseDateElement.value : null;
    const warrantyYnElement = document.querySelector('#asreq-modal input[name=warrantyYn]:checked');
    const warrantyYn = warrantyYnElement ? warrantyYnElement.value : null;
    const customerName = document.querySelector('#asreq-modal input[name=customerName]').value;
    const customerArea = document.querySelector('#asreq-modal select[name=customerArea]').value;
    const customerAdress = document.querySelector('#asreq-modal input[name=customerAdress]').value;
    const customerPhone = document.querySelector('#asreq-modal input[name=customerPhone]').value;
    const preferredServiceDateElement = document.querySelector('#asreq-modal input[name=preferredServiceDate]');
    const preferredServiceDate = preferredServiceDateElement ? preferredServiceDateElement.value : null;
    const issueTitle = document.querySelector('#asreq-modal input[name=issueTitle]').value;
    const issueDescription = document.querySelector('#asreq-modal textarea[name=issueDescription]').value;

    if (!productNo) {
        alert("상품을 선택해주세요.");
        return;
    }
    if (!customerName) {
        alert("고객명을 입력해주세요.");
        return;
    }
    if (customerArea == "") {
        alert("고객 지역을 선택해주세요.");
        return;
    }
    if (!customerAdress) {
        alert("고객 상세주소를 입력해주세요.");
        return;
    }
    if (!customerPhone) {
        alert("고객 핸드폰번호를 입력해주세요.");
        return;
    }
    if (customerPhone.length > 11) {
        alert("고객 핸드폰번호를 11자 이하로 입력해주세요.");
        return;
    }
    if (!issueTitle) {
        alert("AS요청제목을 입력해주세요.");
        return;
    }
    if (!issueDescription) {
        alert("AS요청내용을 입력해주세요.");
        return;
    }

    const result = confirm("저장하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: '/qa/asreq/edit',
        method: 'post',
        data: {
            no,
            productNo,
            purchaseDate,
            warrantyYn,
            customerName,
            customerArea,
            customerAdress,
            customerPhone,
            preferredServiceDate,
            issueTitle,
            issueDescription
        } ,

        success: function(result) {
            if(result == 1) {
                alert("수정되었습니다.");
            }
            else {
                alert("수정실패... 관리자에게 문의해주세요.");
            }
            location.reload();
        },

        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
            location.reload();
        }
    });

}

// 접수 처리
function asreqReceive(no) {

    const result = confirm("접수하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asreq/receive",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(result) {
            if(result == 1) {
                alert("AS요청이 접수되었습니다.");
            }
            else {
                alert("접수실패... 관리자에게 문의해주세요.");
            }
            location.reload();
        },

        error: function() {
            console.log(no);
            alert("통신실패... 관리자에게 문의해주세요.");
            location.reload();
        }
    });

}

// 단건 삭제 처리
function asreqDelete(no) {
    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asreq/delete",
        method: "POST",
        data: {
            no : no 
        } ,
        success: function(result) {
            if(result == 1) {
                alert("삭제되었습니다.");
                location.reload();
            }
            else {
                alert("삭제실패... 관리자에게 문의해주세요.");
            }
            location.reload();
        },

        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
            location.reload();
        }
    });

}

// 모달 닫기
function closeModal() {

    const modal = document.querySelector("#asreq-modal");
    const closeModal = document.querySelector('#asreq-modal .modal-close');
    
    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

}

closeModal();

// 전체 선택 
function handelCheckbox(checkAll) {

    const checkBoxArr = document.querySelectorAll("input[name=listCheckbox]");

    for(let i=0; i<checkBoxArr.length; i++) {
        checkBoxArr[i].checked = checkAll.checked;
    }
}

// 다중 삭제 처리
function asreqDeleteMultiple() {
        
    const checkedArr = document.querySelectorAll("input[name=listCheckbox]:checked");
    const noArr = [];

    for(const checkBox of checkedArr) {
        const no = checkBox.parentNode.parentNode.children[1].innerText;
        noArr.push(no);
    }

    if (noArr.length == 0) {
        alert("선택된 건이 없습니다.");
        return;
    }

    const result = confirm("선택한 건을 삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asreq/delete",
        method: "POST",
        data : {
            no : noArr
        },

        success: function(result) {
            if(result > 0) {
                alert("삭제되었습니다.");
            }
            else {
                alert("삭제실패... 관리자에게 문의해주세요.");
            }
            location.reload();
        },
        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
            location.reload();
        }
    });

}
