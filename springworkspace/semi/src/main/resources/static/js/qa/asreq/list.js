function asreqWrite() {
    
    // 모달 요소 가져오기
    const asreqWriteModal = document.getElementById('asreq-write');
    const closeModal = document.querySelector('.write-close');

    asreqWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        asreqWriteModal.style.display = 'none'; // 모달 숨기기
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
        success: function(data) {
            // console.log(data);
            modalContent.innerHTML = data; 

            const receiveButton = document.querySelector("#asreq-receive-button");

            receiveButton.addEventListener("click", function () {
                asreqReceive(asreqNo);
            });

        },

        fail: function() {
            alert("통신실패...");
        }
    });
}

function asreqDetailClose() {
    const asreqDetailModal = document.getElementById('asreq-detail');
    asreqDetailModal.style.display = 'none'; // 모달 숨기기
}

//수정 눌렀을 때 동작
function asreqEdit(asreqNo) {

    const asreqEditModal = document.getElementById('asreq-edit');
    const editModalContent = document.querySelector('.edit-content');
    console.log("editModalContent : " , editModalContent);
    

    asreqDetailClose();

    asreqEditModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/qa/asreq/edit",
        method: "get",
        data: {
            asreqNo : asreqNo 
        } ,
        success: function(data) {
            // console.log(data);
            editModalContent.innerHTML = data; 
        },

        fail: function() {
            alert("통신실패...");
        }
    });

}

function asreqEditClose() {
    const asreqEditModal = document.getElementById('asreq-edit');
    asreqEditModal.style.display = 'none'; // 모달 숨기기
}

//저장 눌렀을 때 동작
function asreqEditSave() {
    console.log("일단 저장js 까지는 옴??");

    const no = document.querySelector("input[name=no]");//swy
    const productNo = document.querySelector("input[name=productNo]");//swy
    const customerArea = document.querySelector("select[name=customerArea]");//swy
    const customerAdressList = document.querySelectorAll("input[name=customerAdress]");//swy
    const customerPhone = document.querySelector("input[name=customerPhone]");//swy
    const purchaseDate = document.querySelector("input[name=purchaseDate]");//swy
    const warrantyYn = document.querySelector("input[name=warrantyYn]");//swy
    const issueTitle = document.querySelector("input[name=issueTitle]");//swy
    const issueDescription = document.querySelector("textarea[name=issueDescription]");//swy;
    const preferredServiceDate = document.querySelector("input[name=preferredServiceDate]");//swy

    console.log(no);
    console.log("productNo" , productNo);
    console.log("customerArea " , customerArea);
    console.log("customerAdressList " , customerAdressList);
    console.log("customerPhone" , customerPhone);
    console.log(customerPhone.value);
    console.log(customerPhone.placeholder);
    console.log(purchaseDate);
    console.log(warrantyYn);
    console.log(issueTitle);
    console.log(issueDescription);
    console.log(preferredServiceDate);

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

    return false;
}

//접수하기 눌렀을 때 동작
function asreqReceive(no) {

    console.log(no);
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
        success: function(data) {
            alert("AS요청이 접수되었습니다.");
            location.reload();
        },

        error: function() {
            console.log(no);
            alert("통신실패...");
        }
    });

}