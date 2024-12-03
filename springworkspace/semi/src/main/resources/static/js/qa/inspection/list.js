// 등록 모달
function inspectionWrite() {

    // 모달 요소 가져오기
    const inspectionWriteModal = document.querySelector("#inspection-write");
    const closeModal = document.querySelector('#inspection-write .modal-close');

    inspectionWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        inspectionWriteClose();
    }, { once: true } );

    // 상품 검색 버튼 클릭 시 동작
    const searchButton = document.querySelector('#write-modal-product-search');
    searchButton.addEventListener('click', () => {
        searchProduct('inspectionWrite');
    });

    // 등록 버튼 클릭 시 동작
    const writeButton = document.querySelector('#inspection-write-btn');
    writeButton.addEventListener('click', () => {
        write();
    });

}

// 등록 처리
function write() {

    const productNo = document.querySelector('#inspection-write input[name=productNo]').value;
    const inspectionCode = document.querySelector('#inspection-write select[name=inspectionCode]').value;
    const statusCode = document.querySelector('#inspection-write select[name=statusCode]').value;
    const successYnElement = document.querySelector('#inspection-write input[name=successYn]:checked');
    const successYn = successYnElement ? successYnElement.value : null;
    const inspectionDateElement = document.querySelector('#inspection-write input[name=inspectionDate]');
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
            location.reload();
        },
        error: function() {
            alert("통신실패...");
            location.reload();
        }
    });
}

// 등록 모달 닫기 
function inspectionWriteClose() {
    const inspectionDetailModal = document.querySelector("#inspection-detail");
    const form = document.querySelector("#inspection-write-form");
    inspectionDetailModal.style.display = 'none'; // 모달 숨기기
    form.reset(); // 모달 내용 초기화
}

// 상품 검색 모달
function searchProduct(caller) {

    const searchProductModal = document.querySelector("#search-product");
    const closeModal = document.querySelector("#search-product .modal-close");

    searchProductModal.style.display = 'block'; 

    closeModal.addEventListener('click', () => {
        searchProductClose();
    } , { once: true });

    const tbodyTag = document.querySelector("#search-product table>tbody");
    tbodyTag.innerHTML = ""; 

    $.ajax({
        url : "/qa/asreq/productlist",
        method : "GET" ,
        success : function(productVoList){

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
    const selectButton = document.querySelector("#product-select-btn");
    selectButton.addEventListener('click', () => {
        selectProduct(caller);
    } , { once: true });
        
}

// 상품 선택 처리
function selectProduct(caller) {

        const radioArr = document.querySelectorAll("input[name=product-radio-btn]");
        const productNoArr = document.querySelectorAll("input[name=productNo]");

        let serialNumber = null;
        let productName = null;
        let price = null;
        let warrantyPeriod = null;
        let no = null;

        for(let i=0; i<radioArr.length; i++) {
            if (radioArr[i].checked) {
                no = radioArr[i].nextSibling.value;
                serialNumber = radioArr[i].parentNode.parentNode.children[1].innerText;
                productName = radioArr[i].parentNode.parentNode.children[2].innerText;
                price = radioArr[i].parentNode.parentNode.children[3].innerText;
                warrantyPeriod = radioArr[i].parentNode.parentNode.children[4].innerText;
            }
        }

        if (serialNumber === null) {
            alert("상품이 선택되지 않았습니다.");
        }
        
        if(caller == 'inspectionWrite') {
            searchProductClose();
            document.querySelector("#inspection-write input[name=productNo]").value = no;
            document.querySelector("#inspection-write input[name=serialNumber]").value = serialNumber;
            document.querySelector("#inspection-write input[name=productName]").value = productName;
            console.log("inspectionwrite호출");
        }
        // else if(caller == 'asreqEdit') {
        //     searchProductClose();
        //     document.querySelector("#asreq-edit input[name=productNo]").value = no;
        //     document.querySelector("#asreq-edit input[name=serialNumber]").value = serialNumber;
        //     document.querySelector("#asreq-edit input[name=productName]").value = name;

        // }

}

// 상품 검색 모달 닫기
function searchProductClose() {
    const searchProductModal = document.getElementById('search-product');
    searchProductModal.style.display = 'none'; // 모달 숨기기
}

// 상세 모달 
function inspectionDetail(no) {
    // 모달 요소 가져오기
    const inspectionDetailModal = document.querySelector('#inspection-detail');
    const modalContent = document.querySelector('#inspection-detail .modal-content');
    const closeModal = document.querySelector('#inspection-detail .modal-close');

    inspectionDetailModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        inspectionDetailClose();
    });

    $.ajax({
        url: "/qa/inspection/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(inspectionVo) {

            document.querySelector("#inspection-detail input[name=no]").value = inspectionVo.no;
            document.querySelector("#inspection-detail input[name=productNo]").value = inspectionVo.productNo;
            document.querySelector("#inspection-detail input[name=serialNumber]").value = inspectionVo.serialNumber;
            document.querySelector("#inspection-detail input[name=productName]").value = inspectionVo.productName;
            document.querySelector("#inspection-detail select[name=inspectionCode]").value = inspectionVo.inspectionCode;
            document.querySelector("#inspection-detail select[name=statusCode]").value = inspectionVo.statusCode;
            document.querySelector("#inspection-detail input[name=inspectionDate]").value = inspectionVo.inspectionDate;
            document.querySelector("#inspection-detail input[name=successYn]").value = inspectionVo.successYn;
            document.querySelector("#inspection-detail input[name=enrollDate]").value = inspectionVo.enrollDate;
            document.querySelector("#inspection-detail input[name=modifyDate]").value = inspectionVo.modifyDate;
            
            const editButton = document.querySelector("#inspection-edit-btn");
            const deleteButton = document.querySelector("#inspection-delete-btn");

            editButton.addEventListener("click", function () {
                inspectionEdit(inspectionVo.no);
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

// 상세 모달 닫기
function inspectionDetailClose() {
    const inspectionDetailModal = document.getElementById('inspection-detail');
    inspectionDetailModal.style.display = 'none'; // 모달 숨기기
}

// 수정 모달
function inspectionEdit(no) {

    inspectionDetailClose();

    // 모달 요소 가져오기
    const inspectionEditModal = document.querySelector('#inspection-edit');
    const modalContent = document.querySelector('#inspection-edit .modal-content');
    const closeModal = document.querySelector('#inspection-edit .modal-close');

    inspectionEditModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        inspectionEditModal.style.display = 'none'; // 모달 숨기기
    });

    $.ajax({
        url: "/qa/inspection/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(inspectionVo) {

            console.log(inspectionVo);

            document.querySelector("#inspection-edit input[name=no]").value = inspectionVo.no;
            document.querySelector("#inspection-edit input[name=productNo]").value = inspectionVo.productNo;
            document.querySelector("#inspection-edit input[name=serialNumber]").value = inspectionVo.serialNumber;
            document.querySelector("#inspection-edit input[name=productName]").value = inspectionVo.productName;
            document.querySelector("#inspection-edit select[name=inspectionCode]").value = inspectionVo.inspectionCode;
            document.querySelector("#inspection-edit select[name=statusCode]").value = inspectionVo.statusCode;
            document.querySelector("#inspection-edit input[name=inspectionDate]").value = inspectionVo.inspectionDate;

            const successYn = document.querySelectorAll("#inspection-edit input[name=successYn]");
            for(let i=0; i<successYn.length; i++) {
                if(successYn[i].value == inspectionVo.successYn) {
                    successYn[i].checked = true;
                }
            }
            
            const saveButton = document.querySelector("#inspection-save-btn");

            saveButton.addEventListener("click", function () {
                inspectionEditSave();
            });

        },

        error: function() {
            alert("통신실패...");
        }
    });
}

// 수정 모달 닫기
function inspectionEditClose() {
    const inspectionEditModal = document.getElementById('inspection-edit');
    inspectionEditModal.style.display = 'none'; // 모달 숨기기
}

// 수정 처리
function inspectionEditSave() {
    
    inspectionEditClose();

    const no = document.querySelector("#inspection-edit input[name=no]").value;
    const productNo = document.querySelector("#inspection-edit input[name=productNo]").value;
    const serialNumber = document.querySelector("#inspection-edit input[name=serialNumber]").value;
    const productName = document.querySelector("#inspection-edit input[name=productName]").value;
    const inspectionCode = document.querySelector("#inspection-edit select[name=inspectionCode]").value;
    const inspectionDate = document.querySelector("#inspection-edit input[name=inspectionDate]").value;
    const statusCode = document.querySelector("#inspection-edit select[name=statusCode]").value;
    const successYn = document.querySelector("#inspection-edit input[name=successYn]:checked").value;

    $.ajax({
        url: "/qa/inspection/edit",
        method: "POST",
        data: {
            no : no ,
            productNo : productNo ,
            serialNumber : serialNumber ,
            productName : productName ,
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
function inspectionDeleteMultiple() {
        
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