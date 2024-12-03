document.addEventListener('DOMContentLoaded', () => {
    const profileImage = document.querySelector('.image-circle');
    const profileMenu = document.querySelector('#profile-menu');

    if (!profileImage || !profileMenu) {
        console.error("프로필 이미지 또는 메뉴 요소를 찾을 수 없습니다.");
        return;
    }

    // 프로필 이미지 클릭 이벤트
    profileImage.addEventListener('click', () => {
        console.log('프로필 메뉴 클릭'); // 디버깅용
        profileMenu.classList.toggle('display-none');
    });

    // 화면의 다른 부분을 클릭하면 메뉴 닫기
    document.addEventListener('click', (event) => {
        if (!profileImage.contains(event.target) && !profileMenu.contains(event.target)) {
            profileMenu.classList.add('display-none');
        }
    });

    // 창 크기 변경 시 메뉴 닫기
    window.addEventListener("resize", () => {
        profileMenu.classList.add('display-none');
    });
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
/**********************************************/
// 출근 데이터 서버로 전송
function submitAttendance() {
    const empNo = document.getElementById("empNo").value; // 숨겨진 필드에서 empNo 가져오기

    if (!empNo) {
        alert("로그인 정보가 없습니다.");
        closeAttendanceModal();
        return;
    }

    fetch('/attendance/clockIn', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ empNo: empNo }) // JSON 객체로 전달
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("출근 등록 완료");
            location.reload(); // 상태 갱신
        } else {
            alert(data.message);
        }
        closeAttendanceModal();
    })
    .catch(error => {
        console.error("출근 처리 오류:", error);
        alert("출근 등록 중 오류가 발생했습니다.");
        closeAttendanceModal();
    });
}
// 출근/퇴근 상태 확인 및 버튼 상태 갱신
function checkAttendanceStatus() {
    const empNo = document.getElementById("empNo").value;

    if (!empNo) {
        console.error("로그인 정보가 없습니다.");
        return;
    }

    fetch(`/attendance/status?empNo=${empNo}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            const { hasClockIn, isWorking } = data.data;

            const clockInButton = document.querySelector(".button");
            const clockOutButton = document.querySelector(".buttonout");

            if (!hasClockIn) {
                clockInButton.style.display = "block"; // 출근 버튼 활성화
                clockOutButton.style.display = "none"; // 퇴근 버튼 비활성화
            } else if (isWorking) {
                clockInButton.style.display = "none"; // 출근 버튼 비활성화
                clockOutButton.style.display = "block"; // 퇴근 버튼 활성화
            } else {
                clockInButton.style.display = "none"; // 출근 버튼 비활성화
                clockOutButton.style.display = "none"; // 퇴근 버튼 비활성화
            }
        } else {
            alert("출근/퇴근 상태 확인 중 오류: " + data.message);
        }
    })
    .catch(error => {
        console.error("출근/퇴근 상태 확인 중 오류:", error);
    });
}
// 페이지 로드 시 상태 확인
document.addEventListener("DOMContentLoaded", function () {
    checkAttendanceStatus();
});

// 출근 모달 열기
function openAttendanceModal() {
    document.getElementById("attendance-modal").style.display = "block";
}

// 출근 모달 닫기
function closeAttendanceModal() {
    document.getElementById("attendance-modal").style.display = "none";
}
// 퇴근 데이터 서버로 전송
function submitAttendanceout() {
    const empNo = document.getElementById("empNo").value; // 숨겨진 필드에서 empNo 가져오기

    if (!empNo) {
        alert("로그인 정보가 없습니다.");
        closeAttendanceoutModal();
        return;
    }

    fetch('/attendance/clockOut', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ empNo: empNo }) // JSON 객체로 전달
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("퇴근 등록 완료");
            location.reload(); // 상태 갱신
        } else {
            alert( data.message);
        }
        closeAttendanceoutModal();
    })
    .catch(error => {
        console.error("퇴근 처리 오류:", error);
        alert("퇴근 등록 중 오류가 발생했습니다.");
        closeAttendanceoutModal();
    });
}
// 퇴근 모달 열기
function openAttendanceoutModal() {
    document.getElementById("attendanceout-modal").style.display = "block";
}

// 퇴근 모달 닫기
function closeAttendanceoutModal() {
    document.getElementById("attendanceout-modal").style.display = "none";
}
