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
