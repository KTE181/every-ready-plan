// 모달 요소 가져오기
const vacationmodal = document.getElementById('vacationmodal');
const testDiv3 = document.querySelector('#create');
const closeVacationmodal = document.querySelector('.vacationmodal-close');

// "TEST" div 클릭 시 모달 열기
testDiv3.addEventListener('click', () => {

    const empNo = document.querySelector("input[name=empNo]");
    const ename = document.querySelector("input[name=name]");
    const dname = document.querySelector("input[name=dname]");
    const panme = document.querySelector("input[name=pname]");
    empNo.setAttribute("value","");
    ename.setAttribute("value","");
    dname.setAttribute("value","");
    panme.setAttribute("value","");
    console.log(vacationmodal);
    vacationmodal.style.display = 'block'; // 모달 표시
});

// "X" 버튼 클릭 시 모달 닫기
closeVacationmodal.addEventListener('click', () => {
    vacationmodal.style.display = 'none'; // 모달 숨기기
});

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



    const empNo = document.querySelector("input[name=empNo]");

    let target="";
    empNo.addEventListener("change", (event) => {
         target = event.target.value; // 현재 입력된 값
         console.log(target);
    });


//사번을 가져오는것





    function changeEmpNo(element) {


      const empNo = document.querySelector("input[name=empNo]");
      const ename = document.querySelector("input[name=name]");
      const dname = document.querySelector("input[name=dname]");
      const panme = document.querySelector("input[name=pname]");




      var selectempNo = element.textContent || element.innerText;


      console.log("클릭된 사원 번호:", selectempNo);

      $.ajax({
        url: "/api/hr/vacation/getEmployeeData", // 서버 엔드포인트
        method : "POST", // 요청 방식
        data:({ empNo : selectempNo }), // 전송할 데이터
        success: function (data) {
            console.log("서버 응답 데이터:", data);
                console.log(data);
                
                        empNo.setAttribute("value",data.no);
                        ename.setAttribute("value",data.name);
                        dname.setAttribute("value",data.dname);
                        panme.setAttribute("value",data.pname);

        },
        error: function (xhr, status, error) {
            console.error("AJAX 요청 중 오류 발생:", error);
        },
    });
    btnmodal.style.display = 'none';
      return false;
  }



const tbodyTag = document.querySelector(".list-area tbody");

tbodyTag.addEventListener("click",(evt)=>{
    if(evt.target.tagName != "TD"){return;}
   
    const selectNo = evt.target.parentNode.children[1].innerText;


    $.ajax({
      url:`/api/hr/vacation/detail?no=${selectNo}`,
      method:"POST",
      data:({
        selectNo: selectNo,
      }),
      success: function(data){
        console.log(data);
        
        document.querySelector(".vacationselectmodal-content .button-container").innerHTML = '';

        
        const vacationselectmodal = document.getElementById('vacationselectmodal');
        const closeVacationselectmodal = document.querySelector('.vacationselectmodal-close');

        const empNo= document.querySelector(".vacationselectmodal-cont > div > input[name=empNo]");
        const ename = document.querySelector(".vacationselectmodal-cont input[name=name]");
        const dname = document.querySelector(".vacationselectmodal-cont  input[name=dname]");
        const pname = document.querySelector(".vacationselectmodal-cont input[name=pname]");
        const thisDate = document.querySelector(".vacationselectmodal-cont input[name=thisDate]");
        const code = document.querySelector(".vacationselectmodal-cont select[name=code]");
        const reason = document.querySelector(".vacationselectmodal-cont textarea[name=reason]");
        const employeebtn = document.querySelector(".vacationselectmodal-cont input[value='사번선택']");
        
      
        empNo.value =data.empNo;
        ename.value=data.ename;
        dname.value=data.dname;
        pname.value=data.pname;
        thisDate.value=data.thisDate;
        code.setAttribute("value",data.code);
        reason.value=data.reason;

        empNo.setAttribute("disabled","false");
        ename.setAttribute("disabled","false");
        dname.setAttribute("disabled","false");
        pname.setAttribute("disabled","false");
        code.setAttribute("disabled","false");
        thisDate.setAttribute("disabled","false");
        reason.setAttribute("disabled","false");
        employeebtn.setAttribute("disabled","false");

        

        const btnDiv =document.querySelector(".vacationselectmodal-content .button-container");

        const editBtn =document.createElement("input");
        editBtn.setAttribute("type","button");
        editBtn.setAttribute("value","수정하기");

        btnDiv.appendChild(editBtn);


        const delbtn = document.createElement("input");
        delbtn.setAttribute("type","button");
        delbtn.setAttribute("value","삭제하기");

        btnDiv.appendChild(delbtn);

       
        editBtn.addEventListener("click",()=>{
        
         
          code.removeAttribute("disabled");
          thisDate.removeAttribute("disabled");
          reason.removeAttribute("disabled");
         


          editBtn.remove();
          delbtn.remove();

          const btnDiv =document.querySelector(".vacationselectmodal-content .button-container");
           const editBtn2 =document.createElement("input");
           editBtn2.setAttribute("type","button");
           editBtn2.setAttribute("value","수정하기");

         
           btnDiv.appendChild(editBtn2);

           editBtn2.addEventListener("click",()=>{

            const x = document.querySelector(".vacationselectmodal-cont textarea[name=reason]");

            const alldata = {
              selectNo:selectNo,
              empNo:empNo.value,
              ename:ename.value,
              dname:dname.value,
              pname:pname.value,
              code:code.value,
              thisDate:thisDate.value,
              reason:reason.value,
            }

            $.ajax({
              url:"/api/hr/vacation/update",
              contentType: "application/json",
              method:"POST",
              data:JSON.stringify(alldata),
  
              success:function(data2){
                  alert("수정 성공");
                  console.log(data2);
                  location.href="/api/hr/vacation/list";
                  
                  
              },
              fail:function(){
  
              }
  
             })
           })
           
           

          


        })
        delbtn.addEventListener("click",()=>{

          $.ajax({
            url:"/api/hr/vacation/del",
            method:"POST",
            data:({
              no:selectNo,
            }),
            success:function(killdata){
                  alert("삭제 되었습니다.")
                  location.reload();
                  
            },
            error:function(){
  
            }
            
  
          })
        })


             
         
        
        




        vacationselectmodal.style.display = 'block'; // 모달 표시
         // "X" 버튼 클릭 시 모달 닫기
          closeVacationselectmodal.addEventListener('click', () => {
            vacationselectmodal.style.display = 'none'; // 모달 숨기기
        });
        

      },
      fail: function(){

      }

    })
    



    
    });
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
      
     
    
        $.ajax({
          url:"/api/hr/vacation/del",
          method:"delete",
          contentType: 'application/json; charset=utf-8',
          data: JSON.stringify(dataArr),
          success:function(data){
                  console.log(data); 
                  alert("삭제완료");
                  location.reload();
          },
          fail:function(){
    
          }
    
    
      })
    }
    


    function loadPage(pageNumber) {
      // 검색 값이 있다면 함께 보내도록 처리
      // const searchValue = document.querySelector('input[name="searchValue"]').value;
      console.log(pageNumber);
      console.log(pageNumber.dataset.page);
      
    
      $.ajax({
          url: `/api/hr/vacation/getEmplistdata`,  // 서버에서 페이징 데이터를 가져오는 엔드포인트
          method: 'GET',  // GET 요청
          data: {
              pno: pageNumber.dataset.page  ,  // 페이지 번호
              // searchValue: searchValue  // 검색값 (필요한 경우)
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
   
   

