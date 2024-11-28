// 모달 요소 가져오기
const modal = document.getElementById('modal');
const enrollTag = document.querySelector('#enroll-button');
const closeModal = document.querySelector('.modal-close');

enrollTag.addEventListener('click', () => {
    modal.style.display = 'block'; // 모달 표시
});

// "X" 버튼 클릭 시 모달 닫기
closeModal.addEventListener('click', () => {
    modal.style.display = 'none'; // 모달 숨기기
});

