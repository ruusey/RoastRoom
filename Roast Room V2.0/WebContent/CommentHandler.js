	
function comment(id) { 
	//Get content of comment textarea
	//Get length of the string
	
	var comments = $("#"+id+"comment").val();
	var n = comments.length;
	
	//If comment is an appropriate length
	if(n>0 && n<301){
		$.ajax({
	    	
	        url : "CommentHandler",   
			
	        data : {
	        	
	            idValue:id,
	            commentValue:comments 
	        },
	        type:"POST",
	        	
	    });
		//Reload page to update comments
		
	    window.location.reload();
	    
	    //Tell user their comment is not the right length
	    //Even though the check is performed in the textarea,
	    //Better to have extra protection
	}else if(n==0){
		alert("Comment Cannot Be Empty!");
	}else{
		alert("Comment Must Be Less Than 300 Characters");
	}
    
}