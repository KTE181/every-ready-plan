function accountWrite() {
    
    // 모달 요소 가져오기
    const accountWriteModal = document.getElementById('account-write');
    const closeModal = document.querySelector('.write-close');

    accountWriteModal.style.display = 'block'; // 모달 표시

    // if(accountNo != null) {
    //     document.querySelector("#account-write input[name=no]").value = empNo;
    // }

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
            

            const noInput = document.querySelector("#account-detail input[name=no]");
if (noInput) {
    noInput.value = accountVo.no;
} else {
    console.error("DOM 요소를 찾을 수 없습니다: input[name=no]");
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
    accountEditModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/finance/account/edit",
        method: "get",
        dataType: 'json',
        data: {
            no: no
        },
        success: function (accountVo) {
            // 서버에서 받은 데이터 설정
            document.querySelector("#account-edit input[name=no]").value = accountVo.no;
            document.querySelector("#account-edit input[name=accountNo]").value = accountVo.accountNo;
            document.querySelector("#account-edit input[name=accountName]").value = accountVo.accountName;
            // document.querySelector("#account-edit input[name=bankCode]").value = accountVo.bankCode;

            // select 요소에서 bankCode를 설정
            const bankCodeSelect = document.querySelector("#account-edit select[id=bankCode1]");
            bankCodeSelect.value = accountVo.bankCode;

            // hidden 필드 업데이트
            document.querySelector("#account-edit input[name=bankCode]").value = accountVo.bankCode;
        },
        fail: function () {
            alert("통신실패 ...");
        }
    });

    // select 요소의 변경 이벤트 처리
    document.querySelector("#account-edit select[id=bankCode1]").addEventListener('change', function () {
        const selectedBankCode = this.value;
        document.querySelector("#account-edit input[name=bankCode]").value = selectedBankCode;
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
        method: "DELETE", // HTTP DELETE 메서드 사용
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

function accountDeleteMultiple() {
    const selectedIds = Array.from(document.querySelectorAll('.selectItem:checked'))
        .map(item => item.value);

    if (selectedIds.length === 0) {
        alert('삭제할 항목을 선택하세요.');
        return;
    }

    if (confirm('정말 삭제하시겠습니까?')) {
        $.ajax({
            url: '/finance/account/deleteMultiple',
            method: 'POST',
            traditional: true,
            data: { ids: selectedIds },
            success: function(response) {
                alert(response);
                location.reload();
            },
            error: function(xhr) {
                alert('삭제 실패: ' + xhr.responseText);
            }
        });
    }
}