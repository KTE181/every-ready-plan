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
    
    const defaultOption = document.createElement("option");
    defaultOption.setAttribute("value", "");
    defaultOption.innerText = "불량코드";
    codeSelect.appendChild(defaultOption);

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


function productList() {

    const productModal = document.querySelector("#product-modal");
    const searchForm = document.querySelector("#product-search-form");
    const closeModal = document.querySelector("#product-modal .modal-close");
    searchForm.reset();
    productModal.style.display = 'block'; 

    closeModal.addEventListener('click', () => {
        productModal.style.display = 'none';
    } , { once: true });

    const pno = 1;
    
    productData(pno);
    
}

// 상품 검색 데이터 요청 
function productData(pno) {

    const tbodyTag = document.querySelector("#product-modal table>tbody");
    const productSearchType = document.querySelector("#product-modal select[name=productSearchType]").value;
    const productSearchValue = document.querySelector("#product-modal input[name=productSearchValue]").value;

    $.ajax({
        url : "/qa/asreq/productlist",
        data : {
            pno ,
            productSearchType ,
            productSearchValue
        } ,
        method : "GET" ,
        success : function(m){

            const productVoList = m.a;
            const pvo = m.b;
            paintPageArea(pvo);

            tbodyTag.innerHTML = ""; 

            for(const vo of productVoList) {

                const trTag = document.createElement("tr");
                tbodyTag.appendChild(trTag); 

                const tdTag = document.createElement("td");
                const radioTag = document.createElement("input");
                radioTag.setAttribute("type", "radio");
                radioTag.setAttribute("name", "product-radio-btn");
                const noTag = document.createElement("input");
                noTag.setAttribute("type", "hidden");
                noTag.value = vo.no;

                trTag.appendChild(tdTag);
                tdTag.appendChild(radioTag);
                tdTag.appendChild(noTag);

                const tdTag1 = document.createElement("td");
                tdTag1.innerText = vo.serialNumber;
                trTag.appendChild(tdTag1);

                const tdTag2 = document.createElement("td");
                tdTag2.innerText = vo.name;
                trTag.appendChild(tdTag2);

                const tdTag3 = document.createElement("td");
                tdTag3.innerText = vo.price;
                trTag.appendChild(tdTag3);

                const tdTag4 = document.createElement("td");
                tdTag4.innerText = vo.warrantyPeriod;
                trTag.appendChild(tdTag4);
            }
        } , 
        error : function(){
            alert("조회 실패...")
        }
    })

}

// 상품 검색 모달 페이징
function paintPageArea(pvo){
    const pageArea = document.querySelector(".page-area");
    pageArea.innerHTML = "";

    // 이전 버튼
    if(pvo.startPage != 1) {
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `productData(${pvo.startPage-1});`);
        spanTag.innerText = "이전";
        pageArea.appendChild(spanTag);
    }

    // 페이지 버튼
    for(let i=pvo.startPage; i<=pvo.endPage; i++){
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `productData(${i});`);
        spanTag.innerText = i;
        pageArea.appendChild(spanTag);
    }

    // 다음 버튼
    if(pvo.endPage != pvo.maxPage){
        const spanTag = document.createElement("span");
        spanTag.setAttribute("onclick", `productData(${pvo.endPage+1});`);
        spanTag.innerText = "다음";
        pageArea.appendChild(spanTag);
    }
}

// 상품 선택 처리
function selectProduct() {

    const selectedProduct = document.querySelector("#product-modal input[type=radio]:checked");
    if (selectedProduct === null) {
        alert("상품이 선택되지 않았습니다.");
        return;
    }

    const no = selectedProduct.nextSibling.value;
    const serialNumber = selectedProduct.parentNode.parentNode.children[1].innerText;
    const productName = selectedProduct.parentNode.parentNode.children[2].innerText;
    const price = selectedProduct.parentNode.parentNode.children[3].innerText;

    console.log(no);
    console.log(serialNumber);
    console.log(productName);

    document.querySelector("#modalOverlay input[id=dfproductno]").value = no;
    document.querySelector("#modalOverlay input[id=dfserialno]").value = serialNumber;
    document.querySelector("#modalOverlay input[id=dfproduct-name]").value = productName;
    document.querySelector("#modalOverlay input[id=dfprice]").value = price;

    const productModal = document.querySelector("#product-modal");
    productModal.style.display = 'none';

}



