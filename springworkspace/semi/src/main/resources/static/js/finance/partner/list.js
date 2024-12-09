function partnerWrite() {
    
    // 모달 요소 가져오기
    const partnerWriteModal = document.getElementById('partner-write');
    const closeModal = document.querySelector('.write-close');

    partnerWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        partnerWriteModal.style.display = 'none'; // 모달 숨기기
    });

}

//tr 클릭시 동작
function partnerDetail(no) {

     // 모달 요소 가져오기
     const partnerDetailModal = document.getElementById('partner-detail');
     const detailContent = document.querySelector('.detail-content');
     const closeModal = document.querySelector('.detail-close');

     partnerDetailModal.style.display = 'block'; // 모달 표시

     closeModal.addEventListener('click', () => {
         partnerDetailModal.style.display = 'none'; // 모달 숨기기
     });

     $.ajax({
        url: "/finance/partner/detail",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(partnerVo) {
            console.log("partnerVo.area:", partnerVo.area);
            console.log(document.querySelector("#partner-detail select[name=area]"));

            document.querySelector("#partner-detail input[name=no]").value = partnerVo.no;
            document.querySelector("#partner-detail input[name=businessCode]").value = partnerVo.businessCode;
            document.querySelector("#partner-detail input[name=name]").value = partnerVo.name;
            document.querySelector("#partner-detail input[name=bizRegistNo]").value = partnerVo.bizRegistNo;
            document.querySelector("#partner-detail input[name=ceoName]").value = partnerVo.ceoName;
            document.querySelector("#partner-detail input[name=address]").value = partnerVo.address;

            const editButton = document.querySelector("#partner-edit-btn");
            const deleteButton = document.querySelector("#partner-delete-btn");

            editButton.addEventListener("click" , function () {
                partnerEdit(partnerVo.no);
            });
            deleteButton.addEventListener("click" , function () {
                partnerDelete(partnerVo.no);
            });
        },
        fail:function() {
            alert("통신실패 ...");
        }
    });
}

    
function partnerDetailClose() {
    const partnerDetailModal = document.getElementById('partner-detail');
    partnerDetailModal.style.display = 'none'; // 모달 숨기기
}

function partnerEdit(no) {

    console.log(no);
    partnerDetailClose();

    const partnerEditModal = document.getElementById('partner-edit');
    const editModalContent = document.querySelector('.edit-content');

    partnerEditModal.style.display = 'block';//모달표시

    $.ajax({
        url: "/finance/partner/edit",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(partnerVo) {

            document.querySelector("#partner-edit input[name=no]").value = partnerVo.no;
            document.querySelector("#partner-edit input[name=businessCode]").value = partnerVo.businessCode;
            document.querySelector("#partner-edit input[name=name]").value = partnerVo.name;
            document.querySelector("#partner-edit input[name=bizRegistNo]").value = partnerVo.bizRegistNo;
            document.querySelector("#partner-edit input[name=ceoName]").value = partnerVo.ceoName;
            document.querySelector("#partner-edit input[name=address]").value = partnerVo.address;

       },

        fail:function() {
            alert("통신실패 ...")
        }
    });

}

function partnerEditClose(){
    const partnerEditModal = document.getElementById('partner-edit');
    partnerEditModal.style.display = 'none'; //모달 숨기기
}



function partnerDelete(no) {
    if (!confirm("정말 삭제하시겠습니까?")) {
        return; // 사용자가 취소를 누르면 함수 종료
    }

    $.ajax({
        url: `/finance/partner/delete`, // 서버의 삭제 엔드포인트
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

// 전체 선택/해제 함수
function toggleSelectAll(selectAllCheckbox) {
    const checkboxes = document.querySelectorAll('input[name="partnerIds"]');
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
        
    });
}

// 선택된 항목 삭제
function deleteSelectedPartner() {
    const selectedCheckboxes = document.querySelectorAll('input[name="partnerIds"]:checked');
    const selectedIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.value);

    if (selectedIds.length === 0) {
        alert("삭제할 항목을 선택하세요.");
        return;
    }

    if (!confirm("선택된 항목을 삭제하시겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/finance/partner/deleteMultiple",
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

