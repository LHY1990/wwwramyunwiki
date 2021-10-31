function popup(){
    if($('#login_popup').css('display')=='none'){
        $('#login_popup').css('display','block');
    }else{
        $('#login_popup').css('display','none');
    }



    
}
window.addEventListener('DOMContentLoaded', function(){
    //이거 제대로 작동함
    $('#search_box_textarea').on("propertychange change keyup paste input", function(){
        let inputtext= document.getElementById("search_box_textarea").value;
        console.log(inputtext);
        //기능하는지확인

        $.ajax({
            type : "POST",
            url : "./searchintime.do",
            dataType : "json",
            data : {
                "msg" : "이거가냐"

            },
            
            success : function(data) {
                console.log("성공해부럿다");
            },
            error : function(error) {
                console.log("에러난다이거");
            }
    
        })
        
    });
    

})



/*
sessionStorage.getItem("member");
if(sessionStorage!=null){
    alert(sessionStorage.length);
}

$('search_box_textarea').value
*/