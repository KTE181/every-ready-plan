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
