window.addEventListener('DOMContentLoaded', function(){
    // 회원가입창 ajax, 아이디 입력에 대해 반응한다.
    $('#memberId').on("change keyup pastechange paste", (event)=>{
        $.ajax({
            type : "POST",
            url : "./isunique.do",
            dataType : "json",
            data : {
                "input" : $('#memberId').val()
            },
            success : function(items) {
                //console.log(items);
                $('#isUniqueAjax').text(items);
            },
            error : function(error) {
                console.log("에러난다이거");
            }
        })
    });
});

function sendingMailCode(){
    var firstPassword=document.getElementById('memberPassword').value;
    var secondPassword=document.getElementById('passwordCheck').value;
    if(firstPassword!=secondPassword){
        alert("비밀번호가 일치하지 않습니다.");
    }
    if(firstPassword==secondPassword){
        $('#codenumber_check').css('display','block');
    }




    
}