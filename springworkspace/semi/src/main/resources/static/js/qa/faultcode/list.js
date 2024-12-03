function faultcodeEnroll() {

    // 모달 요소 가져오기
    const faultcodeEnrollModal = document.querySelector('#faultcode-enroll');
    const modalContent = document.querySelector('#faultcode-enroll .modal-content');
    const enrollButton = document.querySelector('#faultcode-enroll-btn');
    const closeModal = document.querySelector('#faultcode-enroll .modal-close');

    faultcodeEnrollModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        faultcodeEnrollModal.style.display = 'none'; // 모달 숨기기
    });

    // 등록 버튼 클릭 시 등록 처리
    enrollButton.addEventListener('click', () => {

        const result = confirm("등록하시겠습니까?");

        if(result == false) {
            return;
        }

        const faultName = document.querySelector('#faultcode-enroll input[name=faultName]').value;

        $.ajax({
            url: "/qa/faultcode/enroll",
            method: "post",
            data : {
                faultName : faultName
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

function faultCodeDetail(no) {

    // 모달 요소 가져오기
    const faultcodeDetailModal = document.querySelector('#faultcode-detail');
    const modalContent = document.querySelector('#faultcode-detail .modal-content');
    const closeModal = document.querySelector('#faultcode-detail .modal-close');

    faultcodeDetailModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        faultcodeDetailModal.style.display = 'none'; // 모달 숨기기
    });

    $.ajax({
        url: "/qa/faultcode/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(faultcodeVo) {

            console.log(faultcodeVo);
            document.querySelector("#faultcode-detail input[name=no]").value = faultcodeVo.no;
            document.querySelector("#faultcode-detail input[name=faultName]").value = faultcodeVo.faultName;
            
            const editButton = document.querySelector("#faultcode-edit-btn");
            const deleteButton = document.querySelector("#faultcode-delete-btn");

            editButton.addEventListener("click", function () {
                faultcodeEdit(faultcodeVo.no);
            });

            deleteButton.addEventListener("click", function () {
                faultcodeDelete(faultcodeVo.no);
            });

        },

        error: function() {
            alert("통신실패...");
        }
    });

}

function faultcodeDetailClose() {
    const faultcodeDetailModal = document.getElementById('faultcode-detail');
    faultcodeDetailModal.style.display = 'none'; // 모달 숨기기
}

function faultcodeEdit(no) {

    faultcodeDetailClose();

    // 모달 요소 가져오기
    const faultcodeEditModal = document.querySelector('#faultcode-edit');
    const modalContent = document.querySelector('#faultcode-edit .modal-content');
    const closeModal = document.querySelector('#faultcode-edit .modal-close');

    faultcodeEditModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        faultcodeEditModal.style.display = 'none'; // 모달 숨기기
    });

    $.ajax({
        url: "/qa/faultcode/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(faultcodeVo) {

            document.querySelector("#faultcode-edit input[name=no]").value = faultcodeVo.no;
            document.querySelector("#faultcode-edit input[name=faultName]").value = faultcodeVo.faultName;
            
            const saveButton = document.querySelector("#faultcode-save-btn");

            saveButton.addEventListener("click", function () {
                faultcodeEditSave();
            });

        },

        error: function() {
            alert("통신실패...");
        }
    });
}

function faultcodeEditClose() {
    const faultcodeEditModal = document.getElementById('faultcode-edit');
    faultcodeEditModal.style.display = 'none'; // 모달 숨기기
}

function faultcodeEditSave() {

    faultcodeEditClose();

    const no =  document.querySelector("#faultcode-edit input[name=no]").value;
    const faultName =  document.querySelector("#faultcode-edit input[name=faultName]").value;

    $.ajax({
        url: "/qa/faultcode/edit",
        method: "post",
        data: {
            no : no,
            faultName : faultName
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

function faultcodeDelete(no) {

    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/faultcode/delete",
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