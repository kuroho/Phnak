$(document).ready(function(){
    if(!$("#floatingInput").val().match("\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b")){
        console.log($("#floatingInput").val());
        console.log($("#floatingInput").val().match("\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b"));
        console.log("ok");
    }

    $("#floatingMonsieur").click(function(){
       if($("#floatingMadame").prop("checked", true)){
           $("#floatingMadame").prop("checked", false);
       }
    });

    $("#floatingMadame").click(function(){
        if($("#floatingMonsieur").prop("checked", true)){
            $("#floatingMonsieur").prop("checked", false);
        }
    });
/*
    if($("#submit").prop("disabled",true)){
        $("#submit").css('cursor', 'not-allowed');
    }
*/
});
