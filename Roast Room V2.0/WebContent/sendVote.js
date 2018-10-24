function vote(id, vote) {

    //Ajax call to vote handler
    var sessionName = $.cookie('sessionTracker');
    //alert(Number(document.getElementById("v"+id).innerHTML)+Number(vote));
    //document.getElementById("v"+id).innerHTML = Number(document.getElementById("v"+id).innerHTML)+Number(vote);
    $.ajax({

        url: "VoteHandler",

        data: {

            //Pass vote handler the id of the content and its respective vote value
            //1 or -1
            sessionId: sessionName,
            idValue: id,
            voteValue: vote
        },
        async:false,
        type: "POST",
       

    }).done(function(){
         $.ajax({
                type: "GET",
                async: false,
                url: "GetVoteCount?idValue=" + id,
                
            }).done(function(text){
                
                     var arr = text.split(",");
                    document.getElementById("v" + id).innerHTML = arr[0];
                    document.getElementById("vdwn" + id).innerHTML = arr[1];
                    document.getElementById("vup" + id).innerHTML = arr[2];
                    console.log(arr[3]);
                    if(arr[3]==1){
                        console.log("here 1")
                        $("#buttonu-"+id).prop("disabled", true).css("background-color","gray").css("background-image","none");
                        $("#buttond-"+id).prop("disabled", false).css("background-color","red").css("background-image","none");
                    }else if(arr[3]==-1){
                        console.log("here -1")
                         $("#buttonu-"+id).prop("disabled", false).css("background-color","green").css("background-image","none");
                        $("#buttond-"+id).prop("disabled", true).css("background-color","gray").css("background-image","none");
                    }
                
            });
        
            
    });

    //Get response from Get Vote Count servlet
   }   //Update the number of votes for the post
