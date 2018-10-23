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
<link rel="stylesheet" type="text/css" href="test.css">
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
			
			
			var newSession=generateUUID();
			$.cookie("sessionTracker", newSession, { expires: 7 });
			
			
		}else{
			
		}
	}else{
		alert("You wont be able to vote without cookies enabled");
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
	<br>
	<%
	
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex) {
		   System.out.println("Error: unable to load driver class!");
		   System.exit(1);
		}
	String url= "jdbc:mysql://localhost:3306/forum";
	String USER = "root";
	String PASS = "";
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(url, USER, PASS);
		
		 Statement st = conn.createStatement();
		 Statement st2 = conn.createStatement();
		 Statement st3 = conn.createStatement();
		 ResultSet res = st.executeQuery("SELECT * FROM posts ORDER BY id DESC"); 
		 while (res.next()) {
			
			 String title = res.getString("title"); 
			 String timeStamp = res.getString("time");
			 
			 String content = res.getString("post");
			 int id = res.getInt("id");
			 int votes = 0;
			 
			 ResultSet res2 = st2.executeQuery("select p.user,p.post,sum(v.vote) sv from forum.posts p,forum.votes v where p.id=v.content_id AND p.id="+id+";");
			 if(res2.next()){
				 votes=res2.getInt("sv");
			 }
			 	
				String post = "<h1 class=\"post-title\">"+title+" @ "+timeStamp+"</h1>";
				String brk = "<br>";
				
				
				//<button type="button" id="downvote-button" >downVote</button>
				//<button type="button" id="upvote-button" >upVote</button>
				
				
				//<input value="Hello" type="button" onClick="gotoNode(result.name)" />​
				String contents ="<p>"+content+"</p>";
				
				String upButton = "<button type="+"\""+"button"+"\""+"class="+"\""+"upvote-button"+"\" onClick=\"vote("+id+",1"+")\" >"+"Up Vote</button>";
				
				String downButton = "<button type="+"\""+"button"+"\""+"class="+"\""+"downVote-button"+"\" onClick=\"vote("+id+",-1"+")\" >"+"Down Vote</button>";
				
				String commentButton = "<button type="+"\""+"button"+"\""+"class="+"\""+"comment-button"+"\" onClick=\"comment("+id+")\" >"+"Comment</button>";
				
				out.println("  <div id=\""+id+"\"class=\"post\">");
				
				out.println("	"+post);
				
				out.println("	  "+contents);
				
				out.println("		<center>");
					
				out.println("		  "+upButton);
					
				out.println("		  "+downButton);
					
				out.println("		  "+"<h2 id=v"+id+">"+votes+"</h2>");
					
				out.println("		  "+"<textarea maxlength=\"300\" oninput=\"updateCount("+id+")\" " + "id=\""+id+"comment"+"\" name=\"postcontent\" rows=\"10\" cols=\"50\" form=\"postform\"></textarea>" +
									  "<p class=\"counter\" id=\"wordCount"+id+"\">0</p>");
				
				out.println("		  "+commentButton);
				
				
				
				out.println("		  "+"<h1 onClick=\"expandComments("+id+")\" id = \"commentHeader\">Comments(Newest--->Oldest)</h1>");
				
				out.println("		</center>");
				
				out.println("		<span style=\"display:none;"+"\"id=\"span"+id+"\"class=\"spanComments\">");
				
					ResultSet res3 = st3.executeQuery("SELECT * FROM comments WHERE post = "+id+" ORDER BY idcomments DESC");
					int commentCount=1;
					while(res3.next()){
				out.println("		"+"<p class=\"comment\">"+commentCount+") "+res3.getString("comment")+"</p>");
						commentCount++;
					}
				out.println("  	  </span>");
				out.println("  </div>");
				out.println(brk);
				out.println(brk);
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