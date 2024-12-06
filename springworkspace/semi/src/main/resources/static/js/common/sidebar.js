document.addEventListener("DOMContentLoaded", () => {
  const dropdown = document.querySelector(".menu-item.dropdown");
  const dropdownMenu = dropdown.querySelector(".dropdown-menu");
  const menuItems = dropdownMenu.querySelectorAll("li");

  dropdown.addEventListener("click", () => {
    dropdownMenu.classList.toggle("visible");
    document.querySelector(".menu-item.dropdown1").querySelector(".dropdown-menu1").classList.remove("visible");
    document.querySelector(".menu-item.dropdown2").querySelector(".dropdown-menu2").classList.remove("visible");
    document.querySelector(".menu-item.dropdown3").querySelector(".dropdown-menu3").classList.remove("visible");
    document.querySelector(".menu-item.dropdown4").querySelector(".dropdown-menu4").classList.remove("visible");
  });

  menuItems.forEach((item) => {
    item.addEventListener("click", (event) => {
      menuItems.forEach((i) => i.classList.remove("active"));
      item.classList.add("active");
    });
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const dropdown = document.querySelector(".menu-item.dropdown1");
  const dropdownMenu = dropdown.querySelector(".dropdown-menu1");
  const menuItems = dropdownMenu.querySelectorAll("li");

  dropdown.addEventListener("click", () => {
    dropdownMenu.classList.toggle("visible");
    document.querySelector(".menu-item.dropdown").querySelector(".dropdown-menu").classList.remove("visible");
    document.querySelector(".menu-item.dropdown2").querySelector(".dropdown-menu2").classList.remove("visible");
    document.querySelector(".menu-item.dropdown3").querySelector(".dropdown-menu3").classList.remove("visible");
    document.querySelector(".menu-item.dropdown4").querySelector(".dropdown-menu4").classList.remove("visible");
  });

  menuItems.forEach((item) => {
    item.addEventListener("click", (event) => {
      menuItems.forEach((i) => i.classList.remove("active"));
      item.classList.add("active");
    });
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const dropdown = document.querySelector(".menu-item.dropdown2");
  const dropdownMenu = dropdown.querySelector(".dropdown-menu2");
  const menuItems = dropdownMenu.querySelectorAll("li");

  dropdown.addEventListener("click", () => {
    dropdownMenu.classList.toggle("visible");
    document.querySelector(".menu-item.dropdown").querySelector(".dropdown-menu").classList.remove("visible");
    document.querySelector(".menu-item.dropdown1").querySelector(".dropdown-menu1").classList.remove("visible");
    document.querySelector(".menu-item.dropdown3").querySelector(".dropdown-menu3").classList.remove("visible");
    document.querySelector(".menu-item.dropdown4").querySelector(".dropdown-menu4").classList.remove("visible");
  });

  menuItems.forEach((item) => {
    item.addEventListener("click", (event) => {
      menuItems.forEach((i) => i.classList.remove("active"));
      item.classList.add("active");
    });
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const dropdown = document.querySelector(".menu-item.dropdown3");
  const dropdownMenu = dropdown.querySelector(".dropdown-menu3");
  const menuItems = dropdownMenu.querySelectorAll("li");

  dropdown.addEventListener("click", () => {
    dropdownMenu.classList.toggle("visible");
    document.querySelector(".menu-item.dropdown").querySelector(".dropdown-menu").classList.remove("visible");
    document.querySelector(".menu-item.dropdown1").querySelector(".dropdown-menu1").classList.remove("visible");
    document.querySelector(".menu-item.dropdown2").querySelector(".dropdown-menu2").classList.remove("visible");
    document.querySelector(".menu-item.dropdown4").querySelector(".dropdown-menu4").classList.remove("visible");
  });

  menuItems.forEach((item) => {
    item.addEventListener("click", (event) => {
      menuItems.forEach((i) => i.classList.remove("active"));
      item.classList.add("active");
    });
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const dropdown = document.querySelector(".menu-item.dropdown4");
  const dropdownMenu = dropdown.querySelector(".dropdown-menu4");
  const menuItems = dropdownMenu.querySelectorAll("li");

  dropdown.addEventListener("click", () => {
    dropdownMenu.classList.toggle("visible");
    document.querySelector(".menu-item.dropdown").querySelector(".dropdown-menu").classList.remove("visible");
    document.querySelector(".menu-item.dropdown1").querySelector(".dropdown-menu1").classList.remove("visible");
    document.querySelector(".menu-item.dropdown2").querySelector(".dropdown-menu2").classList.remove("visible");
    document.querySelector(".menu-item.dropdown3").querySelector(".dropdown-menu3").classList.remove("visible");
  });

  menuItems.forEach((item) => {
    item.addEventListener("click", (event) => {
      menuItems.forEach((i) => i.classList.remove("active"));
      item.classList.add("active");
    });
  });
});


function home() {
  // 로고 이미지 클릭 시 hr (홈) 으로 이동
  location.href = "/hr";
}


function navi() {

  //조직도 이동
  document.querySelector('.dropdown-menu li:nth-child(1)').addEventListener("click", () => {
    location.href = "/organization";
  })
  //연혁도 이동
  document.querySelector('.dropdown-menu li:nth-child(2)').addEventListener("click", () => {
    location.href = "/history";
  })
  //직원 검색
  document.querySelector('.dropdown-menu li:nth-child(3)').addEventListener("click", () => {
    location.href = "/employee";
  })
}



function pvnavi() {
  //사이드바 마이페이지 이동
  document.querySelector('.dropdown-menu1 li:nth-child(1)').addEventListener("click", () => {
    location.href = "/mypage";
  })
  //사이드바 개인휴가내역 이동
  document.querySelector('.dropdown-menu1 li:nth-child(2)').addEventListener("click", () => {
    location.href = "/pv/vacation/list";
  })
}

function hrnavi() {
  //초과근무 이동
  document.querySelector('.dropdown-menu2 li:nth-child(3)').addEventListener("click", () => {
    location.href = "/api/hr/overtime/list";
  })
  //휴가 이동
  document.querySelector('.dropdown-menu2 li:nth-child(4)').addEventListener("click", () => {
    location.href = "/api/hr/vacation/list";
  })
  //급여 이동
  document.querySelector('.dropdown-menu2 li:nth-child(5)').addEventListener("click", () => {
    location.href = "/api/hr/salary/list";
  })
}
function qrnavi() {
  //상품관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(1)').addEventListener("click", () => {
    location.href = "/qa/product/list";
  })
  //재고 현황 조회 이동
  document.querySelector('.dropdown-menu3 li:nth-child(2)').addEventListener("click", () => {
    location.href = "/qa/productcnt/list";
  })
  //불량 상품 관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(3)').addEventListener("click", () => {
    location.href = "/qa/defective/list";
  })
  //품질 관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(4)').addEventListener("click", () => {
    location.href = " /qa/inspection/list";
  })
  //AS요청관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(5)').addEventListener("click", () => {
    location.href = "/qa/asreq/list";
  })
  //AS작업관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(6)').addEventListener("click", () => {
    location.href = "/qa/aswork/list";
  })
  //담당자 관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(7)').addEventListener("click", () => {
    location.href = "/qa/asemp/list";
  })
  //불량코드 관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(8)').addEventListener("click", () => {
    location.href = "/qa/defectivecode/list";
  })
  //고장코드 관리 이동
  document.querySelector('.dropdown-menu3 li:nth-child(9)').addEventListener("click", () => {
    location.href = "/qa/faultcode/list";
  })
  
}




function finavi() {
  //거래처 관리 이동
  document.querySelector('.dropdown-menu4 li:nth-child(1)').addEventListener("click", () => {
    location.href = "/finance/partner/list";
  })
  //회사계좌 관리 이동
  document.querySelector('.dropdown-menu4 li:nth-child(2)').addEventListener("click", () => {
    location.href = "/finance/account/list";
  })
  //매출 관리 이동
  document.querySelector('.dropdown-menu4 li:nth-child(3)').addEventListener("click", () => {
    location.href = "/finance/sale/list";
  })
  //매입 관리 이동
  document.querySelector('.dropdown-menu4 li:nth-child(4)').addEventListener("click", () => {
    location.href = "/finance/purchase/list";
  })
  //경비 관리 이동
  document.querySelector('.dropdown-menu4 li:nth-child(5)').addEventListener("click", () => {
    location.href = "/finance/expense/list";
  })
  //재무상태표 관리 이동
  document.querySelector('.dropdown-menu4 li:nth-child(7)').addEventListener("click", () => {
    location.href = "/finance/bas/detail";
  })
  //손인계산서 관리 이동
  document.querySelector('.dropdown-menu4 li:nth-child(8)').addEventListener("click", () => {
    location.href = "/finance/inc/detail";
  })
}


const sidebar = document.querySelectorAll(".dropdown-menu .disabled");
const pvsidebar = document.querySelectorAll(".dropdown-menu1 .disabled");
const insasidebar = document.querySelectorAll(".dropdown-menu2 .disabled");
const qasidebar = document.querySelectorAll(".dropdown-menu3 .disabled");
const fisidebar = document.querySelectorAll(".dropdown-menu4 .disabled");
function access(data) {

  console.log(data);
  
  
  if (data != 1&&data !=100) {
    for (let i = 0; i < insasidebar.length; i++) {
      const e = insasidebar[i];

      e.addEventListener("click", () => {
        alert("권한이 없습니다");
      });
    }
  }
  if (data != 2&&data !=100) {
    for (let i = 0; i < qasidebar.length; i++) {
      const e = qasidebar[i];
      e.addEventListener("click", () => {
        alert("권한이 없습니다");
      });
    }
  }
  if (data != 3&&data !=100) {
    for (let i = 0; i < fisidebar.length; i++) {
      const e = fisidebar[i];
      e.addEventListener("click", () => {
        alert("권한이 없습니다");
      });
    }
  }
}


function disabledinsadelete(data) {

  if (data == 1) {
    navi();
    pvnavi();
    hrnavi();
    access(1);
  }
  if (data == 2) {
    navi();
    pvnavi();
    qrnavi();
    access(2);
  }
  if (data == 3) {
    navi();
    pvnavi();
    finavi();
    access(3);
  }

}

function adminlogin(admin){
  if(admin != null){
    navi();
    pvnavi();
    hrnavi();
    qrnavi();
    finavi();
    access(100);
  }
  

}