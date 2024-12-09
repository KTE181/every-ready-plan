let hrIndex=0;
let fiIndex=0;
let qaIndex=0;
let mkIndex=0;
let mgIndex=0;
let csIndex=0;



$.ajax({
    url:"/hr",
    method:"POST",
    success:function(data){

       //인사 인원
        for(let i= 0; i<data.length;i++){
            const x = data[i];
            //인사 인원
            if(x.deptCode==1){
                hrIndex++;
            }
            //품질 인원
            if(x.deptCode==2){
                qaIndex++;
            }
            //재무 인원
            if(x.deptCode==3){
                fiIndex++;
            }
            //마게팅 인원
            if(x.deptCode==4){
                mkIndex++;
            }
            //cs 인원
            if(x.deptCode==5){
                csIndex++;
            }
            //경영 인원
            if(x.deptCode==7){
                mgIndex++;
            }
            
        }
        const ctx = document.getElementById('myChart').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['인사', '재무', '품질', '마케팅', '경영', 'CS'],
        datasets: [{
            label: '부서별 인원분포도',
            data: [hrIndex, fiIndex, qaIndex, mkIndex, mgIndex, csIndex],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
  

    },
    error:function(){
        console.log("통신실패");
        
    }
})


























