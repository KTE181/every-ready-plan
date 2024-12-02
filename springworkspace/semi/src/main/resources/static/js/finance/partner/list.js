function partnerWrite(){

    const partnerWriteModal = document.getElementById('partner-write');
    const closeModal = document.querySelector('.write-close');

    partnerWriteModal.style.display = 'block';

    //"x" 버튼 클릭시 모달 닫기
    closeModal.addEventListener('click', () =>) {
        partnerWriteModal.style.display = 'none';
    });

}

