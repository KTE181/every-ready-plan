const searchButton = document.querySelector('#asemp-enroll .emp-search-button');
searchButton.addEventListener('click', () => {
    searchEmp();
});

function asempEnroll(empNo, empName, phone, dname, pname) {

    // 모달 요소 가져오기
    const asempEnrollModal = document.getElementById('asemp-enroll');
    const closeModal = document.querySelector('.enroll-close');

    asempEnrollModal.style.display = 'block'; // 모달 표시

    if (empNo != null) {
        document.querySelector("#asemp-enroll input[name=no]").value = empNo;
        document.querySelector("#asemp-enroll input[name=empName]").value = empName;
        document.querySelector("#asemp-enroll input[name=phone]").value = phone;
        document.querySelector("#asemp-enroll input[name=deptName]").value = dname;
        document.querySelector("#asemp-enroll input[name=positionName]").value = pname;
    }

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {

        asempEnrollModal.style.display = 'none'; // 모달 숨기기

        document.querySelector("#asemp-enroll input[name=no]").value = "";
        document.querySelector("#asemp-enroll input[name=empName]").value = "";
        document.querySelector("#asemp-enroll input[name=phone]").value = "";
        document.querySelector("#asemp-enroll input[name=deptName]").value = "";
        document.querySelector("#asemp-enroll input[name=positionName]").value = "";

        empNo = null; 
        empName = null; 
        phone = null; 
        dname = null; 
        pname = null; 
        
    });

}

function asempDetail(no) {

    // 모달 요소 가져오기
    const asempDetailModal = document.getElementById('asemp-detail');
    const detailContent = document.querySelector('.detail-content');
    const closeModal = document.querySelector('.detail-close');

    asempDetailModal.style.display = 'block'; // 모달 표시

    closeModal.addEventListener('click', () => {
        asempDetailModal.style.display = 'none'; // 모달 숨기기
    });

    $.ajax({
        url: "/qa/asemp/detail",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(asempVo) {

            document.querySelector("#asemp-detail input[name=no]").value = asempVo.no;
            document.querySelector("#asemp-detail input[name=empName]").value = asempVo.empName;
            document.querySelector("#asemp-detail input[name=phone]").value = asempVo.phone;
            document.querySelector("#asemp-detail input[name=deptName]").value = asempVo.deptName;
            document.querySelector("#asemp-detail input[name=positionName]").value = asempVo.positionName;
            document.querySelector("#asemp-detail select[name=area]").value = asempVo.area;

            const editButton = document.querySelector("#asemp-edit-bnt");
            const deleteButton = document.querySelector("#asemp-delete-bnt");

            editButton.addEventListener("click", function () {
                asempEdit(asempVo.no);
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

function asempDetailClose() {
    const asempDetailModal = document.getElementById('asemp-detail');
    asempDetailModal.style.display = 'none'; // 모달 숨기기
}

function asempEdit(no) {

    console.log(no);
    asempDetailClose();

    const asempEditModal = document.getElementById('asemp-edit');
    const editModalContent = document.querySelector('.edit-content');
    const closeModal = document.querySelector('.edit-close');

    asempEditModal.style.display = 'block'; // 모달 표시

    closeModal.addEventListener('click', () => {
        asempEditModal.style.display = 'none'; // 모달 숨기기
    });

    $.ajax({
        url: "/qa/asemp/edit",
        method: "get",
        dataType: 'json',
        data: {
            no : no 
        } ,
        success: function(asempVo) {

            document.querySelector("#asemp-edit input[name=no]").value = asempVo.no;
            document.querySelector("#asemp-edit input[name=empName]").value = asempVo.empName;
            document.querySelector("#asemp-edit input[name=phone]").value = asempVo.phone;
            document.querySelector("#asemp-edit input[name=deptName]").value = asempVo.deptName;
            document.querySelector("#asemp-edit input[name=positionName]").value = asempVo.positionName;
            document.querySelector("#asemp-edit select[name=area]").value = asempVo.area;
        },

        fail: function() {
            alert("통신실패...");
        }
    });

}

function asempEditClose() {
    const asempEditModal = document.getElementById('asemp-edit');
    asempEditModal.style.display = 'none'; // 모달 숨기기
}




const selectButton = document.querySelector("#emp-select-btn");

selectButton.addEventListener('click', () => {

    console.log("몇번호출??");
    const radioArr = document.querySelectorAll("input[name=emp-radio-btn]");
    let empNo = null;
    let empName = null;
    let phone = null;
    let dname = null;
    let pname = null;

    for(let i=0; i<radioArr.length; i++) {
        if (radioArr[i].checked) {
            empNo = radioArr[i].parentNode.parentNode.children[1].innerText;
            empName = radioArr[i].parentNode.parentNode.children[2].innerText;
            phone = radioArr[i].parentNode.parentNode.children[3].innerText;
            dname = radioArr[i].parentNode.parentNode.children[4].innerText;
            pname = radioArr[i].parentNode.parentNode.children[5].innerText;
        }
    }

    if (empNo === null) {
        alert("사원이 선택되지 않았습니다.");
    }
    
    searchEmpClose();
    asempEnroll(empNo, empName, phone, dname, pname);

});

function searchEmp() {

    const searchEmpModal = document.getElementById('search-emp');
    const closeModal = document.querySelector('.modal-close');

    searchEmpModal.style.display = 'block'; // 모달 표시

    closeModal.addEventListener('click', () => {
        searchEmpModal.style.display = 'none'; // 모달 숨기기
    });

    const tbodyTag = document.querySelector("#search-emp table>tbody");

    $.ajax({
        url : "/qa/asemp/emplist",
        method : "GET" ,
        success : function(empVoList){

            console.log(empVoList)

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

function searchEmpClose() {
    const searchEmpModal = document.getElementById('search-emp');
    searchEmpModal.style.display = 'none'; // 모달 숨기기
}

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