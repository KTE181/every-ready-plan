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

//조직도 이동
document.querySelector('.dropdown-menu li:nth-child(1)').addEventListener("click",()=>{
  location.href="/organization";
})
//연혁도 이동
document.querySelector('.dropdown-menu li:nth-child(2)').addEventListener("click",()=>{
  location.href="/history";
})
//직원 검색
document.querySelector('.dropdown-menu li:nth-child(3)').addEventListener("click",()=>{
  location.href="/employee";
})





//사이드바 마이페이지 이동
document.querySelector('.dropdown-menu1 li:nth-child(1)').addEventListener("click",()=>{
  location.href="/mypage";
})
//초과근무 이동
document.querySelector('.dropdown-menu2 li:nth-child(3)').addEventListener("click",()=>{
  location.href="/api/hr/overtime/list";
})
//휴가 이동
document.querySelector('.dropdown-menu2 li:nth-child(4)').addEventListener("click",()=>{
  location.href="/api/hr/vacation/list";
})
//급여 이동
document.querySelector('.dropdown-menu2 li:nth-child(5)').addEventListener("click",()=>{
  location.href="/api/hr/salary/list";
})





//상품관리 이동
document.querySelector('.dropdown-menu3 li:nth-child(1)').addEventListener("click",()=>{
  location.href="/qa/product/list";
})
//재고 현황 조회 이동
document.querySelector('.dropdown-menu3 li:nth-child(2)').addEventListener("click",()=>{
  location.href="/qa/productcnt/list";
})
//AS요청관리 이동
document.querySelector('.dropdown-menu3 li:nth-child(4)').addEventListener("click",()=>{
  location.href="/qa/asreq/list";
})
//담당자 관리 이동
document.querySelector('.dropdown-menu3 li:nth-child(7)').addEventListener("click",()=>{
  location.href="/qa/asemp/list";
})
//불량코드 관리 이동
document.querySelector('.dropdown-menu3 li:nth-child(8)').addEventListener("click",()=>{
  location.href="/qa/defectivecode/list";
})






//거래처 관리 이동
document.querySelector('.dropdown-menu4 li:nth-child(1)').addEventListener("click",()=>{
  location.href="/finance/partner/list";
})
//회사계좌 관리 이동
document.querySelector('.dropdown-menu4 li:nth-child(2)').addEventListener("click",()=>{
  location.href="/finance/account/list";
})


