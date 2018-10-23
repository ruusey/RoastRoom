
<!-- Author - Robert Usey -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.io.*,java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement,javax.servlet.*,javax.servlet.annotation.WebServlet,javax.servlet.http.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recent Posts</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link href="icon.jpg" rel="icon" type="image/x-icon" />
<link href="icon.jpg" rel="shortcut icon" type="image/x-icon" />
<script src="sendVote.js"></script>
<script src="CommentHandler.js"></script>
<script src="PostHandler.js"></script>
<script src="updateWordCount.js"></script>
<script src="jquery.cookie.js"></script>
<script src="Gruntfile.js"></script>
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
			
			alert("Cookie not found, lets generate a new one");
			var newSession=generateUUID();
			$.cookie("sessionTracker", newSession, { expires: 7 });
			
			alert("Cookie set on this computer computer: " + newSessionName);
		}else{
			alert("Cookie found on computer: " + $.cookie("sessionTracker"));
		}
	}else{
		alert("You wont be able to vote without cookies enabled")
	}
	
	
});

</script>
<button id="homebutton" onclick="window.location.href='/'">&#60;Home</button><h4 style = "text-align:right;">Build 1.1.0</h4>
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
var open = 0;
function expandComments(id){
	
	if(open==0){
		$('div#'+id).height( 820 );
		open=1;
	}else{
		$('div#'+id).height( 630 );
		open=0;
	}
	
	
}
</script>
<script>
function closeDiv() {
	   document.getElementById('NewPostDiv').style.display = "none";
	   document.getElementById('overlay').style.display = "none";
	}
</script>
	<br>
	<%
	
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex) {
		   System.out.println("Error: unable to load driver class!");
		   System.exit(1);
		}
	String url= "jdbc:mysql://localhost:3306/site_data";
	String USER = "root";
	String PASS = "";
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(url, USER, PASS);
		
		 Statement st = conn.createStatement();
		 ResultSet res = st.executeQuery("SELECT * FROM site_words ORDER BY RAND()"); 
		 while (res.next()) {
			
			 String word = res.getString("word"); 
			 double numTimes = (double)res.getInt("occurances");
			 if(numTimes>100){
				 numTimes = numTimes*0.93;
			 }
			 
			 String contents ="   <p style="+"\""+"display:inline-block;font-size:"+numTimes+1+"px;"+"\""+">  "+word+"  "+"</p>   ";
			 
			 out.print(contents);	
				
		 }
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("SQL EXCEPTION");
	} catch (InstantiationException e) {
		System.out.println("INSTANTIATION EXCEPTION");
	} catch (IllegalAccessException e) {
		System.out.println("ILLEGAL ACCESS EXCEPTION");
	} catch (ClassNotFoundException e) {
		System.out.println("CLASS NOT FOUND EXCEPTION EXCEPTION");
	}  
	%>
</body>
</html>