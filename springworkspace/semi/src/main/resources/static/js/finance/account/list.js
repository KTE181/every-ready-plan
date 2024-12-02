function accountWrite() {
    
    // 모달 요소 가져오기
    const accountWriteModal = document.getElementById('account-write');
    const closeModal = document.querySelector('.write-close');

    accountWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        accountWriteModal.style.display = 'none'; // 모달 숨기기
    });

}

//tr 클릭시 동작
function accountDetail(no) {

    // 모달 요소 가져오기
    const accountDetailModal = document.getElementById('account-detail');
    const modalContent = document.querySelector('.detail-content');

    console.log(accountDetailModal);
    console.log(modalContent);
    console.log("1번");
    // accountDetailModal.style.display = 'block'; // 모달 표시
//    console.log(data);
    accountDetailModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/fi/account/detail",
        method: "get",
        data: {
            no : no
        } ,
        success: function(data) {
            console.log(data);
            console.log("1231234");
            modalContent.innerHTML = data; 

//            const receiveButton = document.querySelector("#account-receive-button");

            receiveButton.addEventListener("click", function () {
                accountReceive(no);
            });

        },

        fail: function() {
            alert("통신실패...");
        }
    });
}

function accountDetailClose() {
    const accountDetailModal = document.getElementById('account-detail');
    accountDetailModal.style.display = 'none'; // 모달 숨기기
}

//수정 눌렀을 때 동작
function accountEdit(no) {

    const accountEditModal = document.getElementById('account-edit');
    const editModalContent = document.querySelector('.edit-content');
    console.log("editModalContent : " , editModalContent);
    

    accountDetailClose();

    accountEditModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/fi/account/edit",
        method: "get",
        data: {
            no : no 
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

function accountEditClose() {
    const accountEditModal = document.getElementById('account-edit');
    accountEditModal.style.display = 'none'; // 모달 숨기기
}

//저장 눌렀을 때 동작
function accountEditSave() {
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
    //     url: '/fi/account/edit',
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
    //             accountEditClose();
    //             // openaccountDetailModal(response.updatedno);
    //     },
    //     fail: function() {
    //         alert("통신실패...");
    //     }
    // });

    return false;
}

//접수하기 눌렀을 때 동작
function accountReceive(no) {

    console.log(no);
    const result = confirm("접수하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/fi/account/receive",
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
//삭제

/*function deleteBoardAjax(bno) {
    $.ajax({
        url: `/board/del1?bno=${bno}`,
        type: 'DELETE',
        success: function(result) {
            alert('삭제 성공!');
            location.href = '/board/list'; // 목록 페이지로 리다이렉트
        },
        error: function(xhr, status, error) {
            alert('삭제 실패...');
            console.error(error);
        }
    });
}
*/

function deleteBoardAjax(no) {
    $.ajax({
        url: `/fi/account/del?no=${no}`, // URL 수정
        type: 'DELETE',
        success: function (result) {
            alert('삭제 성공!');
            location.href = '/fi/account/list'; // 목록 페이지로 리다이렉트
        },
        error: function (xhr, status, error) {
            alert('삭제 실패... 서버와 통신에 실패했습니다.');
            console.error('Error:', error, 'Status:', status);
        }
    });
}