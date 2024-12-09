// 상세 모달 
function loadDetailModal(asworkNo) {

    const modal = document.querySelector("#aswork-modal");
    const modalForm = document.querySelector("#aswork-form");

    // 모달 표시
    modalForm.reset();
    modal.style.display = 'block'; 

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 작업 상세";
    const requiredArr = document.querySelectorAll(".required-text");
    for (let i = 0; i < requiredArr.length; i++) {
        requiredArr[i].style.display = 'none';
    }
    document.querySelector("#search-button").setAttribute("type", "hidden");
    document.querySelector("select[name=statusCode]").setAttribute("disabled", "true");
    document.querySelector("input[name=repairDate]").setAttribute("disabled", "true");
    document.querySelector("input[name=repairDate]").setAttribute("type", "text");
    document.querySelector("select[name=faultCode]").setAttribute("disabled", "true");
    document.querySelector("textarea[name=repairDetalis]").setAttribute("disabled", "true");
    document.querySelector("#enroll-date").style.display = 'flex';
    document.querySelector("#modify-date").style.display = 'flex';
    document.querySelector(".button-container").innerHTML =         
        `<input type="button" id="aswork-edit-button" value="수정">
        <input type="button" id="aswork-delete-button" value="삭제">`;


    $.ajax({
        url: "/qa/aswork/detail",
        method: "get",
        dataType: 'json',
        data: {
            asworkNo : asworkNo 
        } ,
        success: function(asworkVo) {

            document.querySelector("#aswork-modal input[name=no]").value = asworkVo.no;
            document.querySelector("#aswork-modal input[name=asNo]").value = asworkVo.asNo;
            document.querySelector("#aswork-modal input[name=itemCode]").value = asworkVo.itemCode;
            document.querySelector("#aswork-modal input[name=itemName]").value = asworkVo.itemName;
            document.querySelector("#aswork-modal input[name=productNo]").value = asworkVo.productNo;
            document.querySelector("#aswork-modal input[name=serialNumber]").value = asworkVo.serialNumber;
            document.querySelector("#aswork-modal input[name=productName]").value = asworkVo.productName;
            document.querySelector("#aswork-modal input[name=purchaseDate]").value = asworkVo.purchaseDate;
            document.querySelector("#aswork-modal input[name=warrantyYn]").value = asworkVo.warrantyYn;
            document.querySelector("#aswork-modal input[name=customerName]").value = asworkVo.customerName;
            document.querySelector("#aswork-modal select[name=customerArea]").value = asworkVo.customerArea;
            document.querySelector("#aswork-modal input[name=customerAdress]").value = asworkVo.customerAdress;
            document.querySelector("#aswork-modal input[name=customerPhone]").value = asworkVo.customerPhone;
            document.querySelector("#aswork-modal input[name=preferredServiceDate]").value = asworkVo.preferredServiceDate;
            document.querySelector("#aswork-modal input[name=issueTitle]").value = asworkVo.issueTitle;
            document.querySelector("#aswork-modal textarea[name=issueDescription]").value = asworkVo.issueDescription;
            
            document.querySelector("#aswork-modal select[name=statusCode]").value = asworkVo.statusCode;
            document.querySelector("#aswork-modal input[name=empNo]").value = asworkVo.empNo;
            document.querySelector("#aswork-modal input[name=empName]").value = asworkVo.empName;
            document.querySelector("#aswork-modal input[name=dname]").value = asworkVo.dname;
            document.querySelector("#aswork-modal input[name=repairDate]").value = asworkVo.repairDate;
            if (asworkVo.faultCode && asworkVo.faultCode.trim() !== "") {
                document.querySelector("#aswork-modal select[name=faultCode]").value = asworkVo.faultCode;
            } else {
                document.querySelector("#aswork-modal select[name=faultCode]").value = "";
            }
            document.querySelector("#aswork-modal textarea[name=repairDetalis]").value = asworkVo.repairDetalis;
            document.querySelector("#aswork-modal input[name=enrollDate]").value = asworkVo.enrollDate;
            document.querySelector("#aswork-modal input[name=modifyDate]").value = asworkVo.modifyDate;

            console.log(asworkVo.faultCode);

            const editButton = document.querySelector("#aswork-edit-button");
            const deleteButton = document.querySelector("#aswork-delete-button");

            editButton.addEventListener("click", function () {
                loadEditModal();
            });

            deleteButton.addEventListener("click", function () {
                asworkDelete(asworkVo.no);
            });

        },
        fail: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
        }
    });
}

// 수정 모달
function loadEditModal() {

    // 모달 내용 채우기
    document.querySelector(".modal-title").innerText = "AS 작업 수정";
    document.querySelector("#search-button").setAttribute("type", "button");
    document.querySelector("select[name=statusCode]").removeAttribute("disabled");
    document.querySelector("input[name=repairDate]").setAttribute("type", "date");
    document.querySelector("input[name=repairDate]").removeAttribute("disabled");
    document.querySelector("select[name=faultCode]").removeAttribute("disabled");
    document.querySelector("textarea[name=repairDetalis]").removeAttribute("disabled");
    document.querySelector("#enroll-date").style.display = 'none';
    document.querySelector("#modify-date").style.display = 'none';
    document.querySelector(".button-container").innerHTML = `<input type="button" id="aswork-save-button" value="저장">`;
    
    // 저장 버튼 클릭 시 동작 
    const saveButton = document.querySelector("#aswork-save-button");
    saveButton.addEventListener("click", function () {
        asworkEditSave();
    });

}


// 담당자 검색 모달 
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

// 담당자 검색 데이터 요청 
function empData(pno) {

    const tbodyTag = document.querySelector("#emp-modal table>tbody");
    const area = document.querySelector("#emp-modal select[name=asemp-area]").value;

    $.ajax({
        url : "/qa/aswork/asemplist",
        data : {
            pno ,
            area
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
                tdTag2.innerText = vo.empName;
                trTag.appendChild(tdTag2);

                const tdTag3 = document.createElement("td");
                tdTag3.innerText = vo.area;
                trTag.appendChild(tdTag3);

                const tdTag4 = document.createElement("td");
                tdTag4.innerText = vo.phone;
                trTag.appendChild(tdTag4);

                const tdTag5 = document.createElement("td");
                tdTag5.innerText = vo.deptName;
                trTag.appendChild(tdTag5);

                const tdTag6 = document.createElement("td");
                tdTag6.innerText = vo.positionName;
                trTag.appendChild(tdTag6);
            }
        } , 
        error : function(){
            alert("조회 실패...")
        }
    })

}

// 담당자 검색 모달 페이징
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

// 담당자 선택 처리
function selectEmp() {

    const selectedEmp = document.querySelector("#emp-modal input[type=radio]:checked");
    if (selectedEmp === null) {
        alert("사원이 선택되지 않았습니다.");
        return;
    }

    const no = selectedEmp.parentNode.parentNode.children[1].innerText;
    const empName = selectedEmp.parentNode.parentNode.children[2].innerText;
    const dname = selectedEmp.parentNode.parentNode.children[5].innerText;

    document.querySelector("#aswork-modal input[name=empNo]").value = no;
    document.querySelector("#aswork-modal input[name=empName]").value = empName;
    document.querySelector("#aswork-modal input[name=dname]").value = dname;

    const empModal = document.querySelector("#emp-modal");
    empModal.style.display = 'none';

}

// 수정 처리
function asworkEditSave() {

    const no = document.querySelector("#aswork-modal input[name=no]").value;
    const asNo = document.querySelector("#aswork-modal input[name=asNo]").value;
    const statusCode = document.querySelector("#aswork-modal select[name=statusCode]").value;
    const faultCode = document.querySelector("#aswork-modal select[name=faultCode]").value;
    const empNo = document.querySelector("#aswork-modal input[name=empNo]").value;
    const repairDate = document.querySelector("#aswork-modal input[name=repairDate]").value;
    const repairDetalis = document.querySelector("#aswork-modal textarea[name=repairDetalis]").value;

    const result = confirm("저장하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/aswork/edit",
        method: "post",
        data: {
            no, 
            asNo,
            statusCode,
            faultCode,
            empNo,
            repairDate,
            repairDetalis
        } ,
        success: function(result) {
            if(result == 1) {
                alert("수정되었습니다.");
            }
            else {
                alert("수정실패... 관리자에게 문의해주세요.");
            }
            location.reload();
        },

        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
            location.reload();
        }
    });

}

// 단건 삭제 처리
function asworkDelete(no) {

    const result = confirm("삭제하시겠습니까?");

    if(result == false) {
        return;
    }

    $.ajax({
        url: "/qa/aswork/delete",
        method: "POST",
        data: {
            no : no 
        } ,
        success: function(result) {
            if(result == 1) {
                alert("삭제되었습니다.");
            }
            else {
                alert("삭제실패... 관리자에게 문의해주세요.");
            }
            location.reload();
        },

        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
            location.reload();
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
function asworkDeleteMultiple() {
        
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
        url: "/qa/aswork/delete",
        method: "POST",
        data : {
            no : noArr
        },

        success: function(result) {
            if(result > 0) {
                alert("삭제되었습니다.");
            }
            else {
                alert("삭제실패... 관리자에게 문의해주세요.");
            }

            location.reload();

        },
        error: function() {
            alert("통신실패... 관리자에게 문의해주세요.");
        }
    });

}

// 모달 닫기
function closeModal() {

    const modal = document.querySelector("#aswork-modal");
    const closeModal = document.querySelector('#aswork-modal .modal-close');
    
    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

}

closeModal();
