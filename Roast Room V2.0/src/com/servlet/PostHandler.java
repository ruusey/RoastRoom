package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostHandler")
public class PostHandler extends HttpServlet {
	/**
	 * @author Robert Usey
	 */
    Logger logger = Logger.getLogger(PostHandler.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Not Used
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		String postTime = null;
		String content = null;
		String user = null;
		//Get data from Post Ajax call and the servlet request
		user = request.getRemoteAddr();
		content = request.getParameter("postValue");
		postTime = dateFormat.format(date);
		//If the post is not long enough, redundant.
		if (!(content.length() > 0)) {
			System.out
					.println("User tried to post something too short");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error: unable to load driver class!");
				System.exit(1);
			}
			//Connect to DB
			String url = "jdbc:mysql://localhost:3306/forum";
			String USER = "root";
			String PASS = "";
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = DriverManager.getConnection(url, USER, PASS);
				//SQL statement
				String sql = "INSERT INTO posts (user, post, time, title)"
						+ "values (?, ?, ?, ?)";
				//Set and execute the prepared statement
				PreparedStatement preparedStmt = conn.prepareStatement(sql);
				preparedStmt.setString(1, user);
				preparedStmt.setString(2, content);
				preparedStmt.setString(3, postTime);
				preparedStmt.setString(4, "Anonymous");

				preparedStmt.execute();

			} catch (SQLException e) {

				e.printStackTrace();
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			logger.info("User: '"+user+"' created new post: '"+content+"'");
		}

	}

}
