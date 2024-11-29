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
