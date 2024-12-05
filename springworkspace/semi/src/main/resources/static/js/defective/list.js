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

document.querySelectorAll("#defectiveTable td").forEach(row => {
    row.addEventListener("click", function () {
        const defectiveNo = this.getAttribute("data-product-no"); 

        $.ajax({
            url: `/qa/defective/detail?no=${defectiveNo}`,
            method: "GET",
            success: function (x) {

                document.querySelector("#productno1").value = x.no;
                document.querySelector("#price1").value = x.price;
                document.querySelector("#product-name1").value = x.productName;
                document.querySelector("#defective-code1").value = x.defectiveCode;
                document.querySelector("#serial-no1").value = x.serialNumber;
                document.querySelector("#defective-name1").value = x.defectiveName;
                document.querySelector("#textarea1").value = x.description;
                

                document.querySelector("#modalDetail").style.display = "block";
            },
            error: function () {
                alert("상품 조회 실패..");
            }
        });
    });
});


document.querySelector("#closeDetailModal").addEventListener("click", function () {
    document.querySelector("#modalDetail").style.display = "none";
});





document.addEventListener("DOMContentLoaded", function () {
  const registerButton = document.querySelector("#registerDefectiveProduct");

      registerButton.addEventListener("click", function () {

          

          const serialNumber = document.querySelector("#dfserialno").value;
          const name = document.querySelector("#dfproduct-name").value;
          // const serialNumber = document.querySelector("#modalSerialNumber").value;
          const price = document.querySelector("#dfprice").value;
          const productNo = document.querySelector("#dfproductno").value;
          const defectiveCode = document.querySelector("#defectivecode-select").value;
          const defectiveName = document.querySelector("#dfdefective-name").value;
          const description = document.querySelector("#dftext-area").value;


          $.ajax({
              method: "POST",
              url: "/qa/defective/write",
              data: {
                serialNumber : serialNumber,
                  name: name,
                  serialNumber: serialNumber,
                  price: price,
                  no: productNo,
                  defectiveCode: defectiveCode,
                  defectiveName: defectiveName,
                  description: description,
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



// 수정 버튼 클릭 이벤트
document.querySelector("#edit-button").addEventListener("click", function () {
    document.querySelector("#modalDetail").style.display = "none";
    document.querySelector("#modaldefectiveEdit").style.display = "block";
});

document.querySelector("#modaldefectiveEdit #closeDefectiveUpdateModal").addEventListener("click", function (evt) {
    evt.preventDefault();
    document.querySelector("#modaldefectiveEdit").style.display = "none";
});

// modalDetail 닫기 버튼 이벤트
document.querySelector("#modalDetail #closeDetailModal").addEventListener("click", function (evt) {
    evt.preventDefault();
    document.querySelector("#modalDetail").style.display = "none";
});


//수정 버튼 클릭시 수정 모달에 상세조회 정보 출력
document.querySelector("#edit-button").addEventListener("click",function(){

    const productNo = document.querySelector("#productno1").value;
    const price = document.querySelector("#price1").value;
    const productName = document.querySelector("#product-name1").value;
    const serialNumber = document.querySelector("#serial-no1").value;
    const defectiveCode = document.querySelector("#defectivecode-select").value;
    const defectiveName = document.querySelector("#defective-name1").value;
    const description = document.querySelector("#textarea1").value;
    

    document.querySelector("#edit-productno").value = productNo;
    document.querySelector("#edit-price").value = price;
    document.querySelector("#edit-productname").value = productName;
    document.querySelector("#defectivecode-select-edit").value = defectiveCode;
    document.querySelector("#edit-serialNumber").value = serialNumber;
    document.querySelector("#edit-defectivename").value = defectiveName;
    document.querySelector("#edit-text-area").value = description;
    
});


function setDefectiveCodeSelect(){
    const codeSelect= document.querySelector("#defectivecode-select");
    
    $.ajax({
        url : "/qa/defective/dclist",
        method : "GET",
        // data: ~~~,
        success: function(defectiveCodeList){
            console.log("defectiveCodeList" , defectiveCodeList);

            for(let i = 0 ; i < defectiveCodeList.length; ++i){
                const defectiveCodeVo = defectiveCodeList[i];
                const optionTag = document.createElement("option");
                optionTag.setAttribute("value" , defectiveCodeVo.no);
                optionTag.innerText = defectiveCodeVo.no
                codeSelect.appendChild(optionTag);

    }
        },
        fail: function(){
            console.log("통신 실패...")
        },

    });

    
}

window.onload = function(){
    setDefectiveCodeSelect();
    setDefectiveCodeSelectEdit();
}


document.querySelector("#defectivecode-select").addEventListener("change", function () {

        const selectedCode = this.value;
    
        $.ajax({
            url: `/qa/defective/getDefectiveName?code=${selectedCode}`,
            method: "GET",
            success: function (x) {
                document.querySelector("#dfdefective-name").value = x.name;
            },
            error: function () {
                alert("불량명을 불러오지 못했습니다...");
            }
        });
     
});



function setDefectiveCodeSelectEdit(){
    const codeSelect= document.querySelector("#defectivecode-select-edit");
    
    $.ajax({
        url : "/qa/defective/dclist",
        method : "GET",
        // data: ~~~,
        success: function(defectiveCodeList){

            for(let i = 0 ; i < defectiveCodeList.length; ++i){
                const defectiveCodeVo = defectiveCodeList[i];
                const optionTag = document.createElement("option");
                optionTag.setAttribute("value" , defectiveCodeVo.no);
                optionTag.innerText = defectiveCodeVo.no
                codeSelect.appendChild(optionTag);

    }
        },
        fail: function(){
            console.log("통신 실패...")
        },

    });

    
}

document.querySelector("#defectivecode-select-edit").addEventListener("change", function () {

    const selectedCode = this.value;

    $.ajax({
        url: `/qa/defective/getDefectiveName?code=${selectedCode}`,
        method: "GET",
        success: function (x) {
            document.querySelector("#edit-defectivename").value = x.name;
        },
        error: function () {
            alert("불량명을 불러오지 못했습니다...");
        }
    });
 
});


document.querySelector("#modaldefectiveEdit .primary").addEventListener("click", function(){

    const productNo= document.querySelector("#edit-productno").value;
    console.log(productNo);
    const price= document.querySelector("#edit-price").value;
    console.log(price);
    const productName = document.querySelector("#edit-productname").value;
    console.log(productName);
    const serialNumber = document.querySelector("#edit-serialNumber").value;
    console.log(serialNumber);
    const defectiveCode= document.querySelector("#defectivecode-select-edit").value;
    console.log(defectiveCode);
    const defectiveName= document.querySelector("#edit-defectivename").value;
    console.log(defectiveName);
    const description = document.querySelector("#edit-text-area").value;
    console.log(description);

    $.ajax({
        url : "/qa/defective/edit",
        method : "POST",
        data: {
            no : productNo,
            price: price,
            productName : productName,
            serialNumber : serialNumber,
            defectiveCode : defectiveCode,
            defectiveName : defectiveName,
            description : description,
        },

        success: function(){
            alert("수정 성공!");
            location.reload();
        },

        error: function(){
            alert("수정 실패....")
        }
    });

})