function expenseWrite() {
    
    // 모달 요소 가져오기
    const expenseWriteModal = document.getElementById('expense-write');
    const closeModal = document.querySelector('.write-close');

    expenseWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        expenseWriteModal.style.display = 'none'; // 모달 숨기기
    });

}

//tr 클릭시 동작
function expenseDetail(no) {

     // 모달 요소 가져오기
     const expenseDetailModal = document.getElementById('expense-detail');
     const detailContent = document.querySelector('.detail-content');
     const closeModal = document.querySelector('.detail-close');

     expenseDetailModal.style.display = 'block'; // 모달 표시

     closeModal.addEventListener('click', () => {
         expenseDetailModal.style.display = 'none'; // 모달 숨기기
     });

     $.ajax({
        url: "/finance/expense/detail",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(expenseVo) {
            console.log("expenseVo.area:", expenseVo.area);
            console.log(document.querySelector("#expense-detail select[name=area]"));

            document.querySelector("#expense-detail input[name=no]").value = expenseVo.no;
            document.querySelector("#expense-detail input[name=partnerCode]").value = expenseVo.partnerCode;
            document.querySelector("#expense-detail input[name=transCode]").value = expenseVo.transCode;
            document.querySelector("#expense-detail input[name=accountCode]").value = expenseVo.accountCode;
            document.querySelector("#expense-detail input[name=supplyAmount]").value = expenseVo.supplyAmount;
            document.querySelector("#expense-detail input[name=taxAmount]").value = expenseVo.taxAmount;
            document.querySelector("#expense-detail input[name=attachment]").value = expenseVo.attachment;
            document.querySelector("#expense-detail input[name=transDate]").value = expenseVo.transDate;
            document.querySelector("#expense-detail input[name=comments]").value = expenseVo.comments;

            const editButton = document.querySelector("#expense-edit-btn");
            const deleteButton = document.querySelector("#expense-delete-btn");

            editButton.addEventListener("click" , function () {
                expenseEdit(expenseVo.no);
            });
            deleteButton.addEventListener("click" , function () {
                expenseDelete(expenseVo.no);
            });
        },
        fail:function() {
            alert("통신실패 ...");
        }
    });
}

    
function expenseDetailClose() {
    const expenseDetailModal = document.getElementById('expense-detail');
    expenseDetailModal.style.display = 'none'; // 모달 숨기기
}

function expenseEdit(no) {

    console.log(no , '에디트');
    expenseDetailClose();

    const expenseEditModal = document.getElementById('expense-edit');
    const editModalContent = document.querySelector('.edit-content');

    expenseEditModal.style.display = 'block';//모달표시

    $.ajax({
        url: "/finance/expense/edit",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(expenseVo) {

            document.querySelector("#expense-edit input[name=no]").value = expenseVo.no;
            document.querySelector("#expense-edit input[name=partnerCode]").value = expenseVo.partnerCode;
            document.querySelector("#expense-edit input[name=transCode]").value = expenseVo.transCode;
            document.querySelector("#expense-edit input[name=accountCode]").value = expenseVo.accountCode;
            document.querySelector("#expense-edit input[name=supplyAmount]").value = expenseVo.supplyAmount;
            document.querySelector("#expense-edit input[name=taxAmount]").value = expenseVo.taxAmount;
            document.querySelector("#expense-edit input[name=attachment]").value = expenseVo.attachment;
            document.querySelector("#expense-edit input[name=transDate]").value = expenseVo.transDate;
            document.querySelector("#expense-edit input[name=comments]").value = expenseVo.comments;
       },

        fail:function() {
            alert("통신실패 ...")
        }
    });

}

function expenseEditClose(){
    const expenseEditModal = document.getElementById('expense-edit');
    expenseEditModal.style.display = 'none'; //모달 숨기기
}



function expenseDelete(no) {
    if (!confirm("정말 삭제하시겠습니까?")) {
        return; // 사용자가 취소를 누르면 함수 종료
    }

    $.ajax({
        url: `/finance/expense/delete`, // 서버의 삭제 엔드포인트
        method: "post", // HTTP POST 메서드 사용
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

// 전체 선택/해제 함수
function toggleSelectAll(selectAllCheckbox) {
    const checkboxes = document.querySelectorAll('input[name="expenseIds"]');
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
        
    });
}

// 선택된 항목 삭제
function deleteSelectedExpense() {
    const selectedCheckboxes = document.querySelectorAll('input[name="expenseIds"]:checked');
    const selectedIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.value);

    if (selectedIds.length === 0) {
        alert("삭제할 항목을 선택하세요.");
        return;
    }

    if (!confirm("선택된 항목을 삭제하시겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/finance/expense/deleteMultiple",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(selectedIds),
        success: function (response) {
            alert(response.message || "삭제되었습니다.");
            location.reload();
        },
        error: function (xhr) {
            alert("삭제에 실패했습니다.");
            console.error(xhr.responseText);
        }
    });
}