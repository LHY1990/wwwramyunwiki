function popup(){
    if($('#login_popup').css('display')=='none'){
        $('#login_popup').css('display','block');
    }else{
        $('#login_popup').css('display','none');
    }
    
}
window.addEventListener('DOMContentLoaded', function(){
    //이거 제대로 작동함
})



/*
sessionStorage.getItem("member");
if(sessionStorage!=null){
    alert(sessionStorage.length);
}
*/