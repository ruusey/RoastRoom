
function vote(id, vote) {
	
	//Ajax call to vote handler
	var sessionName=$.cookie('sessionTracker');
	//alert(Number(document.getElementById("v"+id).innerHTML)+Number(vote));
	//document.getElementById("v"+id).innerHTML = Number(document.getElementById("v"+id).innerHTML)+Number(vote);
    $.ajax({
    	
        url : "VoteHandler",   
		
        data : {
        	
        	//Pass vote handler the id of the content and its respective vote value
        	//1 or -1
        	sessionId: sessionName, 
            idValue:id,
            voteValue:vote 
        },
        type:"POST",
        	
    });
    
    //Get response from Get Vote Count servlet
    //Update the number of votes for the post
    setTimeout(getVotes, 100 ,id);
    
    
}
function getVotes(id){
	var response = '';
    $.ajax({ type: "GET",   
             url: "GetVoteCount?idValue="+id,   
             async: false,
             success : function(text)
             {
                 response = text;
             }
    });
    document.getElementById("v"+id).innerHTML = response;
    //Update Vote Count
    
}

