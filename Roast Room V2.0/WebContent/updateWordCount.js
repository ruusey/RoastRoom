	
function updateCount(id) {
	//Update the letter count for a new comment being written
	var postContent = $("#"+id+"comment").val();
	var n = postContent.length;
   if(n<301){
	   document.getElementById("wordCount"+id).innerHTML = n+"(Max 300)";
   }else{
	   document.getElementById("wordCount"+id).innerHTML = "(Max 300)";
   }
    
}
function updateCountNewPost() {
	//Update the letter count for a new post being written
	var postContent = $('textarea#postcontent').val();
	var n = postContent.length;
   if(n<201){
	   document.getElementById("wordCountNewPost").innerHTML = n+"(Max 200)";
   }else{
	   document.getElementById("wordCountNewPost").innerHTML = "(Max 200)";
   }
    
}

