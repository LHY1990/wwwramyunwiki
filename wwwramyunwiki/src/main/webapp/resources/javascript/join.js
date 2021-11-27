window.addEventListener('DOMContentLoaded', function(){
    // 회원가입창 ajax
    $('#memberId').on("change keyup pastechange paste", (event)=>{
        
        $.ajax({
            type : "POST",
            url : "./isunique.do",
            dataType : "json",
            data : {
                "input" : $('#memberId').val()

            },
            
            success : function(items) {
                console.log(items);
                
                //$('#auto0').val(items[0]);
                $('#isUniqueAjax').text(items);
                
                
                
            },
            error : function(error) {
                console.log("에러난다이거");
            }
    
        })
        // $('#recommand').css('display','block');
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