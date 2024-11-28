function paintPageArea(pvo){
    const pageArea = document.querySelector(".page-area");
    pageArea.innerHTML = "";
    //이전버튼
    if(pvo.startPage != 1){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/board/list?pno=${pvo.startPage-1}`);
        aTag.innerText = "이전";
        pageArea.appendChild(aTag);
    }
    //페이지버튼
    for(let i = pvo.startPage ; i <= pvo.endPage; ++i ){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/board/list?pno=${i}`);
        aTag.innerText = i;
        pageArea.appendChild(aTag);
    }
    //다음버튼
    if(pvo.endPage != pvo.maxPage){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/board/list?pno=${pvo.endPage + 1}`);
        aTag.innerText = '다음';
        pageArea.appendChild(aTag);
    }
}