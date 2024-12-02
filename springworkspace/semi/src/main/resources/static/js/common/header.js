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



// 마이페이지 이동
function redirectToMypage() {
    window.location.href = "/mypage";
}

// 로그아웃
function logout() {
    fetch("/logout", { // contextPath를 제거하여 절대경로로 처리
        method: "POST",
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("로그아웃 실패");
            }
            window.location.href = "/login"; // 하드코딩된 경로로 로그인 페이지로 이동
        })
        .catch(error => {
            console.error("로그아웃 오류:", error);
            alert("로그아웃 중 문제가 발생했습니다.");
        });
}