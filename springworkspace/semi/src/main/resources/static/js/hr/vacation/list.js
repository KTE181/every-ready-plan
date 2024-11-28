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
