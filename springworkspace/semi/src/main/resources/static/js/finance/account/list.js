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
    const detailContent = document.querySelector('.detail-content');
    const closeModal = document.querySelector('.detail-close');

    accountDetailModal.style.display = 'block'; //모달 표시

    closeModal.addEventListener('click', () => {
        accountDetailModal.style.display = 'none'; //모달 숨기기
    });

    $.ajax({
        url: "/finance/account/detail",
        method: "get",
        dataType: 'json',
        data: {
            no : no
        } ,
        success: function(accountVo) {
            
            console.log("AJAX 응답 데이터:", accountVo);

            if (!accountVo) {
                console.error("accountVo가 null입니다.");
                alert("계좌 정보를 가져오지 못했습니다.");
                return;
            }
        
            if (!accountVo.no || !accountVo.bankCode) {
                console.error("accountVo에 필요한 데이터가 없습니다:", accountVo);
                alert("계좌 정보를 가져오지 못했습니다.");
                return;
            }

            console.log("accountVo.area:", accountVo.area);
            console.log(document.querySelector("#account-detail select[name=area]"));

            document.querySelector("#account-detail input[name=no]").value = accountVo.no;
            document.querySelector("#account-detail input[name=bankCode]").value = accountVo.bankCode;
            document.querySelector("#account-detail input[name=bankName]").value = accountVo.bankName;
            document.querySelector("#account-detail input[name=accountNo]").value = accountVo.accountNo;
            document.querySelector("#account-detail input[name=accountName]").value = accountVo.accountName;

            const editButton = document.querySelector("#account-edit-btn");
            const deleteButton = document.querySelector("#account-delete-btn");

            editButton.addEventListener("click" , function () {
                accountEdit(accountVo.no);
            });
            deleteButton.addEventListener("click" , function () {
                accountDelete(accountVo.no);
            });
        },
        fail:function() {
            alert("통신실패 ...");
        }
    });
}

    
function accountDetailClose() {
    const accountDetailModal = document.getElementById('account-detail');
    accountDetailModal.style.display = 'none'; // 모달 숨기기
}

function accountEdit(no) {

    console.log(no);
    accountDetailClose();

    const accountEditModal = document.getElementById('account-edit');
    const editModalContent = document.querySelector('.edit-content');

    accountEditModal.style.display = 'block';//모달표시

    $.ajax({
        url: "/finance/account/edit",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(accountVo) {

            document.querySelector("#account-detail input[name=no]").value = accountVo.no;
            document.querySelector("#account-detail input[name=bankCode]").value = accountVo.bankCode;
            document.querySelector("#account-detail input[name=bankName]").value = accountVo.bankName;
            document.querySelector("#account-detail input[name=accountNo]").value = accountVo.accountNo;
            document.querySelector("#account-detail input[name=accountName]").value = accountVo.accountName;
       },

        fail:function() {
            alert("통신실패 ...")
        }
    });

}

function accountEditClose(){
    const accountEditModal = document.getElementById('account-edit');
    accountEditModal.style.display = 'none'; //모달 숨기기
}



function accountDelete(no) {
    if (!confirm("정말 삭제하시겠습니까?")) {
        return; // 사용자가 취소를 누르면 함수 종료
    }

    $.ajax({
        url: `/finance/account/delete`, // 서버의 삭제 엔드포인트
        method: "get", // HTTP DELETE 메서드 사용
        data: { no: no }, // 삭제할 no 전달
        success: function (response) {
            alert("삭제되었습니다.");
            location.reload(); // 페이지 새로고침
        },
        error: function (xhr) {
            alert("삭제에 실패했습니다.");
            console.error("오류:", xhr.responseText);
        }
    });
}