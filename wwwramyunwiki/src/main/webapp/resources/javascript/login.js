

function checkPasswordEqual() {
    var oldPassword=document.getElementById('oldPassword').value;
    var firstPassword=document.getElementById('newPassword').value;
    var secondPassword=document.getElementById('newPasswordCheck').value;
    if(oldPassword==""||firstPassword==""||secondPassword==""){
        alert("비밀번호 입력이 필요합니다.");
        return false;
    }



    if(firstPassword!=secondPassword){
        
        alert("입력된 비밀번호가 일치하지 않습니다.");
        return false;
    }
    if(firstPassword==secondPassword){
        return true;
    }
} 


function checkPasswordEqualForWithdraw() {
    var withdrawPassword=document.getElementById('withdrawPassword').value;
    var withdrawPasswordCheck=document.getElementById('withdrawPasswordCheck').value;
    if(withdrawPassword==""||withdrawPasswordCheck==""){
        alert("비밀번호 입력이 필요합니다.");

        return false;



    }



    if(withdrawPassword!=withdrawPasswordCheck){
        
        alert("입력된 비밀번호가 일치하지 않습니다.");
        return false;
    }
    if(withdrawPassword==withdrawPasswordCheck){
        return true;
    }

    else{
        return false;
    }
} 

$(document).ready(function () {

    //애니메이션
    $('.bxslider').bxSlider({ // 클래스명 주의!
        auto: true, // 자동으로 애니메이션 시작
        speed: 500,  // 애니메이션 속도
        pause: 5000,  // 애니메이션 유지 시간 (1000은 1초)
        mode: 'horizontal', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
        autoControls: true, // 시작 및 중지버튼 보여짐
        pager: true, // 페이지 표시 보여짐
        captions: true, // 이미지 위에 텍스트를 넣을 수 있음
        touchEnabled : (navigator.maxTouchPoints > 0), //링크접근 가능
    });

});



