// 모달 열기
function openModal() {
    
    const modalTag = document.querySelector("#modalOverlay");
    modalTag.classList.add('active');
}

// 모달 닫기
function closeModal() {
    const modalTag = document.querySelector("#modalOverlay");
    modalTag.classList.remove('active');
}

function openEditModal() {
    const modalTag = document.querySelector("#edit");
    modalTag.classList.remove('active');
}

function handleCheckBox(x){

    const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");

    for(let i = 0 ; i < checkBoxArr.length; i++){
        checkBoxArr[i].checked = x.checked;
    }

}







$(document).ready(function () {
    // 테이블 행 클릭 이벤트
    $(".product-row").on("click", function () {
        const productNo = $(this).data("product-no"); // 행에 저장된 상품 번호 가져오기

        $.ajax({
            url: "/qa/product/detail",
            type: "GET",
            data: { no: productNo }, // 상품 번호 전송
            success: function (response) {
                // 응답 데이터를 모달 창에 표시
                $("#image").val(response.image);
                $("#item-code").val(response.itemCode);
                $("#product-name").val(response.name);
                $("#serial-number").val(response.serialNumber);
                $("#product-price").val(response.price);
                $("#product-quantity").val(response.quantity);
                $("#warranty").val(response.warrantyPeriod);
                $("#factoryName").val(response.factoryName);
                $("#factory-address").val(response.factoryLocation);
                $("#import-date").val(response.receivedDate);

                // 모달 창 열기
                $("#modalDetail").show();
            },
            error: function () {
                alert("상품 정보를 가져오는 데 실패했습니다.");
            }
        });
    });

    $("#closeModal").on("click", function () {
        $("#modalDetail").hide();
    });

});

//모달 닫기
function closeDetailModal() {
    const modalTag = document.querySelector("#modalDetail");
    modalTag.classList.remove('active');
}




function delProduct(){

    const ProductNoArr = [];
    const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");

    for(const checkbox of checkBoxArr){
        if(checkbox.checked == false){
            continue;
        }
        const ProductNo = checkbox.parentNode.parentNode.children[1].innerText;
         ProductNoArr.push(ProductNo);
    }

    $.ajax({
        url: "/qa/product/delete",
        method: "delete",
        data: {
            ProductNoArr : JSON.stringify(ProductNoArr)
        },
        success: function(x){
            if(x == "good"){
                alert("삭제 성공!");
            }else{
                alert("삭제 실패...");
            }
            location.reload();
        },
        error: function(){
            console.log("통신 실패...")
        },
    });

}


// const firstModal = document.getElementById('modalDetail');
// const secondModal = document.getElementById('modalEdit');

// // 버튼과 닫기 버튼 요소 가져오기
// const openSecondModalBtn = document.getElementById('openEditModal');
// const closeButtons = document.querySelectorAll('.close');

// // 모달 열기 함수
// const openModal2 = (modal) => modal.classList.add('show');

// // 모달 닫기 함수
// const closeModal2 = (modal) => modal.classList.remove('show');

// // 첫 번째 모달 내부 버튼 클릭 시 두 번째 모달 열기
// openSecondModalBtn.addEventListener('click', () => {
//   closeModal2(firstModal); // 첫 번째 모달 닫기
//   openModal2(secondModal); // 두 번째 모달 열기
// });

// // 모든 닫기 버튼에 닫기 이벤트 추가
// closeButtons.forEach((button) =>
//   button.addEventListener('click', (event) => {
//     const modal = event.target.closest('.modal2');
//     closeModal2(modal);
//   })
// );


document.addEventListener("DOMContentLoaded", function () {
    const registerButton = document.querySelector("#registerProduct");

        registerButton.addEventListener("click", function () {

            console.log("product-name tag : " , document.querySelector("#product-name"));
            

            const itemCode = document.querySelector("#item-code1").value;
            const name = document.querySelector("#product-name1").value;
            // const serialNumber = document.querySelector("#modalSerialNumber").value;
            const price = document.querySelector("#product-price1").value;
            const warrantyPeriod = document.querySelector("#warranty1").value;
            const receivedDate = document.querySelector("#import-date1").value;
            const factoryName = document.querySelector("#manufacturer1").value;
            const factoryLocation = document.querySelector("#manufacturer-address1").value;


            $.ajax({
                method: "POST",
                url: "/qa/product/write",
                data: {
                    itemCode: itemCode,
                    name: name,
                    // serialNumber: serialNumber,
                    price: price,
                    warrantyPeriod: warrantyPeriod,
                    receivedDate: receivedDate,
                    factoryName: factoryName,
                    factoryLocation: factoryLocation
                },
                success: function () {
                    alert("상품이 성공적으로 등록되었습니다!");
                    location.reload();
                },
                error: function () {
                    alert("상품 등록 실패");
                }
            });
    });
});




