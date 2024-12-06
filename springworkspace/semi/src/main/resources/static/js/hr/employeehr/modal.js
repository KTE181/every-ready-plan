function previewProfilePicture(event) {
    console.log("이미지 업로드 이벤트 발생"); // 함수가 호출되었는지 확인
    const file = event.target.files[0];
    if (file) {
        console.log("파일 읽기 시작:", file.name); // 파일 이름 출력
        const reader = new FileReader();
        reader.onload = function () {
            const preview = document.getElementById('profilePreview');
            console.log("미리보기 설정 완료");
            preview.src = reader.result;
        };
        reader.readAsDataURL(file);
    } else {
        console.log("파일 없음");
    }
}
