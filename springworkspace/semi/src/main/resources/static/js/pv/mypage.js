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

