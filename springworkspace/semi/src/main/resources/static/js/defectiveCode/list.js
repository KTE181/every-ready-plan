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

  
  function closeDefectiveCodeModal() {
    const modal = document.querySelector("#modalOverlay");

    modal.classList.remove("active");
}





  //불량 코드 삭제
  function delDefectiveCode(){
  
    const defectiveCodeNoArr = [];
    const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");
  
    for(const checkbox of checkBoxArr){
        if(checkbox.checked == false){
            continue;
        }
        const defectiveCodeNo = checkbox.parentNode.parentNode.children[1].innerText;
        defectiveCodeNoArr.push(defectiveCodeNo);
    }
  
    $.ajax({
        url: "/qa/defectivecode/delete",
        method: "delete",
        data: {
          defectiveCodeNoArr : JSON.stringify(defectiveCodeNoArr)
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
  

  //불량 코드 등록
  document.addEventListener("DOMContentLoaded", function () {
    const registerButton = document.querySelector("#registerDefectiveCode");

        registerButton.addEventListener("click", function () {

            const name = document.querySelector("#defectivename").value;
            


            $.ajax({
                method: "POST",
                url: "/qa/defectivecode/write",
                data: {
                    name: name,
                    no: no,
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

  
let no = "";


//불량 코드 상세 조회
document.querySelectorAll("#defectiveCodeTable td").forEach(row => {
    row.addEventListener("click", function (evt) {
        const defectiveCodeNo = this.getAttribute("data-product-no"); 
        no = evt.target.parentNode.children[1].innerText;

        $.ajax({
            url: `/qa/defectivecode/detail?no=${defectiveCodeNo}`,
            method: "GET",
            success: function (x) {

                document.querySelector("#code-number").value = x.no;
                document.querySelector("#code-name").value = x.name;
                

                document.querySelector("#modalDetail").style.display = "block";
            },
            error: function () {
                alert("상품 조회 실패..");
            }
        });
    });
});




document.querySelector("#closeDefectiveCodeDetailModal").addEventListener("click", function () {
    document.querySelector("#modalDetail").style.display = "none";
});



// 수정 버튼 클릭 이벤트
document.querySelector("#edit-button-defectivecode").addEventListener("click", function () {
    document.querySelector("#modalDetail").style.display = "none";
    document.querySelector("#modalEdit").style.display = "block";
});

document.querySelector("#modalEdit #closeDefectiveCodeUpdateModal").addEventListener("click", function (evt) {
    evt.preventDefault();
    document.querySelector("#modalEdit").style.display = "none";
});

// modalDetail 닫기 버튼 이벤트
document.querySelector("#modalDetail #closeDefectiveCodeDetailModal").addEventListener("click", function (evt) {
    evt.preventDefault();
    document.querySelector("#modalDetail").style.display = "none";
});


document.querySelector("#edit-button-defectivecode").addEventListener("click",function(){
    const no = document.querySelector("#code-number").value;
    const name = document.querySelector("#code-name").value;

    document.querySelector("#edit-code-number").value = no;
    document.querySelector("#edit-code-name").value = name;
    
});







// 불량 상품 수정
document.querySelector("#modalEdit .primary").addEventListener("click", function () {

    const defectiveNo= document.querySelector("#edit-code-number").value;
    const defectiveCode= document.querySelector("#edit-code-name").value;



$.ajax({
    url: "/qa/defectivecode/edit", 
    method: "POST",
    data: {

        no : defectiveNo,
        name : defectiveCode,

    },
    success: function () {
        alert("수정되었습니다!");
        location.reload();
    },
    error: function () {
        alert("수정에 실패했습니다...");
    }
});
});