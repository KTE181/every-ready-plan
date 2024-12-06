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

function openItemCodeModal(){
    const modalTag = document.querySelector("#modalItemCode");
    modalTag.classList.add('active');
}


function handleCheckBox(x){

    const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");

    for(let i = 0 ; i < checkBoxArr.length; i++){
        checkBoxArr[i].checked = x.checked;
    }

}







$(document).ready(function () {

    $(".product-row").on("click", function () {
        const productNo = $(this).data("product-no"); 


        $.ajax({
            url: "/qa/product/detail",
            type: "GET",
            data: { no: productNo },
            success: function (response) {

                document.querySelector("#image").value = response.image;
                document.querySelector("#item-code").value = response.itemCode;
                document.querySelector("#product-name").value = response.name;
                document.querySelector("#serial-number").value = response.serialNumber;
                document.querySelector("#product-price").value = response.price;
                document.querySelector("#product-quantity").value = response.quantity;
                document.querySelector("#warranty").value = response.warrantyPeriod;
                document.querySelector("#factoryName").value = response.factoryName;
                document.querySelector("#factory-address").value = response.factoryLocation;
                document.querySelector("#import-date").value = response.receivedDate;

                // 모달 창 열기
                document.querySelector("#modalDetail").style.display = "block";
            },
            error: function () {
                alert("상품 정보를 가져오는 데 실패했습니다.");
            }
        });
    });

    document.querySelector("#closeModal").addEventListener("click", function () {
    document.querySelector("#modalDetail").style.display = "none";
});

});

let no= "";

// 테이블 행 클릭 이벤트
document.querySelectorAll("#productTable td").forEach(row => {



    row.addEventListener("click", function (evt) {
        const productNo = this.getAttribute("data-product-no"); 

        no = evt.target.parentNode.children[1].innerText;

        $.ajax({
            url: `/qa/product/detail?no=${productNo}`,
            method: "GET",
            success: function (x) {

                document.querySelector("#item-code").value = x.itemCode;
                document.querySelector("#product-name").value = x.name;
                document.querySelector("#product-price").value = x.price;
                document.querySelector("#serial-number").value = x.serialNumber;
                document.querySelector("#factoryName").value = x.factoryName;
                document.querySelector("#factory-address").value = x.factoryLocation;
                document.querySelector("#warranty").value = x.warrantyPeriod;
                document.querySelector("#import-date").value = x.receivedDate;

                document.querySelector("#modalDetail").style.display = "block";
            },
            error: function () {
                alert("상품 상세 정보를 가져오는 데 실패했습니다.");
            }
        });
    });
});

document.querySelector("#closeModal").addEventListener("click", function () {
    document.querySelector("#modalDetail").style.display = "none";
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





document.addEventListener("DOMContentLoaded", function () {
    const registerButton = document.querySelector("#registerProduct");

        registerButton.addEventListener("click", function () {            

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
                    reload();
                    
                },
                error: function () {
                    alert("상품 등록 실패");
                }
            });
    });
});




// 수정 버튼 클릭 이벤트
document.querySelector("#edit-button").addEventListener("click", function () {
    document.querySelector("#modalDetail").style.display = "none";
    document.querySelector("#modalEdit").style.display = "block";
});

document.querySelector("#modalEdit #closeProductUpdateModal").addEventListener("click", function (evt) {
    evt.preventDefault();
    document.querySelector("#modalEdit").style.display = "none";
});

// modalDetail 닫기 버튼 이벤트
document.querySelector("#modalDetail #closeModal").addEventListener("click", function (evt) {
    evt.preventDefault();
    document.querySelector("#modalDetail").style.display = "none";
});


document.querySelector("#edit-button").addEventListener("click",function(){
    const itemCode = document.querySelector("#item-code").value;
    console.log(itemCode);
    const name = document.querySelector("#product-name").value;
    console.log(name);
    const price= document.querySelector("#product-price").value;
    console.log(price);
    const factoryName = document.querySelector("#factoryName").value;
    console.log(factoryName);
    const factoryAddress = document.querySelector("#factory-address").value;
    const warranty = document.querySelector("#warranty").value;

    document.querySelector("#edit-item-code").value = itemCode;
    document.querySelector("#edit-product-name").value = name;
    document.querySelector("#edit-product-price").value = price;
    document.querySelector("#edit-factory-name").value = factoryName;
    document.querySelector("#edit-factory-address").value = factoryAddress;
    document.querySelector("#edit-warranty-period").value = warranty;
    
});

document.querySelector("#modalEdit .primary").addEventListener("click", function () {

        const itemCode= document.querySelector("#edit-item-code").value;
        console.log(itemCode);
        const name= document.querySelector("#edit-product-name").value;
        console.log(name);
        const price= document.querySelector("#edit-product-price").value;
        console.log(price);
        const factoryName= document.querySelector("#edit-factory-name").value;
        console.log(factoryName);
        const factoryAddress= document.querySelector("#edit-factory-address").value;
        console.log(factoryAddress);
        const warrantyPeriod= document.querySelector("#edit-warranty-period").value;
        console.log(warrantyPeriod);



    $.ajax({
        url: "/qa/product/edit", 
        method: "POST",
        data: {

            no : no,
            itemCode : itemCode,
            name : name,
            price: price,
            factoryName : factoryName,
            factoryLocation : factoryAddress,
            warrantyPeriod : warrantyPeriod,
        },
        success: function(){
            alert("수정되었습니다!");
            location.reload();
        },
        error: function () {
            alert("수정에 실패했습니다...");
        }
    });
})

