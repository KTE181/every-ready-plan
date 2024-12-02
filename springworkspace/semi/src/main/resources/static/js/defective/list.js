function openModal(){

  const modalTag = document.querySelector('#modalOverlay');
  modalTag.classList.add('active');

}

function closeModal(){
  const modalTag = document.querySelector('#modalOverlay');
  modalTag.classList.remove('active');
}


function handleCheckBox(x){
    
  const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");

  for(let i = 0 ; i < checkBoxArr.length; i++){
      checkBoxArr[i].checked = x.checked;
  }



}

function delDefective(){

  const defectiveNoArr = [];
  const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");

  for(const checkbox of checkBoxArr){
      if(checkbox.checked == false){
          continue;
      }
      const defectiveNo = checkbox.parentNode.parentNode.children[1].innerText;
      defectiveNoArr.push(defectiveNo);
  }

  $.ajax({
      url: "/qa/defective/delete",
      method: "delete",
      data: {
        defectiveNoArr : JSON.stringify(defectiveNoArr)
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

$(document).ready(function () {
  // 테이블 행 클릭 이벤트
  $(".product-row").on("click", function () {
      const defectiveNo = $(this).data("product-no"); // 행에 저장된 상품 번호 가져오기

      $.ajax({
          url: "/qa/defective/detail",
          type: "GET",
          data: { no: defectiveNo }, // 상품 번호 전송
          success: function (response) {
              // 응답 데이터를 모달 창에 표시
              $("#image").val(response.image);
              $("#productno1").val(response.serialNumber);
              $("#price1").val(response.price);
              $("#product-name1").val(response.productName);
              $("#defective-code1").val(response.defectiveCode);
              $("#serial-no1").val(response.serialNumber);
              $("#defective-name1").val(response.defectiveName);
              $("#textarea1").val(response.description);

              // 모달 창 열기
              $("#modalDetail").show();
          },
          error: function () {
              alert("상품 정보를 가져오는 데 실패했습니다.");
          }
      });
  });

  $("#closeDetailModal").on("click", function () {
      $("#modalDetail").hide();
  });

});





// document.addEventListener("DOMContentLoaded", function () {
//   const registerButton = document.querySelector("#registerProduct");

//       registerButton.addEventListener("click", function () {

          

//           const itemCode = document.querySelector("#item-code1").value;
//           const name = document.querySelector("#product-name1").value;
//           // const serialNumber = document.querySelector("#modalSerialNumber").value;
//           const price = document.querySelector("#product-price1").value;
//           const warrantyPeriod = document.querySelector("#warranty1").value;
//           const receivedDate = document.querySelector("#import-date1").value;
//           const factoryName = document.querySelector("#manufacturer1").value;
//           const factoryLocation = document.querySelector("#manufacturer-address1").value;


//           $.ajax({
//               method: "POST",
//               url: "/qa/product/write",
//               data: {
//                   itemCode: itemCode,
//                   name: name,
//                   // serialNumber: serialNumber,
//                   price: price,
//                   warrantyPeriod: warrantyPeriod,
//                   receivedDate: receivedDate,
//                   factoryName: factoryName,
//                   factoryLocation: factoryLocation
//               },
//               success: function () {
//                   alert("상품이 성공적으로 등록되었습니다!");
//                   location.reload();
//               },
//               error: function () {
//                   alert("상품 등록 실패");
//               }
//           });
//   });
// });