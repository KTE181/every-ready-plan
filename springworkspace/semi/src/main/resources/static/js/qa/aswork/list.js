function asworkDetail(asworkNo) {

    // 모달 요소 가져오기
    const asworkDetailModal = document.getElementById('aswork-detail');
    const detailContent = document.querySelector('.detail-content');

    asworkDetailModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/qa/aswork/detail",
        method: "get",
        dataType: 'json',
        data: {
            asworkNo : asworkNo 
        } ,
        success: function(asworkVo) {

            document.querySelector("#aswork-detail input[name=no]").value = asworkVo.no;
            document.querySelector("#aswork-detail input[name=productNo]").value = asworkVo.productNo;
            document.querySelector("#aswork-detail input[name=serialNumber]").value = asworkVo.serialNumber;
            document.querySelector("#aswork-detail input[name=productName]").value = asworkVo.productName;
            document.querySelector("#aswork-detail input[name=purchaseDate]").value = asworkVo.purchaseDate;
            document.querySelector("#aswork-detail input[name=warrantyYn]").value = asworkVo.warrantyYn;
            document.querySelector("#aswork-detail input[name=customerName]").value = asworkVo.customerName;
            document.querySelector("#aswork-detail select[name=customerArea]").value = asworkVo.customerArea;
            document.querySelector("#aswork-detail input[name=customerAdress]").value = asworkVo.customerAdress;
            document.querySelector("#aswork-detail input[name=customerPhone]").value = asworkVo.customerPhone;
            document.querySelector("#aswork-detail input[name=preferredServiceDate]").value = asworkVo.preferredServiceDate;
            document.querySelector("#aswork-detail input[name=issueTitle]").value = asworkVo.issueTitle;
            document.querySelector("#aswork-detail textarea[name=issueDescription]").value = asworkVo.issueDescription;
            
            document.querySelector("#aswork-detail select[name=statusCode]").value = asworkVo.statusCode;
            document.querySelector("#aswork-detail select[name=empNo]").value = asworkVo.empNo;
            document.querySelector("#aswork-detail input[name=repairDate]").value = asworkVo.repairDate;
            document.querySelector("#aswork-detail select[name=faultCode]").value = asworkVo.faultCode;
            document.querySelector("#aswork-detail textarea[name=repairDetalis]").value = asworkVo.repairDetalis;
            
            const editButton = document.querySelector("#aswork-edit-bnt");

            editButton.addEventListener("click", function () {
                asworkEdit(asworkNo);
            });

        },
        fail: function() {
            alert("통신실패...");
        }
    });
}

function asworkDetailClose() {
    const asworkDetailModal = document.getElementById('aswork-detail');
    asworkDetailModal.style.display = 'none'; // 모달 숨기기
}

function asworkEdit(asworkNo) {

    console.log(asworkNo);

    const asworkEditModal = document.getElementById('aswork-edit');
    const editModalContent = document.querySelector('.edit-content');

    asworkDetailClose();

    asworkEditModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/qa/aswork/edit",
        method: "get",
        dataType: 'json',
        data: {
            asworkNo : asworkNo 
        } ,
        success: function(asworkVo) {
            
            document.querySelector("#aswork-edit input[name=no]").value = asworkVo.no;
            document.querySelector("#aswork-edit input[name=productNo]").value = asworkVo.productNo;
            document.querySelector("#aswork-edit input[name=serialNumber]").value = asworkVo.serialNumber;
            document.querySelector("#aswork-edit input[name=productName]").value = asworkVo.productName;
            document.querySelector("#aswork-edit input[name=purchaseDate]").value = asworkVo.purchaseDate;
            document.querySelector("#aswork-edit input[name=warrantyYn]").value = asworkVo.warrantyYn;
            document.querySelector("#aswork-edit input[name=customerName]").value = asworkVo.customerName;
            document.querySelector("#aswork-edit select[name=customerArea]").value = asworkVo.customerArea;
            document.querySelector("#aswork-edit input[name=customerAdress]").value = asworkVo.customerAdress;
            document.querySelector("#aswork-edit input[name=customerPhone]").value = asworkVo.customerPhone;
            document.querySelector("#aswork-edit input[name=preferredServiceDate]").value = asworkVo.preferredServiceDate;
            document.querySelector("#aswork-edit input[name=issueTitle]").value = asworkVo.issueTitle;
            document.querySelector("#aswork-edit textarea[name=issueDescription]").value = asworkVo.issueDescription;
            
            document.querySelector("#aswork-edit select[name=statusCode]").value = asworkVo.statusCode;
            document.querySelector("#aswork-edit select[name=empNo]").value = asworkVo.empNo;
            document.querySelector("#aswork-edit input[name=repairDate]").value = asworkVo.repairDate;
            document.querySelector("#aswork-edit select[name=faultCode]").value = asworkVo.faultCode;
            document.querySelector("#aswork-edit textarea[name=repairDetalis]").value = asworkVo.repairDetalis;

        },

        fail: function() {
            alert("통신실패...");
        }
    });

}

function asworkEditClose() {
    const asworkEditModal = document.getElementById('aswork-edit');
    asworkEditModal.style.display = 'none'; // 모달 숨기기
}