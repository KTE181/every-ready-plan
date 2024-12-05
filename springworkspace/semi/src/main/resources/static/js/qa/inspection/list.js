// 등록 모달
function loadWriteModal() {

    const modal = document.querySelector("#inspection-modal");
    const modalForm = document.querySelector("#inspection-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "품질 검사 등록";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'block';
    }
    document.querySelector("#search-button").setAttribute("type", "button");
    document.querySelector("select[name=inspectionCode]").removeAttribute("disabled");
    document.querySelector("select[name=statusCode]").removeAttribute("disabled");
    document.querySelector("#inspection-date").innerHTML = `<label>검사일자</label><input type="date" name="inspectionDate">`
    document.querySelector("#success-yn").innerHTML = `<input type="radio" name="successYn" value="P">PASS <input type="radio" name="successYn" value="F">FAIL`;
    document.querySelector("#enroll-date").style.display = 'none';
    document.querySelector("#modify-date").style.display = 'none';
    document.querySelector(".button-container").innerHTML = `<input type="button" id="inspection-write-button" value="등록">`;

    // 등록 버튼 클릭 시 동작 
    const writeButton = document.querySelector("#inspection-write-button");
    writeButton.addEventListener("click", function () {
        write();
    });

}

// 등록 처리
function write() {

    const productNo = document.querySelector('#inspection-modal input[name=productNo]').value;
    const inspectionCode = document.querySelector('#inspection-modal select[name=inspectionCode]').value;
    const statusCode = document.querySelector('#inspection-modal select[name=statusCode]').value;
    const successYnElement = document.querySelector('#inspection-modal input[name=successYn]:checked');
    const successYn = successYnElement ? successYnElement.value : null;
    const inspectionDateElement = document.querySelector('#inspection-modal input[name=inspectionDate]');
    const inspectionDate = inspectionDateElement ? inspectionDateElement.value : null;

    if (!productNo) {
        alert("상품을 선택하세요.");
        return;
    }
    if (inspectionCode == "") {
        alert("검사유형을 선택하세요.");
        return;
    }
    if (statusCode == "") {
        alert("진행상태코드를 선택하세요.");
        return;
    }

    const result = confirm("등록하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/inspection/write",
        method: "POST",
        data : {
            productNo : productNo ,
            inspectionCode : inspectionCode , 
            statusCode : statusCode ,
            successYn : successYn ,
            inspectionDate : inspectionDate 
        },

        success: function(result) {
            if(result == 1) {
                alert("등록되었습니다.");
            }
            else {
                alert("등록실패...");
            }
            location.href = "/qa/inspection/list";
        },
        error: function() {
            alert("통신실패...");
            location.href = "/qa/inspection/list";
        }
    });
}

// 상품 검색 모달
function productList() {

    const productModal = document.querySelector("#product-modal");
    const closeModal = document.querySelector("#product-modal .modal-close");

    productModal.style.display = 'block'; 

    closeModal.addEventListener('click', () => {
        productModal.style.display = 'none';
    } , { once: true });

    const tbodyTag = document.querySelector("#product-modal table>tbody");

    $.ajax({
        url : "/qa/asreq/productlist",
        method : "GET" ,
        success : function(productVoList){

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
                tdTag1.innerText = vo.serialNumber;
                trTag.appendChild(tdTag1);

                const tdTag2 = document.createElement("td");
                tdTag2.innerText = vo.name;
                trTag.appendChild(tdTag2);

                const tdTag3 = document.createElement("td");
                tdTag3.innerText = vo.price;
                trTag.appendChild(tdTag3);

                const tdTag4 = document.createElement("td");
                tdTag4.innerText = vo.warrantyPeriod;
                trTag.appendChild(tdTag4);
            }
        } , 
        error : function(){
            alert("조회 실패...")
        }
    })

    // 상품 선택 시 동작
    const selectButton = document.querySelector("#product-select-button");
    selectButton.addEventListener('click', () => {

        const selectedProduct = document.querySelector("#product-modal input[type=radio]:checked");
        if (selectedProduct === null) {
            alert("상품이 선택되지 않았습니다.");
            return;
        }
        
        selectProduct(selectedProduct);
        productModal.style.display = 'none';

    });

}

// 상품 선택 처리
function selectProduct(selectedProduct) {

    const no = selectedProduct.nextSibling.value;
    const serialNumber = selectedProduct.parentNode.parentNode.children[1].innerText;
    const productName = selectedProduct.parentNode.parentNode.children[2].innerText;
    const price = selectedProduct.parentNode.parentNode.children[3].innerText;
    const warrantyPeriod = selectedProduct.parentNode.parentNode.children[4].innerText;

    document.querySelector("#inspection-modal input[name=productNo]").value = no;
    document.querySelector("#inspection-modal input[name=serialNumber]").value = serialNumber;
    document.querySelector("#inspection-modal input[name=productName]").value = productName;

}

// 상세 모달 
function loadDetailModal(no) {

    const modal = document.querySelector("#inspection-modal");
    const modalForm = document.querySelector("#inspection-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "품질 검사 상세";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'none';
    }
    document.querySelector("#search-button").setAttribute("type", "hidden");
    document.querySelector("select[name=inspectionCode]").setAttribute("disabled", "true");
    document.querySelector("select[name=statusCode]").setAttribute("disabled", "true");
    document.querySelector("#inspection-date").innerHTML = `<label>검사일자</label><input type="text" name="inspectionDate" disabled>`
    document.querySelector("#success-yn").innerHTML = `<input type="text" name="successYn" disabled>`;
    document.querySelector("#enroll-date").style.display = 'flex';
    document.querySelector("#modify-date").style.display = 'flex';
    document.querySelector(".button-container").innerHTML = 
        `<input type="button" id="inspection-edit-button" value="수정">
        <input type="button" id="inspection-delete-button" value="삭제">`;

    $.ajax({
        url: "/qa/inspection/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(inspectionVo) {

            document.querySelector("#inspection-modal input[name=no]").value = inspectionVo.no;
            document.querySelector("#inspection-modal input[name=productNo]").value = inspectionVo.productNo;
            document.querySelector("#inspection-modal input[name=serialNumber]").value = inspectionVo.serialNumber;
            document.querySelector("#inspection-modal input[name=productName]").value = inspectionVo.productName;
            document.querySelector("#inspection-modal select[name=inspectionCode]").value = inspectionVo.inspectionCode;
            document.querySelector("#inspection-modal select[name=statusCode]").value = inspectionVo.statusCode;
            document.querySelector("#inspection-modal input[name=inspectionDate]").value = inspectionVo.inspectionDate;
            document.querySelector("#inspection-modal input[name=successYn]").value = inspectionVo.successYn;
            document.querySelector("#inspection-modal input[name=enrollDate]").value = inspectionVo.enrollDate;
            document.querySelector("#inspection-modal input[name=modifyDate]").value = inspectionVo.modifyDate;
            
            const editButton = document.querySelector("#inspection-edit-button");
            const deleteButton = document.querySelector("#inspection-delete-button");

            editButton.addEventListener("click", function () {
                loadEditModal();
            });

            deleteButton.addEventListener("click", function () {
                inspectionDelete(inspectionVo.no);
            });

        },

        error: function() {
            alert("통신실패...");
        }
    });

}

// 수정 모달
function loadEditModal() {

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "품질 검사 수정";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'block';
    }
    document.querySelector("#search-button").setAttribute("type", "button");
    document.querySelector("select[name=inspectionCode]").removeAttribute("disabled");
    document.querySelector("select[name=statusCode]").removeAttribute("disabled");
    document.querySelector("#inspection-modal input[name=inspectionDate]").setAttribute("type", "date");
    document.querySelector("#inspection-modal input[name=inspectionDate]").removeAttribute("disabled");

    const successYn = document.querySelector("#inspection-modal input[name=successYn]").value;

    if (successYn == 'PASS') {
        document.querySelector("#success-yn").innerHTML = `<input type="radio" name="successYn" value="P" checked>PASS <input type="radio" name="successYn" value="F">FAIL`;
    }
    else if (successYn == 'FAIL') {
        document.querySelector("#success-yn").innerHTML = `<input type="radio" name="successYn" value="P">PASS <input type="radio" name="successYn" value="F" checked>FAIL`;
    }
    else {
        document.querySelector("#success-yn").innerHTML = `<input type="radio" name="successYn" value="P">PASS <input type="radio" name="successYn" value="F">FAIL`;
    }

    document.querySelector("#enroll-date").style.display = 'none';
    document.querySelector("#modify-date").style.display = 'none';
    document.querySelector(".button-container").innerHTML = 
        `<input type="button" id="inspection-save-button" value="저장">`;


    // 저장 버튼 클릭 시 동작 
    const saveButton = document.querySelector("#inspection-save-button");
    saveButton.addEventListener("click", function () {
        inspectionEditSave();
    });

}

// 수정 처리
function inspectionEditSave() {

    const no = document.querySelector('#inspection-modal input[name=no]').value;
    const productNo = document.querySelector('#inspection-modal input[name=productNo]').value;
    const inspectionCode = document.querySelector('#inspection-modal select[name=inspectionCode]').value;
    const statusCode = document.querySelector('#inspection-modal select[name=statusCode]').value;
    const successYnElement = document.querySelector('#inspection-modal input[name=successYn]:checked');
    const successYn = successYnElement ? successYnElement.value : null;
    const inspectionDateElement = document.querySelector('#inspection-modal input[name=inspectionDate]');
    const inspectionDate = inspectionDateElement ? inspectionDateElement.value : null;

    if (!productNo) {
        alert("상품을 선택하세요.");
        return;
    }
    if (inspectionCode == "") {
        alert("검사유형을 선택하세요.");
        return;
    }
    if (statusCode == "") {
        alert("진행상태코드를 선택하세요.");
        return;
    }

    const result = confirm("저장하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/inspection/edit",
        method: "POST",
        data: {
            no : no ,
            productNo : productNo ,
            inspectionCode : inspectionCode ,
            statusCode : statusCode ,
            inspectionDate : inspectionDate ,
            successYn : successYn 
        } ,
        success: function(result) {

            if(result == 1) {
                alert("수정되었습니다.");
            }
            else {
                alert("수정실패...");
            }
            location.reload();
        },

        error: function() {
            alert("통신실패...");
            location.reload();
        }
    });
}

// 단건 삭제 처리
function inspectionDelete(no) {

    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/inspection/delete",
        method: "POST",
        data : {
            no : no
        },

        success: function(result) {
            if(result == 1) {
                alert("삭제되었습니다.");
            }
            else {
                alert("삭제실패...");
            }

            location.reload();

        },
        error: function() {
            alert("통신실패...");
        }
    });
}

// 전체 선택 
function handelCheckbox(checkAll) {

        const checkBoxArr = document.querySelectorAll("input[name=listCheckbox]");

        for(let i=0; i<checkBoxArr.length; i++) {
            checkBoxArr[i].checked = checkAll.checked;
        }
}

// 다중 삭제 처리
function deleteMultiple() {
        
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
        url: "/qa/inspection/delete",
        method: "POST",
        data : {
            no : noArr
        },

        success: function(result) {
            if(result > 0) {
                alert("삭제되었습니다.");
            }
            else {
                alert("삭제실패...");
            }

            location.reload();

        },
        error: function() {
            alert("통신실패...");
        }
    });

}

// 모달 닫기
function closeModal() {

    const modal = document.querySelector("#inspection-modal");
    const closeModal = document.querySelector('#inspection-modal .modal-close');
    
    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

}

closeModal();

// searchType 값에 따라서 input 타입 변경
function handleSearchType(x) {

    const searchValueTag = document.querySelector("input[name=searchValue]");

    if(x.value == "productName") {
        searchValueTag.setAttribute("type", "search");
    } 
    else{
        searchValueTag.setAttribute("type", "number");
    }

}

// 새로고침 해도 input 타입 유지
document.addEventListener("DOMContentLoaded", () => {
    const searchTypeSelect = document.querySelector("select[name=searchType]");
    handleSearchType(searchTypeSelect); 
});