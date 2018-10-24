<!-- Author - Robert Usey -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recent Posts</title>
<script src="jquery.js"></script>
<link href="icon.jpg" rel="icon" type="image/x-icon" />
<link href="icon.jpg" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="test.css">
<script src="sendVote.js"></script>
<script src="CommentHandler.js"></script>
<script src="PostHandler.js"></script>
<script src="updateWordCount.js"></script>
<script src="jquery.cookie.js"></script>
<script src="uuid.js"></script>
</head>
<body>
<script>
$( document ).ready(function() {
	function cookies_enabled()
	{
		var cookieEnabled = (navigator.cookieEnabled) ? true : false;

		if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled)
		{ 
			document.cookie="testcookie";
			cookieEnabled = (document.cookie.indexOf("testcookie") != -1) ? true : false;
		}
		return (cookieEnabled);
	}
	var cookies_enabled =  cookies_enabled();
	if(cookies_enabled==true){
		if($.cookie("sessionTracker")==undefined){
			
			
			var newSession=generateUUID();
			$.cookie("sessionTracker", newSession, { expires: 7 });
			getPosts();
			
		}else{
			getPosts();
		}
	}else{
		alert("You wont be able to vote without cookies enabled");
	}
	
	
});

</script>
<button id="homebutton" onclick="window.location.href='/'">&#60;Home</button><h4 style = "text-align:right;">Build 1.2.0</h4>
<center>
<h1>Forum Rules: none</h1>
</center>
<div class = "overlay" id="overlay"></div>

<div class="newPostDiv" id="NewPostDiv" style="display:none; height:480px" class="new_post" >
<center>
<h1>Create New Post</h1>
</center>
	<form method="post" id="postform">
			<textarea maxlength="200" oninput="updateCountNewPost()" id="postcontent" rows="10" cols="50"></textarea>
			<p style="padding-right:54px;" class="counter" id="wordCountNewPost">0</p>
			<p class="submit">
				<button id="newPostButton2" onClick="post();">Post</button>
					
			</p>
					
			<button onClick="closeDiv();">Close</button>
			
	</form>
</div>

	<center><button id="newPostButton" onclick="showDiv()" >+New Post</button></center>
<script>
function showDiv() {
	   document.getElementById('NewPostDiv').style.display = "block";
	   document.getElementById('overlay').style.display = "block";

	}
	

</script>
<script>
function expandComments(id){
	
	$("#span"+id).toggle();
	
}
</script>
<script>
function closeDiv() {
	   document.getElementById('NewPostDiv').style.display = "none";
	   document.getElementById('overlay').style.display = "none";
	}
</script>

</body>
</html>