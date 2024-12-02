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

            console.log("defectivename : " , document.querySelector("#defectivename"));

            const name = document.querySelector("#defectivename").value;
            


            $.ajax({
                method: "POST",
                url: "/qa/defectivecode/write",
                data: {
                    name: name,
                    no: 1,
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

  

//불량코드 상세조회

$(document).ready(function () {
    // 테이블 행 클릭 이벤트
    $(".defectivecode-row").on("click", function () {
        const defectivecodeNo = $(this).data("product-no"); // 행에 저장된 상품 번호 가져오기

        $.ajax({
            url: "/qa/defectivecode/detail",
            type: "GET",
            data: { no: defectivecodeNo }, // 상품 번호 전송
            success: function (response) {
                // 응답 데이터를 모달 창에 표시
                $("#code-number").val(response.no);
                $("#code-name").val(response.name);

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