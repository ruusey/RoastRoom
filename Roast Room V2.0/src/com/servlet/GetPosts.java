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
	//Used to update the vote count after a vote is made
	PrintWriter out = response.getWriter();
	//Get id of content and calculate the new votes
	String html = GetAllPosts();
	//Print out the votes so we can grab them for our update
	out.println(html);
}
    private String GetAllPosts() {
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
		 StringBuffer buf = new StringBuffer();
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
				
				buf.append("  <div id=\""+id+"\"class=\"post\">");
				
				buf.append("	"+post);
				
				buf.append("	  "+contents);
				
				buf.append("		<center>");
					
				buf.append("		  "+upButton);
					
				buf.append("		  "+downButton);
					
				buf.append("		  "+"<h2 id=v"+id+">"+votes+"</h2>");
					
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
		 }
		 logger.info("Succesfully retrieved all posts.");
		 return buf.toString();
		
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
	return null;
    }

}
