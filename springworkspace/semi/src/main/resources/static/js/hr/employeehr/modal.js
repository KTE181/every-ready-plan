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
//사원등록
document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault(); // 폼 기본 동작 중지

    const formData = new FormData(this);
    formData.delete('no'); // 사번은 서버에서 처리하므로 제거

    const jsonData = Object.fromEntries(formData.entries());

    fetch('/employeehr/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(jsonData),
    })
    .then(response => {
        if (response.ok) {
            alert("사원 등록이 완료되었습니다.");
            location.reload(); // 페이지 새로고침
        } else {
            return response.text().then(text => {
                throw new Error(text);
            });
        }
    })
    .catch(error => {
        console.error("사원 등록 중 오류 발생:", error);
        alert("등록 실패: " + error.message);
    });
});

//상세조회
// YYYYMMDD -> YYYY-MM-DD 변환 함수
function formatDate(dateString) {
    if (!dateString || dateString.length !== 8) {
        return ""; // 날짜가 없거나 형식이 잘못되었을 경우 빈 문자열 반환
    }
    return `${dateString.substring(0, 4)}-${dateString.substring(4, 6)}-${dateString.substring(6, 8)}`;
}

function openDetailModal(employeeNo) {
    // 서버에서 해당 사원의 정보 가져오기
    fetch(`/employeehr/detail/${employeeNo}`)
        .then((response) => response.json())
        .then((employee) => {
            // 가져온 데이터를 모달에 채우기
            document.getElementById("detailNo").value = employee.no;
            document.getElementById("detailName").value = employee.name;
            document.getElementById("detailBirth").value = formatDate(employee.birth);
            document.getElementById("detailPhone").value = employee.phone;
            document.getElementById("detailEmergencyPhone").value = employee.emergencyPhone;
            document.getElementById("detailEmail").value = employee.email;
            document.getElementById("detailAddress").value = employee.address;
            // 성별 값 설정
            if (employee.gender === "M") {
                document.getElementById("detailGenderM").checked = true; // 남자
            } else if (employee.gender === "F") {
                document.getElementById("detailGenderF").checked = true; // 여자
            }
            document.getElementById("detailDeptCode").value = employee.deptCode;
            document.getElementById("detailPositionCode").value = employee.positionCode;
            document.getElementById("detailEnterDate").value = formatDate(employee.enterDate);
            document.getElementById("detailOutDate").value = formatDate(employee.outDate);

            document.getElementById("detailSalary").value = employee.salary;
            document.getElementById("detailTotalVacationDays").value = employee.totalVacationDays;
            document.getElementById("detailStatusCode").value = employee.statusCode;
            document.getElementById("detailBankCode").value = employee.bankCode;
            document.getElementById("detailAccountNo").value = employee.accountNo;
            // 프로필 이미지 설정
            const profilePreview = document.getElementById("detailProfilePreview");

            if (employee.profileImage) {
                profilePreview.src = employee.profileImage;
            } else {
                profilePreview.src = "/img/employee/default.png"; // 기본 이미지 경로
            }
            // 모달 열기
            document.getElementById("detailModal").style.display = "block";
        })
        .catch((error) => {
            console.error("상세 정보 로드 중 오류:", error);
            alert("상세 정보를 불러오는 중 오류가 발생했습니다.");
        });

}

function getExtension(profileImagePath) {
    return profileImagePath.split('.').pop(); // 확장자를 반환
}
function saveChanges() {
    // FormData 객체 생성
    const formData = new FormData();

    // 입력된 데이터 추가
    formData.append("no", document.getElementById("detailNo").value);
    formData.append("name", document.getElementById("detailName").value);
    const birth = document.getElementById("detailBirth").value.replace(/-/g, "");
    formData.append("phone", document.getElementById("detailPhone").value);
    formData.append("emergencyPhone", document.getElementById("detailEmergencyPhone").value);
    formData.append("email", document.getElementById("detailEmail").value);
    formData.append("address", document.getElementById("detailAddress").value);
    formData.append("deptCode", document.getElementById("detailDeptCode").value);
    formData.append("positionCode", document.getElementById("detailPositionCode").value);
    const enterDate = document.getElementById("detailEnterDate").value.replace(/-/g, "");
    const outDate = document.getElementById("detailOutDate").value
        ? document.getElementById("detailOutDate").value.replace(/-/g, "")
        : "";
    formData.append("salary", document.getElementById("detailSalary").value);
    formData.append("totalVacationDays", document.getElementById("detailTotalVacationDays").value);
    formData.append("statusCode", document.getElementById("detailStatusCode").value);
    formData.append("bankCode", document.getElementById("detailBankCode").value);
    formData.append("accountNo", document.getElementById("detailAccountNo").value);
    formData.append("birth", birth);
    formData.append("enterDate", enterDate);
    formData.append("outDate", outDate);

    // 성별 값 추가
    const genderInput = document.querySelector('input[name="detailGender"]:checked');
    if (genderInput) {
        formData.append("gender", genderInput.value);
    } else {
        console.error("성별이 선택되지 않았습니다.");
    }
    // 프로필 사진 파일 추가
    const profilePicture = document.getElementById("detailProfilePicture").files[0];
    if (profilePicture) {
        formData.append("profilePicture", profilePicture);
    }

    // 서버로 데이터 전송
    fetch(`/employeehr/update`, {
        method: "POST",
        body: formData, // FormData 객체를 전송
    })
        .then((response) => {
            if (response.ok) {
                alert("변경 사항이 저장되었습니다.");
                closeModal("detailModal");
                location.reload(); // 페이지 새로고침
            } else {
                throw new Error("저장 실패");
            }
        })
        .catch((error) => {
            console.error("저장 중 오류 발생:", error);
            alert("저장 중 오류가 발생했습니다.");
        });
}
function enableEdit() {
    // 모든 input, select, textarea 요소의 disabled 속성을 해제
    const fields = document.querySelectorAll("#detailModal fieldset");

    fields.forEach((field) => {
        field.disabled = false; // disabled 속성 해제
    });

    // '수정' 버튼을 '저장' 버튼으로 변경
    const editButton = document.getElementById("editButton");
    editButton.style.display = "none"; // 수정 버튼 숨기기

    const saveButton = document.getElementById("saveButton");
    saveButton.style.display = "inline-block"; // 저장 버튼 표시
}


function previewProfilePicture(event) {
    const profilePreview = document.getElementById("detailProfilePreview");
    profilePreview.src = URL.createObjectURL(event.target.files[0]);
}



