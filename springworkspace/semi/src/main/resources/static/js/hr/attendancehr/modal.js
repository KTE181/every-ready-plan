document.addEventListener("DOMContentLoaded", () => {
    // 열기 버튼
    const openModalBtn = document.getElementById("openModal");
    if (openModalBtn) {
        openModalBtn.addEventListener("click", () => openModal("attendanceModal"));
    }

    // 닫기 버튼
    const closeModalBtn = document.getElementById("closeModal");
    if (closeModalBtn) {
        closeModalBtn.addEventListener("click", () => {
            const modal = document.getElementById("attendanceModal");
            if (modal) {
                modal.style.display = "none";
            } else {
                console.error("모달을 찾을 수 없습니다.");
            }
        });
    }
});

// 전역 스코프에 함수 정의
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = "block";
    } else {
        console.error(`모달 창을 찾을 수 없습니다. modalId: ${modalId}`);
    }
}
document.addEventListener("DOMContentLoaded", () => {
    // 검색 버튼 클릭 이벤트
    document.getElementById("searchEmployeeButton").addEventListener("click", () => {
        const empNo = document.getElementById("registerEmpNo").value; // 모달에서 사용되는 ID

        if (!empNo) {
            alert("사번을 입력하세요.");
            return;
        }

        fetch(`/employee/search?empNo=${empNo}`)
            .then((response) => response.json())
            .then((data) => {
                if (data) {
                    console.log("사원명(employeeName):", data.name);
                    console.log("소속부서(deptName):", data.deptName);
                    console.log("직급(position):", data.position);

                    document.getElementById("employeeName").value = data.name || "";
                    document.getElementById("deptName").value = data.deptName || "";
                    document.getElementById("position").value = data.position || "";
                } else {
                    alert("해당 사번의 사원을 찾을 수 없습니다.");
                }
            })
            .catch((error) => {
                console.error("사원 검색 중 오류 발생:", error);
                alert("오류가 발생했습니다. 관리자에게 문의하세요.");
            });
    });


    // 출근시간/퇴근시간 변경 시 근무시간 계산
    document.getElementById("ciTime").addEventListener("change", calculateWorkTime);
    document.getElementById("coTime").addEventListener("change", calculateWorkTime);

    function calculateWorkTime() {
        const ciTime = document.getElementById("ciTime").value;
        const coTime = document.getElementById("coTime").value;

        if (ciTime && coTime) {
            const ciDate = new Date(`1970-01-01T${ciTime}:00`);
            const coDate = new Date(`1970-01-01T${coTime}:00`);

            const diffMs = coDate - ciDate;

            if (diffMs > 0) {
                const diffHrs = Math.floor(diffMs / (1000 * 60 * 60));
                const diffMins = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60));
                document.getElementById("workTime").value = `${diffHrs}시간 ${diffMins}분`;
            } else {
                document.getElementById("workTime").value = "0시간 0분";
                alert("퇴근시간은 출근시간보다 늦어야 합니다.");
            }
        }
    }

    // 등록 버튼 클릭 이벤트
    document.getElementById("registerAttendanceButton").addEventListener("click", () => {
        const attendanceData = {
            empNo: document.getElementById("empNo").value,
            attendanceDate: document.getElementById("attendanceDate").value,
            ciTime: document.getElementById("ciTime").value,
            coTime: document.getElementById("coTime").value,
        };

         console.log("등록 데이터 확인:", attendanceData);

        fetch("/attendance/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(attendanceData),
        })
            .then((response) => {
                if (response.ok) {
                    alert("등록 성공");
                    window.location.reload();
                } else {
                    alert("등록 실패");
                }
            })
            .catch((error) => {
                console.error("등록 중 오류 발생:", error);
                alert("오류가 발생했습니다. 관리자에게 문의하세요.");
            });
    });
});

