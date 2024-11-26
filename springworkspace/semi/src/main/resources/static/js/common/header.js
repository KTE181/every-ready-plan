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

window.addEventListener("resize", () => {
    profileMenu.classList.add('display-none'); 
  });