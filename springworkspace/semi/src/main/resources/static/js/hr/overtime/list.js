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
      const panme = document.querySelector("input[name=pname]");




      var selectempNo = element.textContent || element.innerText;


      console.log("클릭된 사원 번호:", selectempNo);

      $.ajax({
        url: "/api/hr/overtime/getEmployeeData", // 서버 엔드포인트
        method : "POST", // 요청 방식
        data:({ empNo : selectempNo }), // 전송할 데이터
        success: function (data) {
            console.log("서버 응답 데이터:", data);
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
