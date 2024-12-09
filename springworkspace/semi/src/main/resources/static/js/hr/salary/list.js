// 모달 요소 가져오기
const salarymodal = document.getElementById('salarymodal');
const testDiv3 = document.querySelector('#create');
const closeSalarymodal = document.querySelector('.salarymodal-close');


const alldata = "";
// "TEST" div 클릭 시 모달 열기
testDiv3.addEventListener('click', () => {

  const empNo = document.querySelector("input[name=empNo]");
  const ename = document.querySelector("input[name=name]");
  const dname = document.querySelector("input[name=dname]");
  const panme = document.querySelector("input[name=pname]");
  const salary = document.querySelector("input[name=salary]");
  const basic = document.querySelector(".salarymodal-cont input[name=basic]");
  const payment = document.querySelector(".salarymodal-second input[name=payment]");
  const nationalPension = document.querySelector(".salarymodal-third input[name=nationalPension]");
  const healthInsurance = document.querySelector(".salarymodal-third input[name=healthInsurance]");
  const employmentInsurance = document.querySelector(".salarymodal-third input[name=employmentInsurance]");
  const longtermCareInsurance = document.querySelector(".salarymodal-third input[name=longtermCareInsurance]");
  const incomeTax = document.querySelector(".salarymodal-third input[name=incomeTax]");
  const localTaxes = document.querySelector(".salarymodal-third input[name=localTaxes]");
  const deductions = document.querySelector(".salarymodal-third input[name=deductions]");
  const netPayment = document.querySelector(".salarymodal-third input[name=netPayment]");


  empNo.removeAttribute("value");
  ename.removeAttribute("value");
  dname.removeAttribute("value");
  panme.removeAttribute("value");
  salary.removeAttribute("value");
  basic.value = "";
  payment.value = "";
  nationalPension.value = "";
  healthInsurance.value = "";
  employmentInsurance.value = "";
  longtermCareInsurance.value = "";


  localTaxes.value = "";
  deductions.value = "";
  netPayment.value = "";
  // console.log(salarymodal);


  salarymodal.style.display = 'block'; // 모달 표시
});

// "X" 버튼 클릭 시 모달 닫기
closeSalarymodal.addEventListener('click', () => {
  salarymodal.style.display = 'none'; // 모달 숨기기
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

let target = "";
empNo.addEventListener("change", (event) => {
  target = event.target.value; // 현재 입력된 값
  // console.log(target);
});


//사번을 가져오는것





function changeEmpNo(element) {


  //사원 기본정보
  const empNo = document.querySelector(".salarymodal-cont input[name=empNo]");
  const ename = document.querySelector(".salarymodal-cont input[name=name]");
  const dname = document.querySelector(".salarymodal-cont input[name=dname]");
  const panme = document.querySelector(".salarymodal-cont input[name=pname]");
  const salary = document.querySelector(".salarymodal-cont input[name=salary]");
  const basic = document.querySelector(".salarymodal-cont input[name=basic]");

  //통신비 식대 고정 ~
  const communicationCost = document.querySelector(".salarymodal-second input[name=communicationCost]");
  const mealAllowance = document.querySelector(".salarymodal-second input[name=mealAllowance]");


  //계산에 의한 지급총액 
  const payment = document.querySelector(".salarymodal-second input[name=payment]");


  //계산 식에 의한 데이터 추가용
  const nationalPension = document.querySelector(".salarymodal-third input[name=nationalPension]");
  const healthInsurance = document.querySelector(".salarymodal-third input[name=healthInsurance]");
  const employmentInsurance = document.querySelector(".salarymodal-third input[name=employmentInsurance]");
  const longtermCareInsurance = document.querySelector(".salarymodal-third input[name=longtermCareInsurance]");
  const incomeTax = document.querySelector(".salarymodal-third input[name=incomeTax]");
  const localTaxes = document.querySelector(".salarymodal-third input[name=localTaxes]");
  const deductions = document.querySelector(".salarymodal-third input[name=deductions]");
  const netPayment = document.querySelector(".salarymodal-third input[name=netPayment]");

  const npercentage = 4.3;
  const hpercentage = 3.2;
  const epercentage = 0.7;
  const lpercentage = 0.32;
  const itpercentage = 1.4;
  const ltpercentage = 0.14;



  var selectempNo = element.textContent || element.innerText;


  // console.log("클릭된 사원 번호:", selectempNo);

  $.ajax({
    url: "/api/hr/vacation/getEmployeeData", // 서버 엔드포인트
    method: "POST", // 요청 방식
    data: ({ empNo: selectempNo }), // 전송할 데이터
    success: function (data) {
      // console.log("서버 응답 데이터:", data);
      
      empNo.setAttribute("value", data.no);
      ename.setAttribute("value", data.name);
      dname.setAttribute("value", data.dname);
      panme.setAttribute("value", data.pname);
      salary.setAttribute("value", data.salary);
      basic.value = Math.round((data.salary / 12)/10)*10;

      const basicValue = parseInt(basic.value, 10) || 0; // 기본값 0 설정
      const communicationCostValue = parseInt(communicationCost.getAttribute("value"), 10) || 0;
      const mealAllowanceValue = parseInt(mealAllowance.getAttribute("value"), 10) || 0;

      const total1 = basicValue + communicationCostValue + mealAllowanceValue;
      payment.value = total1;


      nationalPension.value = Math.round((total1 * (npercentage / 100)) / 10) * 10;
      healthInsurance.value = Math.round((total1 * (hpercentage / 100)) / 10) * 10;
      employmentInsurance.value = Math.round((total1 * (epercentage / 100)) / 10) * 10;
      longtermCareInsurance.value = Math.round((total1 * (lpercentage / 100)) / 10) * 10;
      incomeTax.value = Math.round((total1 * (itpercentage / 100)) / 10) * 10;
      localTaxes.value = Math.round((total1 * (ltpercentage / 100)) / 10) * 10;


      const nationalPensionValue = parseInt(nationalPension.value, 10) || 0;
      const healthInsuranceValue = parseInt(healthInsurance.value, 10) || 0;
      const employmentInsuranceValue = parseInt(employmentInsurance.value, 10) || 0;
      const longtermCareInsuranceValue = parseInt(longtermCareInsurance.value, 10) || 0;
      const incomeTaxValue = parseInt(incomeTax.value, 10) || 0;
      const localTaxesValue = parseInt(localTaxes.value, 10) || 0;

      const total2 = Math.round((nationalPensionValue + healthInsuranceValue + employmentInsuranceValue + longtermCareInsuranceValue + incomeTaxValue + localTaxesValue) / 10) * 10;
      deductions.value = total2;



      netPayment.value = total1 - total2;


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

  const selectNo = evt.target.parentNode.children[1].innerText;
  // console.log(selectNo);

  $.ajax({
    url: `/api/hr/salary/detail`,
    method: "POST",
    data: ({
      selectNo,
    }),
    success: function (data) {
      // console.log("통신성공");
      document.querySelector(".salaryselectmodal .btn-class").innerHTML = '';


      // 모달 요소 가져오기
      const salaryselectmodal = document.getElementById('salaryselectmodal');
      const closeSalaryselectmodal = document.querySelector('.salaryselectmodal-close');



      //모달에다가 값 가져오기

      const empNo = document.querySelector(".salaryselectmodal-cont input[name=empNo]");
      const selectbtn = document.querySelector(".salaryselectmodal-cont .employee-select-btn");
      const ename = document.querySelector(".salaryselectmodal-cont input[name=name]");
      const dname = document.querySelector(".salaryselectmodal-cont input[name=dname]");
      const pname = document.querySelector(".salaryselectmodal-cont input[name=pname]");
      const salary = document.querySelector(".salaryselectmodal-cont input[name=salary]");
      const basic = document.querySelector(".salaryselectmodal-cont input[name=basic]");
      const payment = document.querySelector(".salaryselectmodal-second input[name=payment]");
      const mealAllowance = document.querySelector(".salaryselectmodal-second input[name=mealAllowance]");
      const communicationCost = document.querySelector(".salaryselectmodal-second input[name=communicationCost]");
      const payYearmonth = document.querySelector(".salaryselectmodal-second input[name=payYearmonth]");
      const nationalPension = document.querySelector(".salaryselectmodal-third input[name=nationalPension]");
      const healthInsurance = document.querySelector(".salaryselectmodal-third input[name=healthInsurance]");
      const employmentInsurance = document.querySelector(".salaryselectmodal-third input[name=employmentInsurance]");
      const longtermCareInsurance = document.querySelector(".salaryselectmodal-third input[name=longtermCareInsurance]");
      const incomeTax = document.querySelector(".salaryselectmodal-third input[name=incomeTax]");
      const localTaxes = document.querySelector(".salaryselectmodal-third input[name=localTaxes]");
      const deductions = document.querySelector(".salaryselectmodal-third input[name=deductions]");
      const netPayment = document.querySelector(".salaryselectmodal-third input[name=netPayment]");



      empNo.setAttribute("value", data.empNo);
      ename.value = data.ename;
      dname.value = data.dname;
      pname.value = data.pname;
      salary.value = data.salary;
      basic.value = data.basic;
      payment.value = data.payment;
      payYearmonth.value = data.payYearmonth;
      nationalPension.value = data.nationalPension;
      healthInsurance.value = data.healthInsurance;
      employmentInsurance.value = data.employmentInsurance;
      longtermCareInsurance.value = data.longtermCareInsurance;
      incomeTax.value = data.incomeTax;
      localTaxes.value = data.localTaxes;
      deductions.value = data.deductions;
      netPayment.value = data.netPayment;




      empNo.setAttribute("disabled", "false");
      ename.setAttribute("disabled", "false");
      dname.setAttribute("disabled", "false");
      pname.setAttribute("disabled", "false");
      salary.setAttribute("disabled", "false");
      selectbtn.setAttribute("disabled", "false");
      basic.setAttribute("disabled", "false");
      payment.setAttribute("disabled", "false");
      payYearmonth.setAttribute("disabled", "false");
      communicationCost.setAttribute("disabled", "false");
      mealAllowance.setAttribute("disabled", "false");
      nationalPension.setAttribute("disabled", "false");
      healthInsurance.setAttribute("disabled", "false");
      employmentInsurance.setAttribute("disabled", "false");
      longtermCareInsurance.setAttribute("disabled", "false");
      incomeTax.setAttribute("disabled", "false");
      localTaxes.setAttribute("disabled", "false");
      deductions.setAttribute("disabled", "false");
      netPayment.setAttribute("disabled", "false");










      const btnDivTag = document.querySelector(".btn-class");
      const editbtn = document.createElement("input");
      editbtn.setAttribute("type", "button");
      editbtn.setAttribute("value", "수정하기");
      const delbtn = document.createElement("input");
      delbtn.setAttribute("type", "button");
      delbtn.setAttribute("value", "삭제하기");
      btnDivTag.appendChild(editbtn);
      btnDivTag.appendChild(delbtn);


      salaryselectmodal.style.display = 'block'; // 모달 표시







      // "X" 버튼 클릭 시 모달 닫기
      closeSalaryselectmodal.addEventListener('click', () => {
        salaryselectmodal.style.display = 'none'; // 모달 숨기기
      });

      editbtn.addEventListener("click", () => {

        
       
        mealAllowance.removeAttribute("disabled");
       
        payYearmonth.removeAttribute("disabled");
        nationalPension.removeAttribute("disabled");
        healthInsurance.removeAttribute("disabled");
        employmentInsurance.removeAttribute("disabled");
        longtermCareInsurance.removeAttribute("disabled");
        incomeTax.removeAttribute("disabled");
        localTaxes.removeAttribute("disabled");
        
        

        editbtn.remove();
        delbtn.remove();
        const editbtn2 = document.createElement("input");
        editbtn2.setAttribute("type", "button");
        editbtn2.setAttribute("value", "수정하기");
        btnDivTag.appendChild(editbtn2);

        editbtn2.addEventListener("click", () => {

          const editdata = {
            no:selectNo,
            empNo:empNo.value,
            payYearmonth:payYearmonth.value,
            nationalPension:nationalPension.value,
            healthInsurance:healthInsurance.value,
            employmentInsurance:employmentInsurance.value,
            longtermCareInsurance:longtermCareInsurance.value,
            mealAllowance:mealAllowance.value,
            communicationCost:communicationCost.value,
            incomeTax:incomeTax.value,
            localTaxes:localTaxes.value,
        
          };

          $.ajax({


            url: "/api/hr/salary/edit",
            method: "POST",
            data: (editdata),
            success: function (data) {
  
  
              // console.log("성공");
              // console.log(data);

             
              location.href="/api/hr/salary/list";
  
  
            },
            fail: function () { }
          })

        })
       


      })
      delbtn.addEventListener("click",()=>{

        $.ajax({
          url:"/api/hr/salary/del",
          method:"POST",
          data:({
            no:selectNo,
          }),
          success:function(killdata){
              
                location.reload();
                
          },
          error:function(){

          }
          

        })
      })
      
   



    },
    fail: function () {

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

          // console.log(CheckboxArr[i].value);
          delallData[CheckboxArr[i].value] = CheckboxArr[i].value;
          // [ 안 ] 안에는 선언이 되어있는 변수면 변수의 값을가져오고
          // 선언이 안되어있는 새로운 키값을 사용하려면 "" 문자열로써 사용하면된다.
          //객체 안에       키값에다가          벨류값을 집어넣는다
          // 이 변수명에 동적으로 키이름을 만들고 벨류값을 집어넣어서 객체로써 동작한다.
          dataArr.push(CheckboxArr[i].value);
      }
    }
    // console.log(delallData);
    // 최종적으로 완성된 키:벨류 값의 배열  
    // console.log(dataArr);
    if(dataArr.length==0){
      alert("삭제할 게시글 체크박스를 선택해주세요");
      return;
    }
    const chooseOne = confirm("삭제하시겠습니까?");
    if(chooseOne){
 

    $.ajax({
      url:"/api/hr/salary/del",
      method:"delete",
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(dataArr),
      success:function(data){
              // console.log(data); 
              location.reload();
      },
      fail:function(){

      }


  })
}else{
  return;
}
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
          // console.log("통신성공");
          // console.log(data);
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
 
  // console.log(searchEmpNo.value);
  // console.log(searchEname.value);
  
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
        // console.log("통신성공");
        // console.log(data);
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


