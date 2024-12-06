// 등록 모달
function loadWriteModal() {

    const modal = document.querySelector("#asemp-modal");
    const modalForm = document.querySelector("#asemp-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 담당자 등록";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'block';
    }
    document.querySelector("#search-button").setAttribute("type", "button");
    document.querySelector("#asemp-modal select[name=area]").removeAttribute("disabled");
    document.querySelector(".button-container").innerHTML = `<input type="button" id="asemp-write-button" value="등록">`;

    // 등록 버튼 클릭 시 동작 
    const writeButton = document.querySelector("#asemp-write-button");
    writeButton.addEventListener("click", function () {
        write();
    });

}

// 등록 처리 
function write() {

    const no = document.querySelector("#asemp-modal input[name=no]").value;
    const area = document.querySelector("#asemp-modal select[name=area]").value;

    if (!no) {
        alert("사원을 선택하세요.");
        return;
    }

    if (!area) {
        alert("담당지역을 선택하세요.");
        return;
    }

    const result = confirm("등록하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asemp/write",
        method: "POST",
        data : {
            no ,
            area
        },

        success: function(result) {
            if(result == 1) {
                alert("등록되었습니다.");
            }
            else {
                alert("등록실패...");
            }
            location.href = "/qa/asemp/list";
        },
        error: function() {
            alert("통신실패...");
            location.href = "/qa/asemp/list";
        }
    });
}

// 사원 검색 모달 
function empList() {

    const empModal = document.querySelector("#emp-modal");
    const modalForm = document.querySelector("#emp-search-form");
    const closeModal = document.querySelector("#emp-modal .modal-close");
    modalForm.reset();
    empModal.style.display = 'block'; 

    closeModal.addEventListener('click', () => {
        empModal.style.display = 'none';
    } , { once: true });

    const pno = 1;
    
    empData(pno);

}

// 사원 검색 데이터 요청 
function empData(pno) {

    const tbodyTag = document.querySelector("#emp-modal table>tbody");
    const dept = document.querySelector("#emp-modal select[name=dept]").value;
    const empSearchType = document.querySelector("#emp-modal select[name=empSearchType]").value;
    const empSearchValue = document.querySelector("#emp-modal input[name=empSearchValue]").value;

    $.ajax({
        url : "/qa/asemp/emplist",
        data : {
            pno ,
            dept ,
            empSearchType ,
            empSearchValue
        } ,
        method : "GET" ,
        success : function(m){

            const empVoList = m.a;
            const pvo = m.b;
            paintPageArea(pvo);

            tbodyTag.innerHTML = ""; 

            for(const vo of empVoList) {

                const trTag = document.createElement("tr");
                tbodyTag.appendChild(trTag); 

                const tdTag = document.createElement("td");
                const radioTag = document.createElement("input");
                radioTag.setAttribute("type", "radio");
                radioTag.setAttribute("name", "emp-radio-btn");

                trTag.appendChild(tdTag);
                tdTag.appendChild(radioTag);
                
                const tdTag1 = document.createElement("td");
                tdTag1.innerText = vo.no;
                trTag.appendChild(tdTag1);

                const tdTag2 = document.createElement("td");
                tdTag2.innerText = vo.name;
                trTag.appendChild(tdTag2);

                const tdTag3 = document.createElement("td");
                tdTag3.innerText = vo.phone;
                trTag.appendChild(tdTag3);

                const tdTag4 = document.createElement("td");
                tdTag4.innerText = vo.dname;
                trTag.appendChild(tdTag4);

                const tdTag5 = document.createElement("td");
                tdTag5.innerText = vo.pname;
                trTag.appendChild(tdTag5);
            }
        } , 
        error : function(){
            alert("조회 실패...")
        }
    })

}

// 사원 검색 모달 페이징
function paintPageArea(pvo){
    const pageArea = document.querySelector(".page-area");
    pageArea.innerHTML = "";

    // 이전 버튼
    if(pvo.startPage != 1) {
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `empData(${pvo.startPage-1});`);
        spanTag.innerText = "이전";
        pageArea.appendChild(spanTag);
    }

    // 페이지 버튼
    for(let i=pvo.startPage; i<=pvo.endPage; i++){
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `empData(${i});`);
        spanTag.innerText = i;
        pageArea.appendChild(spanTag);
    }

    // 다음 버튼
    if(pvo.endPage != pvo.maxPage){
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `empData(${pvo.endPage+1});`);
        spanTag.innerText = "다음";
        pageArea.appendChild(spanTag);
    }
}

// 사원 선택 처리
function selectEmp() {

    const selectedEmp = document.querySelector("#emp-modal input[type=radio]:checked");
    if (selectedEmp === null) {
        alert("사원이 선택되지 않았습니다.");
        return;
    }

    const no = selectedEmp.parentNode.parentNode.children[1].innerText;
    const empName = selectedEmp.parentNode.parentNode.children[2].innerText;
    const phone = selectedEmp.parentNode.parentNode.children[3].innerText;
    const deptName = selectedEmp.parentNode.parentNode.children[4].innerText;
    const positionName = selectedEmp.parentNode.parentNode.children[5].innerText;

    document.querySelector("#asemp-modal input[name=no]").value = no;
    document.querySelector("#asemp-modal input[name=empName]").value = empName;
    document.querySelector("#asemp-modal input[name=phone]").value = phone;
    document.querySelector("#asemp-modal input[name=deptName]").value = deptName;
    document.querySelector("#asemp-modal input[name=positionName]").value = positionName;

    const empModal = document.querySelector("#emp-modal");
    empModal.style.display = 'none';

}

// 상세 모달
function loadDetailModal(no) {

    const modal = document.querySelector("#asemp-modal");
    const modalForm = document.querySelector("#asemp-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 담당자 상세";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'none';
    }
    document.querySelector("#search-button").setAttribute("type", "hidden");
    document.querySelector("#asemp-modal select[name=area]").setAttribute("disabled", "true");
    document.querySelector(".button-container").innerHTML = 
        `<input type="button" id="asemp-edit-button" value="수정">
        <input type="button" id="asemp-delete-button" value="삭제">`;


    $.ajax({
        url: "/qa/asemp/detail",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(asempVo) {

            document.querySelector("#asemp-modal input[name=no]").value = asempVo.no;
            document.querySelector("#asemp-modal input[name=empName]").value = asempVo.empName;
            document.querySelector("#asemp-modal input[name=phone]").value = asempVo.phone;
            document.querySelector("#asemp-modal input[name=deptName]").value = asempVo.deptName;
            document.querySelector("#asemp-modal input[name=positionName]").value = asempVo.positionName;
            document.querySelector("#asemp-modal select[name=area]").value = asempVo.area;

            const editButton = document.querySelector("#asemp-edit-button");
            const deleteButton = document.querySelector("#asemp-delete-button");

            editButton.addEventListener("click", function () {
                loadEditModal(asempVo.no);
            });

            deleteButton.addEventListener("click", function () {
                asempDelete(asempVo.no);
            });

        },
        fail: function() {
            alert("통신실패...");
        }
    });
}

// 수정 모달
function loadEditModal() {

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 담당자 수정";
    document.querySelector("#asemp-modal #required-text-area").style.display='block';
    document.querySelector("#asemp-modal select[name=area]").removeAttribute("disabled");
    document.querySelector(".button-container").innerHTML = `<input type="button" id="asemp-save-button" value="저장">`;
  
    // 저장 버튼 클릭 시 동작 
    const saveButton = document.querySelector("#asemp-save-button");
    saveButton.addEventListener("click", function () {
         asempEditSave();
    });

}

// 수정 처리
function asempEditSave() {

    const no = document.querySelector("#asemp-modal input[name=no]").value;
    const area = document.querySelector("#asemp-modal select[name=area]").value;

    if (!no) {
        alert("사원을 선택하세요.");
        return;
    }

    if (!area) {
        alert("담당지역을 선택하세요.");
        return;
    }

    const result = confirm("저장하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asemp/edit",
        method: "POST",
        data: {
            no ,
            area
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
            location.reload();
        }
    });
}

// 단건 삭제 처리
function asempDelete(no) {

    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/asemp/delete",
        method: "POST",
        data: {
            no : no 
        } ,
        success: function(result) {

            if(result == 1) {
                alert("삭제되었습니다.");
            }
            else {
                alert("삭제 실패...");
            }

            location.reload();
        },

        fail: function() {
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
function asempDeleteMultiple() {
        
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
        url: "/qa/asemp/delete",
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

    const modal = document.querySelector("#asemp-modal");
    const closeModal = document.querySelector('#asemp-modal .modal-close');
    
    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

}

closeModal();

// searchType 값에 따라서 input 타입 변경
function handleSearchType(x) {

    const searchValueTag = document.querySelector("input[name=searchValue]");

    if(x.value == "empName") {
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