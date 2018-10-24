
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

function getPosts() {
	var input
	if($.cookie("secret") == undefined){
		input = prompt("Whats the secret password?");
		 $.ajax({
			 	beforeSend: function(request) {
    				request.setRequestHeader("pass", input);
  				},
		        url : "GetPosts",
				type:"GET",
				success:function(a,b,c){
					$(document.body).append(a);
					$.cookie("secret", input, { expires: 7 });
				},
				error: function(a,b,c){
					getPosts();
				}
					
		    });
	}else{
		 $.ajax({
			 	beforeSend: function(request) {
    				request.setRequestHeader("pass", $.cookie("secret"));
  				},
		        url : "GetPosts",
				type:"GET",
				success:function(a,b,c){
					$(document.body).append(a);
				},
				error: function(a,b,c){
					getPosts();
				}
					
		    });
	}
		
		
		
		 
	
   
    
}

