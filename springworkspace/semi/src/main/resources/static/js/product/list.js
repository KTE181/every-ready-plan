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


// function getProductDetail(prno){

//     const asreqDetailModal = document.getElementById('product-detail');
//     const modalTag = document.querySelector('.detail-content');

//     asreqDetailModal.style.display = 'block'; 

//     $.ajax({
//         url: "/qa/product/detail",
//         method: "get",
//         data: {
//             prno : prno 
//         } ,
//         success: function(data) {
//             console.log(data);
//             modalTag.innerHTML = data; 
//         },

//         fail: function() {
//             alert("통신실패...");
//         }
//     });
// }

function handleCheckBox(x){
    
    const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");

    for(let i = 0 ; i < checkBoxArr.length; i++){
        checkBoxArr[i].checked = x.checked;
    }



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