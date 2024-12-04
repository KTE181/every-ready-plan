// 초과근무 등록 모달창 ------------------------------------

// 모달 요소 가져오기
const overmodal1 = document.getElementById('overmodal1');
const testDiv1 = document.querySelector('#create');
const closeOverModal1 = document.querySelector('.overmodal1-close');

// "TEST" div 클릭 시 모달 열기
testDiv1.addEventListener('click', () => {
  console.log(overmodal1);
  overmodal1.style.display = 'block'; // 모달 표시
});

// "X" 버튼 클릭 시 모달 닫기
closeOverModal1.addEventListener('click', () => {
  overmodal1.style.display = 'none'; // 모달 숨기기
});
// 사원검색 모달창 ------------------------------------
const btnmodal = document.getElementById('btnmodal');
const closeBtnmodal = document.querySelector('.btnmodal-close');
const btns = document.querySelectorAll('.employee-select-btn'); // 모든 버튼 선택

// 각 버튼에 이벤트 리스너 추가
btns.forEach((btn) => {
  btn.addEventListener('click', () => {
    btnmodal.style.display = 'block'; // 모달 표시
  });
});

// "X" 버튼 클릭 시 모달 닫기
closeBtnmodal.addEventListener('click', () => {
  btnmodal.style.display = 'none'; // 모달 숨기기
});



function changeEmpNo(element) {


  const empNo = document.querySelector("input[name=empNo]");
  const ename = document.querySelector("input[name=name]");
  const dname = document.querySelector("input[name=dname]");
  const pname = document.querySelector("input[name=pname]");




  var selectempNo = element.textContent || element.innerText;


  console.log("클릭된 사원 번호:", selectempNo);

  $.ajax({
    url: "/api/hr/overtime/getEmployeeData", // 서버 엔드포인트
    method: "POST", // 요청 방식
    data: ({ empNo: selectempNo }), // 전송할 데이터
    success: function (data) {
      console.log("서버 응답 데이터:", data);
      empNo.setAttribute("value", data.no);
      ename.setAttribute("value", data.name);
      dname.setAttribute("value", data.dname);
      pname.setAttribute("value", data.pname);

    },
    error: function (xhr, status, error) {
      console.error("AJAX 요청 중 오류 발생:", error);
    },
  });
  btnmodal.style.display = 'none';
  return false;
}
const tbodyTag = document.querySelector(".list-area tbody");

tbodyTag.addEventListener("click", (evt) => {
  if (evt.target.tagName != "TD") { return; }

  const overmodal2 = document.querySelector('.overselectmodal1');

  
  const closeOverModal2 = document.querySelector('.overselectmodal1-close');
  const no = evt.target.parentNode.children[1].innerText;
 
  
  overmodal2.style.display = 'block'; // 모달 표시


  closeOverModal2.addEventListener('click', () => {
    overmodal2.style.display = 'none'; // 모달 숨기기
  });

  $.ajax({
    url: "/api/hr/overtime/detail",
    method: "POST",
    data: ({
      no
    }),
    success: function (data) {
      document.querySelector(".overselectmodal1-content .button-container").innerHTML = '';
      console.log(data);
      const empNo = document.querySelector(".overselectmodal1-cont input[name=empNo]");
      const ename = document.querySelector(".overselectmodal1-cont input[name=name]");
      const dname = document.querySelector(".overselectmodal1-cont input[name=dname]");
      const pname = document.querySelector(".overselectmodal1-cont input[name=pname]");
      const thisDate = document.querySelector(".overselectmodal1 input[name=thisDate]");
      const type = document.querySelector(`.overselectmodal1 input[name=type][value="${data.type}`);
      const typeAll = document.querySelectorAll(`.overselectmodal1 input[name=type]`);
      const hour = document.querySelector(".overselectmodal1 select[name=hour]");
      const minute = document.querySelector(".overselectmodal1 select[name=minute]");
      const selectbnt = document.querySelector(".overselectmodal1 .employee-select-btn");

      console.log(typeAll);

      
      const formattedHour = Number(hour);
      
      empNo.setAttribute("value",data.empNo);
      ename.setAttribute("value",data.name);
      dname.setAttribute("value",data.dname);
      pname.setAttribute("value",data.pname);
      thisDate.setAttribute("value",data.thisDate);
      type.checked ="true";
      hour.value=data.hour;
      minute.value=data.minute;


      empNo.setAttribute("disabled","flase");
      selectbnt.setAttribute("disabled","flase");
      ename.setAttribute("disabled","flase");
      dname.setAttribute("disabled","flase");
      pname.setAttribute("disabled","flase");
      thisDate.setAttribute("disabled","flase");
      for (let i = 0; i < typeAll.length; i++) {
        typeAll[i].disabled = true;
       }
     
      hour.setAttribute("disabled","flase");
      minute.setAttribute("disabled","flase");
     
      const btnTag = document.querySelector(".overselectmodal1 .button-container");

      console.log(btnTag);
      
      const editBtn = document.createElement("button");
      editBtn.innerText="수정하기";
      btnTag.appendChild(editBtn);

      const delBtn = document.createElement("button");
      delBtn.innerText="삭제하기";
      btnTag.appendChild(delBtn);


      delBtn.addEventListener("click",()=>{

        $.ajax({
          url:"/api/hr/overtime/del",
          method:"POST",
          data:({
            no:no,
          }),
          success:function(killdata){
                location.reload();
                
          },
          error:function(){

          }
          

        })
      })
      

      editBtn.addEventListener("click",()=>{
      thisDate.removeAttribute("disabled","flase");
      for (let i = 0; i < typeAll.length; i++) {
        typeAll[i].removeAttribute("disabled","flase");
       }
      hour.removeAttribute("disabled","flase");
      minute.removeAttribute("disabled","flase");

      editBtn.remove();
      delBtn.remove();






      const editBtn2 = document.createElement("button");
      editBtn2.innerText="수정하기";
      btnTag.appendChild(editBtn2);

      

      

      editBtn2.addEventListener("click",()=>{
        const selectedRadioValue = document.querySelector('input[name="type"]:checked')?.value;
        const alldata= {
          no:no,
          thisDate:thisDate.value,
          type:selectedRadioValue,
          hour:hour.value,
          minute:minute.value
        };
        $.ajax({
          url:"/api/hr/overtime/edit",
          method:"POST",
          
          data:(alldata),
          success:function(data){
            console.log("통신성공");
            location.reload();
            
            
          },
          error:function(){}

        })
      })

      })

    },
    error: function () {

    }

  })



})
function handleCheckbox(x){
  //모든 체크박스 가져오기
  const checkBoxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");

  //모든 체크박스 체크하기
  for(let i = 0; i < checkBoxArr.length; ++i){
      checkBoxArr[i].checked = x.checked;
  }
}
function deleteNotice(){
  let delallData ={};
  const dataArr=[];
  // 빈 객체를 만들어주고 
  const CheckboxArr = document.querySelectorAll(".checkbox-td > input[type=checkbox]");
  for(let i = 0; i <CheckboxArr.length;i++){
      if(CheckboxArr[i].checked == true){

          console.log(CheckboxArr[i].value);
          delallData[CheckboxArr[i].value] = CheckboxArr[i].value;
          // [ 안 ] 안에는 선언이 되어있는 변수면 변수의 값을가져오고
          // 선언이 안되어있는 새로운 키값을 사용하려면 "" 문자열로써 사용하면된다.
          //객체 안에       키값에다가          벨류값을 집어넣는다
          // 이 변수명에 동적으로 키이름을 만들고 벨류값을 집어넣어서 객체로써 동작한다.
          dataArr.push(CheckboxArr[i].value);
      }
    }
    console.log(delallData);
    // 최종적으로 완성된 키:벨류 값의 배열  
    console.log(dataArr);
    if(dataArr.length==0){
      alert("삭제할 게시글 체크박스를 선택해주세요");
      return;
    }
 

    $.ajax({
      url:"/api/hr/overtime/del",
      method:"delete",
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(dataArr),
      success:function(data){
              console.log(data); 
              location.reload();
      },
      fail:function(){

      }


  })
}
function loadPage(pageNumber) {
  // 검색 값이 있다면 함께 보내도록 처리
  // const searchValue = document.querySelector('input[name="searchValue"]').value;
  // console.log(pageNumber);
  // console.log(pageNumber.dataset.page);
  let pno=0;
  if(pageNumber==1){

    pno = 1;
  }else{
    pno =pageNumber.dataset.page;
  }
  

  $.ajax({
      url: `/api/hr/vacation/getEmplistdata`,  // 서버에서 페이징 데이터를 가져오는 엔드포인트
      method: 'GET',  // GET 요청
      data: {
          pno: pno  ,  // 페이지 번호
         
      },
      success: function(data) {
          // 서버에서 받은 데이터로 테이블 업데이트
          
          console.log("통신성공");
          console.log(data);
          updateTable(data);
         
      },
      error: function(xhr, status, error) {
          console.error("페이징 데이터 로드 실패:", error);
      }
  });
}
function updateTable(data) {
  const tableBody = document.querySelector('.btnmodal-main table tbody');
  tableBody.innerHTML = ''; // 테이블 내용 초기화

  // 받은 데이터로 테이블 행 추가
      for(let i = 0; i<data.length;i++){
      const row = document.createElement('tr');
      row.innerHTML = `
          <td><a href="#" id="empNo_${data[i].no}" onclick="changeEmpNo(this);">${data[i].no}</a></td>
          <td>${data[i].name}</td>
          <td>${data[i].dname}</td>
          <td>${data[i].pname}</td>
      `;
      tableBody.appendChild(row);
    }
}




//조회버튼 클릭시에 요청보내고 값가져오기

const choiceBtn = document.querySelector("#searchEmpbar #choice");
const searchEmpNo = document.querySelector(".btnmodal-title input[name=searchEmpNo]");
const searchEname = document.querySelector(".btnmodal-title input[name=searchEname]");
choiceBtn.addEventListener("click",()=>{
 
  console.log(searchEmpNo.value);
  console.log(searchEname.value);
  
  if(searchEmpNo.value=='' && searchEname.value==''){
    loadPage(1);
    return;
  }

  
  $.ajax({
    url: `/api/hr/vacation/getEmplistdata`,  // 서버에서 페이징 데이터를 가져오는 엔드포인트
    method: 'POST',  // GET 요청
    data: {
      searchEmpNo:searchEmpNo.value,
      searchEname:searchEname.value, // 페이지 번호
       
    },
    success: function(data) {
        // 서버에서 받은 데이터로 테이블 업데이트
        if(data == ""){
          alert("사원의 정보가 없습니다");
          loadPage(1);
          return;
        }
        console.log("통신성공");
        console.log(data);
        updateTable2(data);
       
    },
    error: function(xhr, status, error) {
        console.error("페이징 데이터 로드 실패:", error);
    }
});
  
})
function updateTable2(data) {
  const tableBody = document.querySelector('.btnmodal-main table tbody');
  tableBody.innerHTML = ''; // 테이블 내용 초기화

  // 받은 데이터로 테이블 행 추가
      
      const row = document.createElement('tr');
      row.innerHTML = `
          <td><a href="#" id="empNo_${data.no}" onclick="changeEmpNo(this);">${data.no}</a></td>
          <td>${data.name}</td>
          <td>${data.dname}</td>
          <td>${data.pname}</td>
      `;
      tableBody.appendChild(row);
   
}

