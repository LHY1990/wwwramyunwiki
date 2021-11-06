
function popup(){
    if($('#login_popup').css('display')=='none'){
        $('#login_popup').css('display','block');
    }else{
        $('#login_popup').css('display','none');
    }



    
}

function login_needed() {
    alert("로그인이 필요한 서비스 입니다.");
}

// propertychange change keyup paste input
window.addEventListener('DOMContentLoaded', function(){
    //이거 제대로 작동함change는 다시넣어보자
    $('#search_box_textarea').on("change keyup pastechange keyup paste", function(){
        //let inputtext= document.getElementById("search_box_textarea").value;
        //console.log(inputtext);
        //기능하는지확인
        //$('#auto0').val("");
        //$('#auto1').val("");
        //$('#auto2').val("");
        //$('#auto3').val("");
        //$('#auto4').val("");
        
        $.ajax({
            type : "POST",
            url : "./searchintime.do",
            dataType : "json",
            data : {
                "msg" : $('#search_box_textarea').val()

            },
            
            success : function(items) {
                // console.log("성공해부럿다");
                // console.log(items[0]);
                
                $('#auto0').val(items[0]);
                $('#auto1').val(items[1]);
                $('#auto2').val(items[2]);
                $('#auto3').val(items[3]);
                $('#auto4').val(items[4]);
                
                
                
            },
            error : function(error) {
                console.log("에러난다이거");
            }
    
        })
        $('#recommand').css('display','block');
    });
    $('#search_box_textarea').on("blur focusout", function() {
        $('#recommand').css('display','none');
        
    });

})



/*
sessionStorage.getItem("member");
if(sessionStorage!=null){
    alert(sessionStorage.length);
}

$('#search_box_textarea').value
*/