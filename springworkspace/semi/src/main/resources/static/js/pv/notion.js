function getdata(){
    $.ajax({
        url:"/home",
        method:"POST",
        success:function(data){
            // console.log(JSON.parse(data));
            const dataArr=JSON.parse(data);
            
            console.log(dataArr);
            const tbodyTag = document.querySelector("#tbodyarea");
            for(let i= 0; i<dataArr.results.length;i++){
                const x =dataArr.results[i];

                const trTag =document.createElement("tr");
                const tdTag1 =document.createElement("td");
                const tdTag2 =document.createElement("td");
                const tdTag3 =document.createElement("td");

                tdTag1.innerText=x.properties?.name?.title[0]?.text?.content;
                tdTag2.innerText=x.properties?.location?.rich_text[0]?.text?.content;
                tdTag3.innerText=x.properties?.day?.rich_text[0]?.text?.content;
                trTag.appendChild(tdTag1);
                trTag.appendChild(tdTag2);
                trTag.appendChild(tdTag3);

                tbodyTag.appendChild(trTag);

                
            }
            console.log("데이터 로딩성공");
        },
        error:function(){}
    })
}
getdata();