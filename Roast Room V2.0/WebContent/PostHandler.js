
function post() { 
	var postContent = $('textarea#postcontent').val();
	var n = postContent.length;
	if(n>5 && n<201){
		 $.ajax({
		        url : "PostHandler",   
				
		        data : {
		            postValue:postContent,
		        },
		        type:"POST",
		        	
		    });
		 document.getElementById('NewPostDiv').style.display = "none";
		 document.getElementById('overlay').style.display = "block";
	}else if(n<5){
		alert("Post Was Too Short");
	}else{
		alert("Post Must Be Less Than 200 Characters");
	}
   
    
}

