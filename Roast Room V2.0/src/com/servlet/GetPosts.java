package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * 
 *
 */
@WebServlet("/GetPosts")
public class GetPosts extends HttpServlet{

    Logger logger = Logger.getLogger(GetPosts.class.getName());
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	if(request.getHeader("pass").equals("chipsi123")) {
	    String session = null;
	    String rawCookie = request.getHeader("Cookie");
	    String[] rawCookieParams = rawCookie.split(";");
	    for(String rawCookieNameAndValue :rawCookieParams)
	    {
	      String[] rawCookieNameAndValuePair = rawCookieNameAndValue.split("=");
	      if(rawCookieNameAndValuePair[0].equals("sessionTracker")) {
		  session = rawCookieNameAndValuePair[1];
	      }
	    }
	    PrintWriter out = response.getWriter();
		//Get id of content and calculate the new votes
		String html = GetAllPosts(session);
		//Print out the votes so we can grab them for our update
		out.println(html);
	}else {
	   response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Incorrect Password");
	}
	//Used to update the vote count after a vote is made
	
}
    private String GetAllPosts(String session) {
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
		 Statement st3 = conn.createStatement();
		 ResultSet res = st.executeQuery("SELECT * FROM posts ORDER BY id DESC"); 
		 StringBuffer buf = new StringBuffer();
		 while (res.next()) {
			
			 String title = res.getString("title"); 
			 String timeStamp = res.getString("time");
			 
			 String content = res.getString("post");
			 int id = res.getInt("id");
			 int votes = 0;
			 int upVotes=0;
			 int downVotes=0;
			 votes = GetVoteCount.getCountTotal(id);
			 
			 upVotes= GetVoteCount.getCountUp(id);
			 downVotes= GetVoteCount.getCountDown(id);
			 int voted = GetVoteCount.getVoteStatus(session, id);
			 System.out.println(" voted "+id+" "+voted);
				String post = "<h1 class=\"post-title\">"+title+" @ "+timeStamp+"</h1>";
				String brk = "<br>";
				
				
				//<button type="button" id="downvote-button" >downVote</button>
				//<button type="button" id="upvote-button" >upVote</button>
				
				
				//<input value="Hello" type="button" onClick="gotoNode(result.name)" />​
				String contents ="<p>"+content+"</p>";
				
				String upButton = "<button type="+"\""+"button"+"\""+"id="+"\""+"buttonu-"+id+"\""+"class="+"\""+"upvote-button"+"\" onClick=\"vote("+id+",1"+")\" >"+"Up Vote</button>";
				
				String downButton = "<button type="+"\""+"button"+"\""+"id="+"\""+"buttond-"+id+"\""+"class="+"\""+"downVote-button"+"\" onClick=\"vote("+id+",-1"+")\" >"+"Down Vote</button>";
				if(voted==1) {
				    
				    upButton = "<button style='background-color:gray;background-image:none;' type="+"\""+"button"+"\""+"id="+"\""+"buttonu-"+id+"\""+"class="+"\""+"upvote-button"+"\" onClick=\"vote("+id+",1"+")\" disabled >"+"Up Vote</button>";
				    downButton = "<button style='background-color:red;background-image:none;' type="+"\""+"button"+"\""+"id="+"\""+"buttond-"+id+"\""+"class="+"\""+"downVote-button"+"\" onClick=\"vote("+id+",-1"+")\" >"+"Down Vote</button>";
				}else if(voted==-1) {
				    upButton = "<button style='background-color:green;background-image:none;' type="+"\""+"button"+"\""+"id="+"\""+"buttonu-"+id+"\""+"class="+"\""+"upvote-button"+"\" onClick=\"vote("+id+",1"+")\" >"+"Up Vote</button>";
				    downButton = "<button style='background-color:gray;background-image:none;' type="+"\""+"button"+"\""+"id="+"\""+"buttond-"+id+"\""+"class="+"\""+"downVote-button"+"\" onClick=\"vote("+id+",-1"+")\" disabled >"+"Down Vote</button>";
				}
				String commentButton = "<button type="+"\""+"button"+"\""+"class="+"\""+"comment-button"+"\" onClick=\"comment("+id+")\" >"+"Comment</button>";
				
				buf.append("  <div id=\""+id+"\"class=\"post\">");
				
				buf.append("	"+post);
				
				buf.append("	  "+contents);
				
				buf.append("		<center>");
					
				buf.append("		  "+upButton);
					
				buf.append("		  "+downButton);
					
				buf.append("		  "+"<h2 id='v"+id+"'>"+votes+"</h2>");
				buf.append("		  "+"<h2 id='vup"+id+"' style='color:green;'>"+upVotes+"</h2>");
				buf.append("		  "+"<h2 id='vdwn"+id+"' style='color:red;'>"+downVotes+"</h2>");
					
				buf.append("		  "+"<textarea maxlength=\"300\" oninput=\"updateCount("+id+")\" " + "id=\""+id+"comment"+"\" name=\"postcontent\" rows=\"10\" cols=\"50\" form=\"postform\"></textarea>" +
									  "<p class=\"counter\" id=\"wordCount"+id+"\">0</p>");
				
				buf.append("		  "+commentButton);
				
				
				
				buf.append("		  "+"<h1 onClick=\"expandComments("+id+")\" id = \"commentHeader\">Comments(Newest--->Oldest)</h1>");
				
				buf.append("		</center>");
				
				buf.append("		<span style=\"display:none;"+"\"id=\"span"+id+"\"class=\"spanComments\">");
				
					ResultSet res3 = st3.executeQuery("SELECT * FROM comments WHERE post = "+id+" ORDER BY idcomments DESC");
					int commentCount=1;
					while(res3.next()){
					    buf.append("		"+"<p class=\"comment\">"+commentCount+") "+res3.getString("comment")+"</p>");
						commentCount++;
					}
					
					buf.append("  	  </span>");
					buf.append("  </div>");
					buf.append(brk);
					buf.append(brk);
					res3.close();
					
					
					
		 }
		 st3.close();
		 st.close();
		 res.close();
		 logger.info("Succesfully retrieved all posts.");
		 conn.close();
		 return buf.toString();
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("SQL EXCEPTION");
	} 
	
	return null;
    }

}
