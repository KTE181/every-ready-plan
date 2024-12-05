// 등록 모달
function loadWriteModal() {

    const modal = document.querySelector("#faultcode-modal");
    const modalForm = document.querySelector("#faultcode-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "고장 코드 등록";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'block';
    }
    document.querySelector("#fault-code").style.display = 'none';
    document.querySelector("input[name=faultName]").removeAttribute("disabled");
    document.querySelector(".button-container").innerHTML = `<input type="button" id="faultcode-write-button" value="등록">`;

    // 등록 버튼 클릭 시 동작
    const writeButton = document.querySelector("#faultcode-write-button");
    writeButton.addEventListener("click", function () {
        write();
    });

}

// 등록 처리 
function write() {

    const faultName = document.querySelector('#faultcode-modal input[name=faultName]').value;

    if (!faultName) {
        alert("고장유형명을 입력해주세요.");
        return;
    }

    const result = confirm("등록하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/faultcode/write",
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
            location.href = "/qa/faultcode/list";
        },

        error: function() {
            alert("통신실패...");
            location.href = "/qa/faultcode/list";
        }
    });

}

// 상세 모달
function loadDetailModal(no) {

    const modal = document.querySelector("#faultcode-modal");
    const modalForm = document.querySelector("#faultcode-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "고장 코드 상세";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'none';
    }

    document.querySelector("#fault-code").style.display = 'flex';
    document.querySelector("#faultcode-modal input[name=no]").setAttribute("disabled", "true");
    document.querySelector("input[name=faultName]").setAttribute("disabled", "true");
    document.querySelector(".button-container").innerHTML = 
        `<input type="button" id="faultcode-edit-button" value="수정">
        <input type="button" id="faultcode-delete-button" value="삭제">`;


    $.ajax({
        url: "/qa/faultcode/detail",
        method: "get",
        data: {
            no : no 
        } ,
        success: function(faultcodeVo) {

            document.querySelector("#faultcode-modal input[name=no]").value = faultcodeVo.no;
            document.querySelector("#faultcode-modal input[name=faultName]").value = faultcodeVo.faultName;
            
            const editButton = document.querySelector("#faultcode-edit-button");
            const deleteButton = document.querySelector("#faultcode-delete-button");

            editButton.addEventListener("click", function () {
                loadEditModal();
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

// 수정 모달
function loadEditModal() {

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "고장 코드 수정";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'block';
    }
    document.querySelector("input[name=faultName]").removeAttribute("disabled");
    document.querySelector(".button-container").innerHTML = `<input type="button" id="faultcode-save-button" value="저장">`;

    // 저장 버튼 클릭 시 동작
    const saveButton = document.querySelector("#faultcode-save-button");
    saveButton.addEventListener("click", function () {
        faultcodeEditSave();
    });

}

// 수정 처리
function faultcodeEditSave() {

    const no =  document.querySelector("#faultcode-modal input[name=no]").value;
    const faultName =  document.querySelector("#faultcode-modal input[name=faultName]").value;

    if (!faultName) {
        alert("고장유형명을 입력해주세요.");
        return;
    }

    const result = confirm("저장하시겠습니까?");

    if(result == false) {
        return;
    }

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

// 단건 삭제 처리
function faultcodeDelete(no) {

    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/faultcode/delete",
        method: "POST",
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

// 전체 선택 
function handelCheckbox(checkAll) {

    const checkBoxArr = document.querySelectorAll("input[name=listCheckbox]");

    for(let i=0; i<checkBoxArr.length; i++) {
        checkBoxArr[i].checked = checkAll.checked;
    }
}

// 다중 삭제 처리
function deleteMultiple() {
        
    const checkedArr = document.querySelectorAll("input[name=listCheckbox]:checked");
    const noArr = [];

    for(const checkBox of checkedArr) {
        const no = checkBox.parentNode.parentNode.children[1].innerText;
        noArr.push(no);
    }

    if (noArr.length == 0) {
        alert("선택된 건이 없습니다.");
        return;
    }

    const result = confirm("선택한 건을 삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/faultcode/delete",
        method: "POST",
        data : {
            no : noArr
        },

        success: function(result) {
            if(result > 0) {
                alert("삭제되었습니다.");
            }
            else {
                alert("삭제실패...");
            }

            location.reload();

        },
        error: function() {
            alert("통신실패...");
        }
    });

}

// 모달 닫기
function closeModal() {

    const modal = document.querySelector("#faultcode-modal");
    const closeModal = document.querySelector('#faultcode-modal .modal-close');
    
    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

}

closeModal();

// searchType 값에 따라서 input 타입 변경
function handleSearchType(x) {

    const searchValueTag = document.querySelector("input[name=searchValue]");

    if(x.value == "faultName") {
        searchValueTag.setAttribute("type", "search");
    } 
    else{
        searchValueTag.setAttribute("type", "number");
    }

}

// 새로고침 해도 input 타입 유지
document.addEventListener("DOMContentLoaded", () => {
    const searchTypeSelect = document.querySelector("select[name=searchType]");
    handleSearchType(searchTypeSelect); 
});