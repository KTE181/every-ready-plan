function asreqWrite() {
    
    // 모달 요소 가져오기
    const asreqWriteModal = document.getElementById('asreq-write');
    const closeModal = document.querySelector('.write-close');

    asreqWriteModal.style.display = 'block'; // 모달 표시

    // "X" 버튼 클릭 시 모달 닫기
    closeModal.addEventListener('click', () => {
        asreqWriteModal.style.display = 'none'; // 모달 숨기기
    });

}

function asreqDetail(asreqNo) {

    // 모달 요소 가져오기
    const asreqDetailModal = document.getElementById('asreq-detail');
    const modalContent = document.querySelector('.detail-content');

    asreqDetailModal.style.display = 'block'; // 모달 표시

    $.ajax({
        url: "/qa/asreq/detail",
        method: "get",
        data: {
            asreqNo : asreqNo 
        } ,
        success: function(data) {
            console.log(data);
            modalContent.innerHTML = data; 
        },

        fail: function() {
            alert("통신실패...");
        }
    });
}

function asreqDetailClose() {
    const asreqDetailModal = document.getElementById('asreq-detail');
    const closeModal = document.querySelector('.detail-close');
    asreqDetailModal.style.display = 'none'; // 모달 숨기기
}