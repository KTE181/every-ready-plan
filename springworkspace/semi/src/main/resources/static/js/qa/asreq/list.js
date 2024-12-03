const writeSearchButton = document.querySelector('#asreq-write-search-btn');
writeSearchButton.addEventListener('click', () => {
    searchProduct('asreqWrite');
});

const editSearchButton = document.querySelector('#asreq-edit-search-btn');
editSearchButton.addEventListener('click', () => {
    searchProduct('asreqEdit');
});

function asreqWrite() {

    // 모달 요소 가져오기
    const asreqWriteModal = document.querySelector("#asreq-write");
    const closeModal = document.querySelector('.write-close');

    asreqWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {

        asreqWriteModal.style.display = 'none'; // 모달 숨기기

        document.querySelector("#asreq-write input[name=serialNumber]").value = "";
        document.querySelector("#asreq-write input[name=name]").value = "";

    });

}

//tr 클릭시 동작
function asreqDetail(asreqNo) {

    // 모달 요소 가져오기
    const asreqDetailModal = document.getElementById('asreq-detail');
    const modalContent = document.querySelector('.detail-content');

    console.log(asreqDetailModal);
    console.log(modalContent);

    asreqDetailModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/qa/asreq/detail",
        method: "get",
        data: {
            asreqNo : asreqNo 
        } ,
        success: function(asreqVo) {

            document.querySelector("#asreq-detail input[name=no]").value = asreqVo.no;
            document.querySelector("#asreq-detail input[name=productNo]").value = asreqVo.productNo;
            document.querySelector("#asreq-detail input[name=serialNumber]").value = asreqVo.serialNumber;
            document.querySelector("#asreq-detail input[name=productName]").value = asreqVo.productName;
            document.querySelector("#asreq-detail input[name=purchaseDate]").value = asreqVo.purchaseDate;
            document.querySelector("#asreq-detail input[name=warrantyYn]").value = asreqVo.warrantyYn;
            document.querySelector("#asreq-detail input[name=customerName]").value = asreqVo.customerName;
            document.querySelector("#asreq-detail select[name=customerArea]").value = asreqVo.customerArea;
            document.querySelector("#asreq-detail input[name=customerAdress]").value = asreqVo.customerAdress;
            document.querySelector("#asreq-detail input[name=customerPhone]").value = asreqVo.customerPhone;
            document.querySelector("#asreq-detail input[name=preferredServiceDate]").value = asreqVo.preferredServiceDate;
            document.querySelector("#asreq-detail input[name=issueTitle]").value = asreqVo.issueTitle;
            document.querySelector("#asreq-detail textarea[name=issueDescription]").value = asreqVo.issueDescription;

            const receiveButton = document.querySelector("#asreq-receive-button");
            const editButton = document.querySelector("#asreq-edit-button");
            const deleteButton = document.querySelector("#asreq-delete-button");

            receiveButton.addEventListener("click", function () {
                asreqReceive(asreqVo.no);
            });

            editButton.addEventListener("click", function () {
                asreqEdit(asreqVo.no);
            });

            deleteButton.addEventListener("click", function () {
                asreqDelete(asreqVo.no);
            });

        },

        error: function() {
            alert("통신실패...");
        }
    });
}

function asreqDetailClose() {
    const asreqDetailModal = document.getElementById('asreq-detail');
    asreqDetailModal.style.display = 'none'; // 모달 숨기기
}

//수정 눌렀을 때 동작
function asreqEdit(asreqNo, serialNumber, name) {

    asreqDetailClose();

    const asreqEditModal = document.querySelector('#asreq-edit');
    const editModalContent = document.querySelector('.edit-content');

    asreqEditModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/qa/asreq/edit",
        method: "get",
        data: {
            asreqNo : asreqNo 
        } ,
        success: function(asreqVo) {

            document.querySelector("#asreq-edit input[name=no]").value = asreqVo.no;
            document.querySelector("#asreq-edit input[name=productNo]").value = asreqVo.productNo;
            document.querySelector("#asreq-edit input[name=serialNumber]").value = asreqVo.serialNumber;
            document.querySelector("#asreq-edit input[name=productName]").value = asreqVo.productName;
            document.querySelector("#asreq-edit input[name=purchaseDate]").value = asreqVo.purchaseDate;

            const warrantyYn = document.querySelectorAll("#asreq-edit input[name=warrantyYn]");
            for(let i=0; i<warrantyYn.length; i++) {
                if(warrantyYn[i].value == asreqVo.warrantyYn) {
                    warrantyYn[i].checked = true;
                }
            }

            document.querySelector("#asreq-edit input[name=customerName]").value = asreqVo.customerName;
            document.querySelector("#asreq-edit select[name=customerArea]").value = asreqVo.customerArea;
            document.querySelector("#asreq-edit input[name=customerAdress]").value = asreqVo.customerAdress;
            document.querySelector("#asreq-edit input[name=customerPhone]").value = asreqVo.customerPhone;
            document.querySelector("#asreq-edit input[name=preferredServiceDate]").value = asreqVo.preferredServiceDate;
            document.querySelector("#asreq-edit input[name=issueTitle]").value = asreqVo.issueTitle;
            document.querySelector("#asreq-edit textarea[name=issueDescription]").value = asreqVo.issueDescription;

        },

        error: function() {
            alert("통신실패...");
        }
    });

}

function asreqEditClose() {
    const asreqEditModal = document.getElementById('asreq-edit');
    asreqEditModal.style.display = 'none'; // 모달 숨기기
}

//삭제 눌렀을 때 동작
function asreqDelete(no) {
    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asreq/delete",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(result) {
            if(result == 1) {
                alert("삭제되었습니다.");
                location.reload();
            }
            else {
                alert("오류발생...");
            }

        },

        error: function() {
            console.log(no);
            alert("통신실패...");
        }
    });

}

//접수하기 눌렀을 때 동작
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
                location.reload();
            }
            else {
                alert("오류발생...");
            }
        },

        error: function() {
            console.log(no);
            alert("통신실패...");
        }
    });

}


//저장 눌렀을 때 동작
function asreqEditSave() {

    const no = document.querySelector("#asreq-edit input[name=no]");
    const productNo = document.querySelector("#asreq-edit input[name=productNo]");
    const serialNumber = document.querySelector("#asreq-edit input[name=serialNumber]");
    const productName = document.querySelector("#asreq-edit input[name=productName]");
    const purchaseDate = document.querySelector("#asreq-edit input[name=purchaseDate]");
    const warrantyYn = document.querySelector("#asreq-edit input[name=warrantyYn]");
    const customerName = document.querySelector("#asreq-edit input[name=customerName]");
    const customerArea = document.querySelector("#asreq-edit input[name=customerArea]");
    const customerAdress = document.querySelectorAll("#asreq-edit input[name=customerAdress]");
    const customerPhone = document.querySelector("#asreq-edit input[name=customerPhone]");
    const preferredServiceDate = document.querySelector("#asreq-edit input[name=preferredServiceDate]");
    const issueTitle = document.querySelector("#asreq-edit input[name=issueTitle]");
    const issueDescription = document.querySelector("#asreq-edit textarea[name=issueDescription]");

    console.log(no);
    console.log(productNo);
    console.log(serialNumber);
    console.log(productName);
    console.log(purchaseDate);
    console.log(warrantyYn);
    console.log(customerName);
    console.log(customerArea);
    console.log(customerAdress);
    console.log(customerPhone);
    console.log(preferredServiceDate);
    console.log(issueTitle);
    console.log(issueDescription);

    // $.ajax({
    //     url: '/qa/asreq/edit',
    //     method: 'post',
    //     data: {
    //         no,
    //         productNo,
    //         customerArea,
    //         customerAdress,
    //         customerPhone,
    //         purchaseDate,
    //         warrantyYn,
    //         issueTitle,
    //         issueDescription,
    //         preferredServiceDate
    //     } ,
    //     success: function(response) {
    //             asreqEditClose();
    //             // openAsreqDetailModal(response.updatedAsreqNo);
    //     },
    //     fail: function() {
    //         alert("통신실패...");
    //     }
    // });

    // return false;
}

function searchProduct(caller) {

    currentCaller = caller;

    const searchProductModal = document.getElementById('search-product');
    const closeModal = document.querySelector('.modal-close');

    searchProductModal.style.display = 'block'; // 모달 표시

    closeModal.addEventListener('click', () => {
        searchProductModal.style.display = 'none'; // 모달 숨기기
    });

    const tbodyTag = document.querySelector("#search-product table>tbody");

    $.ajax({
        url : "/qa/asreq/productlist",
        method : "GET" ,
        success : function(productVoList){

            console.log(productVoList)

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

}

let currentCaller = null;

const selectButton = document.querySelector("#product-select-btn");

selectButton.addEventListener('click', () => {
    
    const radioArr = document.querySelectorAll("input[name=product-radio-btn]");
    const productNoArr = document.querySelectorAll("input[name=productNo]");

    let serialNumber = null;
    let name = null;
    let price = null;
    let warrantyPeriod = null;
    let no = null;

    for(let i=0; i<radioArr.length; i++) {
        if (radioArr[i].checked) {
            no = radioArr[i].nextSibling.value;
            serialNumber = radioArr[i].parentNode.parentNode.children[1].innerText;
            name = radioArr[i].parentNode.parentNode.children[2].innerText;
            price = radioArr[i].parentNode.parentNode.children[3].innerText;
            warrantyPeriod = radioArr[i].parentNode.parentNode.children[4].innerText;
        }
    }

    if (serialNumber === null) {
        alert("상품이 선택되지 않았습니다.");
    }
    
    if(currentCaller == 'asreqWrite') {
        searchProductClose();
        document.querySelector("#asreq-write input[name=productNo]").value = no;
        document.querySelector("#asreq-write input[name=serialNumber]").value = serialNumber;
        document.querySelector("#asreq-write input[name=name]").value = name;
        console.log("asreqWrite호출");
    }
    else if(currentCaller == 'asreqEdit') {
        searchProductClose();
        document.querySelector("#asreq-edit input[name=productNo]").value = no;
        document.querySelector("#asreq-edit input[name=serialNumber]").value = serialNumber;
        document.querySelector("#asreq-edit input[name=productName]").value = name;

    }

});

function searchProductClose() {
    const searchProductModal = document.getElementById('search-product');
    searchProductModal.style.display = 'none'; // 모달 숨기기
}