window.onload=function(){
    
    
    document.getElementById('layoutInnerId').style.left=(400+'px');

    $(window).resize(function(){

        var layoutInnerSizeWidth =  ((window.innerWidth/2)-201);
        document.getElementById('layoutInnerId').style.left=(layoutInnerSizeWidth+'px');



        
    })



    







}