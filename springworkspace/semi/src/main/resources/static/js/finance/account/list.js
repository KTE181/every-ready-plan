function LoadAccountList(){
    const tbodyTag = document.queryselector("main>table>tbody");

    //tbodyTag 내용

    $.ajax({
        url: "finance/account/list/data",
//        method :,
        success : function(accountVoList){
            console.log(accountVoList);

        },
        fail : function() {
            alert("게시글 목록 조회 실패");
        },

    })
}

loadAccountList();
