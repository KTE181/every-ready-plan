function saleWrite() {
    
    // 모달 요소 가져오기
    const saleWriteModal = document.getElementById('sale-write');
    const closeModal = document.querySelector('.write-close');

    saleWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        saleWriteModal.style.display = 'none'; // 모달 숨기기
    });

}

//tr 클릭시 동작
function saleDetail(no) {

     // 모달 요소 가져오기
     const saleDetailModal = document.getElementById('sale-detail');
     const detailContent = document.querySelector('.detail-content');
     const closeModal = document.querySelector('.detail-close');

     saleDetailModal.style.display = 'block'; // 모달 표시

     closeModal.addEventListener('click', () => {
         saleDetailModal.style.display = 'none'; // 모달 숨기기
     });

     $.ajax({
        url: "/finance/sale/detail",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(saleVo) {
            console.log("saleVo.area:", saleVo.area);
            console.log(document.querySelector("#sale-detail select[name=area]"));

            document.querySelector("#sale-detail input[name=no]").value = saleVo.no;
            document.querySelector("#sale-detail input[name=partnerCode]").value = saleVo.partnerCode;
            document.querySelector("#sale-detail input[name=transCode]").value = saleVo.transCode;
            document.querySelector("#sale-detail input[name=accountCode]").value = saleVo.accountCode;
            document.querySelector("#sale-detail input[name=supplyAmount]").value = saleVo.supplyAmount;
            document.querySelector("#sale-detail input[name=taxAmount]").value = saleVo.taxAmount;
            document.querySelector("#sale-detail input[name=attachment]").value = saleVo.attachment;
            document.querySelector("#sale-detail input[name=transDate]").value = saleVo.transDate;
            document.querySelector("#sale-detail input[name=comments]").value = saleVo.comments;

            const editButton = document.querySelector("#sale-edit-btn");
            const deleteButton = document.querySelector("#sale-delete-btn");

            editButton.addEventListener("click" , function () {
                saleEdit(saleVo.no);
            });
            deleteButton.addEventListener("click" , function () {
                saleDelete(saleVo.no);
            });
        },
        fail:function() {
            alert("통신실패 ...");
        }
    });
}

    
function saleDetailClose() {
    const saleDetailModal = document.getElementById('sale-detail');
    saleDetailModal.style.display = 'none'; // 모달 숨기기
}

function saleEdit(no) {

    console.log(no , '에디트');
    saleDetailClose();

    const saleEditModal = document.getElementById('sale-edit');
    const editModalContent = document.querySelector('.edit-content');

    saleEditModal.style.display = 'block';//모달표시

    $.ajax({
        url: "/finance/sale/edit",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(saleVo) {

            document.querySelector("#sale-edit input[name=no]").value = saleVo.no;
            document.querySelector("#sale-edit input[name=partnerCode]").value = saleVo.partnerCode;
            document.querySelector("#sale-edit input[name=transCode]").value = saleVo.transCode;
            document.querySelector("#sale-edit input[name=accountCode]").value = saleVo.accountCode;
            document.querySelector("#sale-edit input[name=supplyAmount]").value = saleVo.supplyAmount;
            document.querySelector("#sale-edit input[name=taxAmount]").value = saleVo.taxAmount;
            document.querySelector("#sale-edit input[name=attachment]").value = saleVo.attachment;
            document.querySelector("#sale-edit input[name=transDate]").value = saleVo.transDate;
            document.querySelector("#sale-edit input[name=comments]").value = saleVo.comments;
       },

        fail:function() {
            alert("통신실패 ...")
        }
    });

}

function saleEditClose(){
    const saleEditModal = document.getElementById('sale-edit');
    saleEditModal.style.display = 'none'; //모달 숨기기
}



function saleDelete(no) {
    if (!confirm("정말 삭제하시겠습니까?")) {
        return; // 사용자가 취소를 누르면 함수 종료
    }

    $.ajax({
        url: `/finance/sale/delete`, // 서버의 삭제 엔드포인트
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