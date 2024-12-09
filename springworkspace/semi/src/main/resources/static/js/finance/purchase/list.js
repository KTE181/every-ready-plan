function purchaseWrite() {
    
    // 모달 요소 가져오기
    const purchaseWriteModal = document.getElementById('purchase-write');
    const closeModal = document.querySelector('.write-close');

    purchaseWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        purchaseWriteModal.style.display = 'none'; // 모달 숨기기
    });

}

//tr 클릭시 동작
function purchaseDetail(no) {

     // 모달 요소 가져오기
     const purchaseDetailModal = document.getElementById('purchase-detail');
     const detailContent = document.querySelector('.detail-content');
     const closeModal = document.querySelector('.detail-close');

     purchaseDetailModal.style.display = 'block'; // 모달 표시

     closeModal.addEventListener('click', () => {
         purchaseDetailModal.style.display = 'none'; // 모달 숨기기
     });

     $.ajax({
        url: "/finance/purchase/detail",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(purchaseVo) {
            console.log("purchaseVo.area:", purchaseVo.area);
            console.log(document.querySelector("#purchase-detail select[name=area]"));

            document.querySelector("#purchase-detail input[name=no]").value = purchaseVo.no;
            document.querySelector("#purchase-detail input[name=partnerCode]").value = purchaseVo.partnerCode;
            document.querySelector("#purchase-detail input[name=transCode]").value = purchaseVo.transCode;
            document.querySelector("#purchase-detail input[name=accountCode]").value = purchaseVo.accountCode;
            document.querySelector("#purchase-detail input[name=supplyAmount]").value = purchaseVo.supplyAmount;
            document.querySelector("#purchase-detail input[name=taxAmount]").value = purchaseVo.taxAmount;
            document.querySelector("#purchase-detail input[name=attachment]").value = purchaseVo.attachment;
            document.querySelector("#purchase-detail input[name=transDate]").value = purchaseVo.transDate;
            document.querySelector("#purchase-detail input[name=comments]").value = purchaseVo.comments;

            const editButton = document.querySelector("#purchase-edit-btn");
            const deleteButton = document.querySelector("#purchase-delete-btn");

            editButton.addEventListener("click" , function () {
                purchaseEdit(purchaseVo.no);
            });
            deleteButton.addEventListener("click" , function () {
                purchaseDelete(purchaseVo.no);
            });
        },
        fail:function() {
            alert("통신실패 ...");
        }
    });
}

    
function purchaseDetailClose() {
    const purchaseDetailModal = document.getElementById('purchase-detail');
    purchaseDetailModal.style.display = 'none'; // 모달 숨기기
}

function purchaseEdit(no) {

    console.log(no , '에디트');
    purchaseDetailClose();

    const purchaseEditModal = document.getElementById('purchase-edit');
    const editModalContent = document.querySelector('.edit-content');

    purchaseEditModal.style.display = 'block';//모달표시

    $.ajax({
        url: "/finance/purchase/edit",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(purchaseVo) {

            document.querySelector("#purchase-edit input[name=no]").value = purchaseVo.no;
            document.querySelector("#purchase-edit input[name=partnerCode]").value = purchaseVo.partnerCode;
            document.querySelector("#purchase-edit input[name=transCode]").value = purchaseVo.transCode;
            document.querySelector("#purchase-edit input[name=accountCode]").value = purchaseVo.accountCode;
            document.querySelector("#purchase-edit input[name=supplyAmount]").value = purchaseVo.supplyAmount;
            document.querySelector("#purchase-edit input[name=taxAmount]").value = purchaseVo.taxAmount;
            document.querySelector("#purchase-edit input[name=attachment]").value = purchaseVo.attachment;
            document.querySelector("#purchase-edit input[name=transDate]").value = purchaseVo.transDate;
            document.querySelector("#purchase-edit input[name=comments]").value = purchaseVo.comments;
       },

        fail:function() {
            alert("통신실패 ...")
        }
    });

}

function purchaseEditClose(){
    const purchaseEditModal = document.getElementById('purchase-edit');
    purchaseEditModal.style.display = 'none'; //모달 숨기기
}



function purchaseDelete(no) {
    if (!confirm("정말 삭제하시겠습니까?")) {
        return; // 사용자가 취소를 누르면 함수 종료
    }

    $.ajax({
        url: `/finance/purchase/delete`, // 서버의 삭제 엔드포인트
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
    const checkboxes = document.querySelectorAll('input[name="purchaseIds"]');
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
        
    });
}

// 선택된 항목 삭제
function deleteSelectedPurchases() {
    const selectedCheckboxes = document.querySelectorAll('input[name="purchaseIds"]:checked');
    const selectedIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.value);

    if (selectedIds.length === 0) {
        alert("삭제할 항목을 선택하세요.");
        return;
    }

    if (!confirm("선택된 항목을 삭제하시겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/finance/purchase/deleteMultiple",
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
