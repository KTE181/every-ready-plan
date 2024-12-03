// 파일 입력창 열기
function openFileInput() {
    document.getElementById("profileImageInput").click();
}

// 파일 업로드 처리
function uploadProfileImage() {
    const fileInput = document.getElementById("profileImageInput");
    const file = fileInput.files[0];

    if (!file) {
        alert("파일을 선택하세요.");
        return;
    }

    const validExtensions = ["jpg", "png"];
    const fileExtension = file.name.split(".").pop().toLowerCase();
    if (!validExtensions.includes(fileExtension)) {
        alert("jpg 또는 png 파일만 업로드 가능합니다.");
        return;
    }

    const formData = new FormData();
    formData.append("profileImage", file);

    const employeeId = document.getElementById("employeeData").dataset.employeeId;
    formData.append("employeeId", employeeId);

    fetch(`${contextPath}/mypage/uploadProfileImage`, {
        method: "POST",
        body: formData,
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("서버 응답 실패");
            }
            return response.json();
        })
        .then(data => {
            console.log("서버 응답:", data);
            if (data.success) {
                alert("프로필 이미지가 성공적으로 변경되었습니다.");
                location.reload(); // 페이지 새로고침
            } else {
                alert(data.message || "업로드 중 문제가 발생했습니다.");
            }
        })
        .catch(error => {
            console.error("업로드 실패:", error);
            alert("업로드 중 문제가 발생했습니다.");
        });

}
/****************************************/
// 모달 열기
function showPasswordModal() {
    document.getElementById("passwordModal").style.display = "flex";
}

// 모달 닫기
function closePasswordModal() {
    document.getElementById("passwordModal").style.display = "none";
}

// 비밀번호 변경 요청
function changePassword() {
    const currentPassword = document.getElementById("currentPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    // 유효성 검사
    if (newPassword !== confirmPassword) {
        alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return;
    }

    // 서버로 요청
    fetch(`${contextPath}/mypage/changePassword`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            currentPassword: currentPassword,
            newPassword: newPassword,
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("서버 응답 실패");
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                alert("비밀번호가 성공적으로 변경되었습니다.");
                window.location.href = `${contextPath}/login`; // 로그인 페이지로 리다이렉트
            } else {
                alert(data.message || "비밀번호 변경 중 문제가 발생했습니다.");
            }
        })
        .catch(error => {
            console.error("비밀번호 변경 실패:", error);
            alert("비밀번호 변경 중 문제가 발생했습니다.");
        });
}

