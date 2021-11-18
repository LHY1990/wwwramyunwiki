
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

//change keyup pastechange paste 
window.addEventListener('DOMContentLoaded', function(){
    // 검색창 ajax
    $('#search_box_textarea').on("propertychange change keyup paste input", function(){
        //$('#auto0').val("");
        $.ajax({
            type : "POST",
            url : "./searchintime.do",
            dataType : "json",
            data : {
                "msg" : $('#search_box_textarea').val()

            },
            
            success : function(items) {
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
    // var searchAjax=document.getElementById('search_box_textarea');
    // searchAjax.keyDown=function(){
    //     //$('#auto0').val("");
    //     $.ajax({
    //         type : "POST",
    //         url : "./searchintime.do",
    //         dataType : "json",
    //         data : {
    //             "msg" : $('#search_box_textarea').val()

    //         },
            
    //         success : function(items) {
    //             // console.log(items[0]);
                
    //             $('#auto0').val(items[0]);
    //             $('#auto1').val(items[1]);
    //             $('#auto2').val(items[2]);
    //             $('#auto3').val(items[3]);
    //             $('#auto4').val(items[4]);
                
                
                
    //         },
    //         error : function(error) {
    //             console.log("에러난다이거");
    //         }
    
    //     })
    //     $('#recommand').css('display','block');
    // }
    

    // 라면 추천버튼 클릭시 AJAX
    $("#ramyunlikes").on("click", function() {
        $.ajax({
            type : "POST",
            url : "./likeramyun.do",
            dataType : "json",
            data : {
                "ramyunName" : $('#ramyunID').val()
            },
            success : function(items) {
                console.log("successAjax");
                console.log(items[0]);
                
                $('#recommand_thumb').text(items[0]);
            },
            error : function(error) {
                console.log("errorAjax");
            }
        })
    })
    // 라면 신고버튼 클릭시 AJAX
    $("#ramyunreporting").on("click", function() {
        $.ajax({
            type : "POST",
            url : "./reportramyun.do",
            dataType : "json",
            data : {
                "ramyunName" : $('#ramyunID').val()
            },
            success : function(items) {
                console.log("successAjax");
                console.log(items[0]);
                $('#reporting_thumb').text(items[0]);
            },
            error : function(error) {
                console.log("errorAjax");
            }
        })
    })
    // 영양성분 추천버튼 클릭시 AJAX
    $("#ingredientlikes").on("click", function() {
        $.ajax({
            type : "POST",
            url : "./likeingredient.do",
            dataType : "json",
            data : {
                "ingredientName" : $('#ingredientID').val()
            },
            success : function(items) {
                console.log("successAjax");
                console.log(items[0]);
                $('#recommand_thumb').text(items[0]);
            },
            error : function(error) {
                console.log("errorAjax");
            }
        })
    })
    // 영양성분 신고버튼 클릭시 AJAX
    $("#ingredientreporting").on("click", function() {
        $.ajax({
            type : "POST",
            url : "./reportingredient.do",
            dataType : "json",
            data : {
                "ingredientName" : $('#ingredientID').val()
            },
            success : function(items) {
                console.log("successAjax");
                console.log(items[0]);
                $('#reporting_thumb').text(items[0]);
            },
            error : function(error) {
                console.log("errorAjax");
            }
        })
    })
    // 제조공장 추천버튼 클릭시 AJAX
    $("#manufactorylikes").on("click", function() {
        $.ajax({
            type : "POST",
            url : "./likemanufactory.do",
            dataType : "json",
            data : {
                "manufactoryName" : $('#manufactoryID').val()
            },
            success : function(items) {
                console.log("successAjax");
                console.log(items[0]);
                $('#recommand_thumb').text(items[0]);
            },
            error : function(error) {
                console.log("errorAjax");
            }
        })
    })
    // 제조공장 신고버튼 클릭시 AJAX
    $("#manufactoryreporting").on("click", function() {
        $.ajax({
            type : "POST",
            url : "./reportmanufactory.do",
            dataType : "json",
            data : {
                "manufactoryName" : $('#manufactoryID').val()
            },
            success : function(items) {
                console.log("successAjax");
                console.log(items[0]);
                $('#reporting_thumb').text(items[0]);
            },
            error : function(error) {
                console.log("errorAjax");
            }
        })
    })
    

    
    
})



/*
sessionStorage.getItem("member");
if(sessionStorage!=null){
    alert(sessionStorage.length);
}

$('#search_box_textarea').value
*/