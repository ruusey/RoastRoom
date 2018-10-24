package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VoteHandler")
public class VoteHandler extends HttpServlet {
	/**
	 * @author Robert Usey
	 */
    	Logger logger = Logger.getLogger(VoteHandler.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Not Used
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//Get values from JQuery script calls
		String id = request.getParameter("idValue");
		String vote = request.getParameter("voteValue");
		String sessionTracker = request.getParameter("sessionId");
		String sip = request.getRemoteAddr();
		int voteInt=0;
		int idInt=0;
		try {
		    voteInt=Integer.parseInt(vote);
		    idInt=Integer.parseInt(id);
		}catch(Exception e) {
		    
		}
		if(voteInt==1) {
		    logger.info("UUID: '"+sessionTracker + "' Upvoted post: " + id);
		}else {
		    logger.info("UUID: '"+sessionTracker + "' Downvoted post: " + id);
		}
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}
		if(sessionTracker!=null){
			
		}
		//Connect to the DB
		String url = "jdbc:mysql://localhost:3306/forum";
		String USER = "root";
		String PASS = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection conn = DriverManager.getConnection(url, USER, PASS);
			//SQL statement
			
			String sql =null;
			
			    sql = "INSERT INTO votes (user_id, content_id, vote, ip)"
					+ "values (?, ?, ?, ?)" + " ON DUPLICATE KEY UPDATE vote="+voteInt+";";
			
			
			//Generate our prepared statement for the DB
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, sessionTracker);
			preparedStmt.setString(2, id);
			preparedStmt.setString(3, vote);
			preparedStmt.setString(4, sip);
//			preparedStmt.setString(5, vote);
			System.out.println(preparedStmt.toString());
			preparedStmt.execute();
			conn.close();

		} catch (SQLException e) {
			if (e.getMessage().toLowerCase().indexOf("duplicate") < 0) {
				e.printStackTrace();
			}

		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

}
