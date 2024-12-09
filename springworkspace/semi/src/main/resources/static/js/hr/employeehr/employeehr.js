    // 전체 체크박스 클릭 시 모든 체크박스 상태 변경
    function toggleAll(source) {
        const checkboxes = document.querySelectorAll('input[name="employeeCheck"]');
        checkboxes.forEach((checkbox) => {
            checkbox.checked = source.checked;
        });
    }
    //삭제
    function deleteEmployees() {
        // 선택된 직원 ID들을 JSON으로 서버에 전송
        const employeeIds = [...document.querySelectorAll('input[name="employeeCheck"]:checked')].map(input => input.value);

        if (employeeIds.length === 0) {
            alert("삭제할 직원들을 선택해주세요.");
            return;
        }

        // 삭제 확인 알림창
        const confirmed = confirm(`선택된 ${employeeIds.length}명의 직원들을 삭제하시겠습니까?`);
        if (!confirmed) {
            // 사용자가 취소를 선택하면 함수 종료
            return;
        }

        fetch('/employeehr/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(employeeIds) // JSON 배열로 전달
        })
        .then(response => {
            if (response.ok) {
                alert("삭제가 완료되었습니다.");
                location.reload();
            } else {
                response.text().then(text => alert(`삭제 실패: ${text}`));
            }
        })
        .catch(error => {
            console.error("삭제 요청 중 오류 발생:", error);
            alert("삭제 요청 중 오류가 발생했습니다.");
        });

    }

//등록모달
// 모달 열기
document.getElementById("create").addEventListener("click", () => {
    document.getElementById("registerModal").style.display = "block";
});

// 모달 닫기 함수
function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = "none";
    } else {
        console.warn(`ID가 '${modalId}'인 모달을 찾을 수 없습니다.`);
    }
}

// 이벤트 전파 차단
document.querySelectorAll('input[name="employeeCheck"]').forEach((checkbox) => {
    checkbox.addEventListener('click', (event) => {
        event.stopPropagation();
    });
});
function openDetailModal(no) {
    // 체크박스 클릭 여부 확인
    if (event.target.tagName === 'INPUT' && event.target.type === 'checkbox') {
        return; // 체크박스 클릭 시 아무 작업도 하지 않음
    }
    // 체크박스 외의 영역 클릭 시 모달 열기
    console.log(`Open modal for employee: ${no}`);
    // 모달 열기 코드 작성
}


