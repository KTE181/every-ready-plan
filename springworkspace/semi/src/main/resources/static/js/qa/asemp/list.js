function asempEnroll() {
    
    // 모달 요소 가져오기
    const asempEnrollModal = document.getElementById('asemp-enroll');
    const closeModal = document.querySelector('.enroll-close');

    asempEnrollModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        asempEnrollModal.style.display = 'none'; // 모달 숨기기
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
            
            console.log("asempVo.area:", asempVo.area);
            console.log(document.querySelector("#asemp-detail select[name=area]"));

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

            editButton.addEventListener("click", function () {
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

    asempEditModal.style.display = 'block'; // 모달 표시

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