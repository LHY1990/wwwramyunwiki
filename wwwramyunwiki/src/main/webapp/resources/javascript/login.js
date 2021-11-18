

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