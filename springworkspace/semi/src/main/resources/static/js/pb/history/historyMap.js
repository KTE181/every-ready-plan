
document.addEventListener("DOMContentLoaded", () => {
    const dropdown = document.querySelector(".menu-item.dropdown");
    const dropdownMenu = dropdown.querySelector(".dropdown-menu");
    const menuItems = dropdownMenu.querySelectorAll("li");

    dropdown.addEventListener("click", () => {
      dropdownMenu.classList.toggle("visible");
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
    });

    menuItems.forEach((item) => {
      item.addEventListener("click", (event) => {
        menuItems.forEach((i) => i.classList.remove("active"));
        item.classList.add("active");
      });
    });
  });


  const profileImage = document.querySelector(".image-circle");
const profileMenu = document.querySelector("#profile-menu");


profileImage.addEventListener('click', () => {
    profileMenu.classList.toggle('display-none');
});

document.addEventListener('click', (event) => {
    if (!profileImage.contains(event.target) && !profileMenu.contains(event.target)) {
        profileMenu.classList.add('display-none');
    }
});