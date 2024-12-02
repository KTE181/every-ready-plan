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
            const deleteButton = documnet.querySelector("#partner-delete-btn");

            editButton.addEventListener("click" , function () {
                partnerEdit(partnerVo.no);
            });
            editButton.addEventListener("click" , function () {
                partnerDelete(partnerVo.no);
            });


        },

        fail:function() {
            alert("통신실패 ...")
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



