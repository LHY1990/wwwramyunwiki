window.onload=function(){
    


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

    // if($('#memberEmail').val()==''){
    //     alert('이메일 입력란이 비었습니다.')
        
    // }
    // alert("메일이 보내졌습니다.")


    
}