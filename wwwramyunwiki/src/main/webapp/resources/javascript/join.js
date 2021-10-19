window.onload=function(){
    
    
    // document.getElementById('layoutInnerId').style.left=(400+'px');

    // $(window).resize(function(){

    //     var layoutInnerSizeWidth =  ((window.innerWidth/2)-201);
    //     document.getElementById('layoutInnerId').style.left=(layoutInnerSizeWidth+'px');



        
    // })



    







}

function sendingMailCode(){
    var firstPassword=document.getElementById('memberPassword').value;
    var secondPassword=document.getElementById('passwordCheck').value;
    if(firstPassword!=secondPassword){
        alert("비밀번호가 일치하지 않습니다.");
    }
    if(firstPassword==secondPassword){
        $('#codenumber_check').css('display','block');
    }

    alert("메일이 보내졌습니다.")
}