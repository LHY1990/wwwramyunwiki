
function popup(){
    if($('#login_popup').css('display')=='none'){
        $('#login_popup').css('display','block');
    }else{
        $('#login_popup').css('display','none');
    }



    
}
// propertychange change keyup paste input
window.addEventListener('DOMContentLoaded', function(){
    //이거 제대로 작동함
    $('#search_box_textarea').on("propertychange keyup paste input", function(){
        //let inputtext= document.getElementById("search_box_textarea").value;
        //console.log(inputtext);
        //기능하는지확인
        $('#auto0').text("");
        $('#auto1').text("");
        $('#auto2').text("");
        $('#auto3').text("");
        $('#auto4').text("");
        
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
                // console.log(items[1]);
                // console.log(items[2]);
                $('#auto0').text(items[0]);
                $('#auto1').text(items[1]);
                $('#auto2').text(items[2]);
                $('#auto3').text(items[3]);
                $('#auto4').text(items[4]);
                
                
                
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