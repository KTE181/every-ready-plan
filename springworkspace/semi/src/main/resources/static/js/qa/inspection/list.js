function inspectionWrite() {

    // 모달 요소 가져오기
    const inspectionWriteModal = document.querySelector("#inspection-write");
    const closeModal = document.querySelector('.modal-close');

    inspectionWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {

        inspectionWriteModal.style.display = 'none'; // 모달 숨기기

    });

    // 등록 버튼 클릭 시 등록 처리
    const writeButton = document.querySelector('#inspection-write-btn');

    writeButton.addEventListener('click', () => {

        const result = confirm("등록하시겠습니까?");

        if(result == false) {
            return;
        }

        const productNo = document.querySelector('#inspection-write input[name=productNo]').value;
        const inspectionCode = document.querySelector('#inspection-write select[name=inspectionCode]').value;
        const statusCode = document.querySelector('#inspection-write select[name=statusCode]').value;
        const successYn = document.querySelector('#inspection-write input[name=successYn]:checked').value;
        const inspectionDate = document.querySelector('#inspection-write input[name=inspectionDate]').value;

        $.ajax({
            url: "/qa/inspection/write",
            method: "POST",
            data : {
                productNo : productNo ,
                inspectionCode : inspectionCode , 
                statusCode : statusCode ,
                successYn : successYn ,
                inspectionDate : inspectionDate 
            },

            success: function(result) {
                if(result == 1) {
                    alert("등록되었습니다.");
                }
                else {
                    alert("등록실패...");
                }

                location.reload();

            },
            error: function() {
                alert("통신실패...");
            }
        });

    });
}

function inspectionDetail(no) {
    // 모달 요소 가져오기
    const inspectionDetailModal = document.querySelector('#inspection-detail');
    const modalContent = document.querySelector('#inspection-detail .modal-content');
    const closeModal = document.querySelector('#inspection-detail .modal-close');

    inspectionDetailModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        inspectionDetailModal.style.display = 'none'; // 모달 숨기기
    });

    $.ajax({
        url: "/qa/inspection/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(inspectionVo) {

            document.querySelector("#inspection-detail input[name=no]").value = inspectionVo.no;
            document.querySelector("#inspection-detail input[name=productNo]").value = inspectionVo.productNo;
            document.querySelector("#inspection-detail input[name=serialNumber]").value = inspectionVo.serialNumber;
            document.querySelector("#inspection-detail input[name=productName]").value = inspectionVo.productName;
            document.querySelector("#inspection-detail select[name=inspectionCode]").value = inspectionVo.inspectionCode;
            document.querySelector("#inspection-detail select[name=statusCode]").value = inspectionVo.statusCode;
            document.querySelector("#inspection-detail input[name=inspectionDate]").value = inspectionVo.inspectionDate;
            document.querySelector("#inspection-detail input[name=successYn]").value = inspectionVo.successYn;
            
            const editButton = document.querySelector("#inspection-edit-btn");
            const deleteButton = document.querySelector("#inspection-delete-btn");

            editButton.addEventListener("click", function () {
                inspectionEdit(inspectionVo.no);
            });

            deleteButton.addEventListener("click", function () {
                inspectionDelete(inspectionVo.no);
            });

        },

        error: function() {
            alert("통신실패...");
        }
    });

}

function inspectionDetailClose() {
    const inspectionDetailModal = document.getElementById('inspection-detail');
    inspectionDetailModal.style.display = 'none'; // 모달 숨기기
}

function inspectionEdit(no) {

    inspectionDetailClose();

    // 모달 요소 가져오기
    const inspectionEditModal = document.querySelector('#inspection-edit');
    const modalContent = document.querySelector('#inspection-edit .modal-content');
    const closeModal = document.querySelector('#inspection-edit .modal-close');

    inspectionEditModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        inspectionEditModal.style.display = 'none'; // 모달 숨기기
    });

    $.ajax({
        url: "/qa/inspection/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(inspectionVo) {

            console.log(inspectionVo);

            document.querySelector("#inspection-edit input[name=no]").value = inspectionVo.no;
            document.querySelector("#inspection-edit input[name=productNo]").value = inspectionVo.productNo;
            document.querySelector("#inspection-edit input[name=serialNumber]").value = inspectionVo.serialNumber;
            document.querySelector("#inspection-edit input[name=productName]").value = inspectionVo.productName;
            document.querySelector("#inspection-edit select[name=inspectionCode]").value = inspectionVo.inspectionCode;
            document.querySelector("#inspection-edit select[name=statusCode]").value = inspectionVo.statusCode;
            document.querySelector("#inspection-edit input[name=inspectionDate]").value = inspectionVo.inspectionDate;

            const successYn = document.querySelectorAll("#inspection-edit input[name=successYn]");
            for(let i=0; i<successYn.length; i++) {
                if(successYn[i].value == inspectionVo.successYn) {
                    successYn[i].checked = true;
                }
            }
            
            const saveButton = document.querySelector("#inspection-save-btn");

            saveButton.addEventListener("click", function () {
                inspectionEditSave();
            });

        },

        error: function() {
            alert("통신실패...");
        }
    });
}

function inspectionEditClose() {
    const inspectionEditModal = document.getElementById('inspection-edit');
    inspectionEditModal.style.display = 'none'; // 모달 숨기기
}


function inspectionEditSave() {
    
    inspectionEditClose();

    const no = document.querySelector("#inspection-edit input[name=no]").value;
    const productNo = document.querySelector("#inspection-edit input[name=productNo]").value;
    const serialNumber = document.querySelector("#inspection-edit input[name=serialNumber]").value;
    const productName = document.querySelector("#inspection-edit input[name=productName]").value;
    const inspectionCode = document.querySelector("#inspection-edit select[name=inspectionCode]").value;
    const inspectionDate = document.querySelector("#inspection-edit input[name=inspectionDate]").value;
    const statusCode = document.querySelector("#inspection-edit select[name=statusCode]").value;
    const successYn = document.querySelector("#inspection-edit input[name=successYn]:checked").value;

    $.ajax({
        url: "/qa/inspection/edit",
        method: "POST",
        data: {
            no : no ,
            productNo : productNo ,
            serialNumber : serialNumber ,
            productName : productName ,
            inspectionCode : inspectionCode ,
            statusCode : statusCode ,
            inspectionDate : inspectionDate ,
            successYn : successYn 
        } ,
        success: function(result) {

            if(result == 1) {
                alert("수정되었습니다.");
            }
            else {
                alert("수정실패...");
            }

            location.reload();

        },

        error: function() {
            alert("통신실패...");
        }
    });
}

function inspectionDelete(no) {

    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/inspection/delete",
        method: "get",
        data : {
            no : no
        },

        success: function(result) {
            if(result == 1) {
                alert("삭제되었습니다.");
            }
            else {
                alert("등록실패...");
            }

            location.reload();

        },
        error: function() {
            alert("통신실패...");
        }
    });
}
