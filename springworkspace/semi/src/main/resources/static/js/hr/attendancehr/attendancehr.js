// 전체 선택/해제
function toggleAll(source) {
    const checkboxes = document.getElementsByName('attendanceCheck');
    for (const checkbox of checkboxes) {
        checkbox.checked = source.checked;
    }
}

// 선택된 항목 삭제
function deleteSelected() {
    const selected = [];
    const checkboxes = document.getElementsByName('attendanceCheck');
    for (const checkbox of checkboxes) {
        if (checkbox.checked) {
            selected.push(checkbox.value); // 체크된 값 추가
        }
    }

    if (selected.length === 0) {
        alert("삭제할 항목을 선택하세요.");
        return;
    }

    if (confirm("선택된 항목을 삭제하시겠습니까?")) {
        fetch('/attendancehr/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(selected), // JSON 배열 전송
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('삭제 요청 실패');
            }
            return response.text();
        })
        .then(data => {
            alert(data); // 서버 응답 메시지 출력
            location.reload(); // 페이지 새로고침
        })
        .catch(error => {
            console.error('Error:', error);
            alert("삭제 처리 중 오류가 발생했습니다.");
        });
    }
}

