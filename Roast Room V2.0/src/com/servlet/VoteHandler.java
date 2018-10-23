package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Not Used
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In Vote handler!");
		//Get values from JQuery script calls
		String id = request.getParameter("idValue");
		String vote = request.getParameter("voteValue");
		String sessionTracker = request.getParameter("sessionId");
		String sip = request.getRemoteAddr();
		
		System.out.println(sessionTracker + " " + id + " " + vote);

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
			String sql = "INSERT INTO votes (user_id, content_id, vote, ip)"
					+ "values (?, ?, ?, ?)" + "ON DUPLICATE KEY UPDATE vote=?;";
			//Generate our prepared statement for the DB
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, sessionTracker);
			preparedStmt.setString(2, id);
			preparedStmt.setString(3, vote);
			preparedStmt.setString(4, sip);
			preparedStmt.setString(5, vote);

			preparedStmt.execute();

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
