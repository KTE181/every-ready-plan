function loadBalanceSheetVoList(searchType, searchValue) {
    const tbodyTag = document.querySelector("main table>tbody");
    // tbodyTag 내용 채우기 ~~~

    const url = new URL(location);
    let pno = url.searchParams.get("pno");
    if (pno == null) {
        pno = 1;
    }

    $.ajax({
        url: `/balanceSheet/list/data?pno=${pno}`,//pno나중에 처리
        data: {
            searchType,
            searchValue,
        },
        success: function(m) {
            const balanceSheetVoList = m.a; // 서버에서 반환된 balanceSheetVoList 리스트

            console.log(balanceSheetVoList);

            tbodyTag.innerHTML = ""; // 기존 테이블 내용 초기화

            // 서버에서 받은 incVoList를 반복문으로 테이블에 추가
            for (const vo of balanceSheetVoList) {
                const trTag = document.createElement("tr");

                const tdTag01 = document.createElement('td');
                tdTag01.innerText = vo.no; // No 출력
                trTag.appendChild(tdTag01);

                const tdTag02 = document.createElement('td');
                tdTag02.innerText = vo.year; // Year 출력
                trTag.appendChild(tdTag02);

                const tdTag03 = document.createElement('td');
                tdTag03.innerText = vo.deposite; // deposite 출력
                trTag.appendChild(tdTag03);

                const tdTag04 = document.createElement('td');
                tdTag04.innerText = vo.prepaidTax; // prepaidTax 출력
                trTag.appendChild(tdTag04);

                const tdTag05 = document.createElement('td');
                tdTag05.innerText = vo.merchadise; // merchadise 출력
                trTag.appendChild(tdTag05);

                const tdTag06 = document.createElement('td');
                tdTag06.innerText = vo.rawMaterial; // rawMaterial 출력
                trTag.appendChild(tdTag06);

                const tdTag07 = document.createElement('td');
                tdTag07.innerText = vo.withholdings; // withholdings 출력
                trTag.appendChild(tdTag07);

                const tdTag08 = document.createElement('td');
                tdTag08.innerText = vo.withheldTax; // withheldTax 출력
                trTag.appendChild(tdTag08);

                const tdTag09 = document.createElement('td');
                tdTag09.innerText = vo.capitalStock; // capitalStock 출력
                trTag.appendChild(tdTag09);

                tbodyTag.appendChild(trTag);
            }
        },
        fail: function() {
            alert("balanceSheetVoList 목록 조회 실패 (관리자에게 문의하세요)");
        },
    });
}
loadBalanceSheetVoList();
