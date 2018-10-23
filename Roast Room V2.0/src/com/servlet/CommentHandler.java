package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CommentHandler")
public class CommentHandler extends HttpServlet {
	/**
	 * @author Robert Usey
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Not Used
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String name = request.getParameter("idValue");
		String postTime = null;
		String content = null;
		String user = null;

		user = request.getRemoteAddr();
		content = request.getParameter("commentValue");
		postTime = dateFormat.format(date);

		System.out.println(content + " " + name);

		if (!(content.length() > 0)) {
			System.out
					.println("User tried to comment nothing, what an asshole");
		} 

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error: unable to load driver class!");
				System.exit(1);
			}
			// Connect to DB
			String url = "jdbc:mysql://localhost:3306/forum";
			String USER = "root";
			String PASS = "";
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = DriverManager.getConnection(url, USER, PASS);
				// Prepared statement
				String sql = "INSERT INTO comments (comment, post, timestamp, ip)"
						+ "values (?, ?, ?, ?)";
				// Populate and execute the prepared statement
				PreparedStatement preparedStmt = conn.prepareStatement(sql);
				preparedStmt.setString(1, content);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, postTime);
				preparedStmt.setString(4, user);

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

		}

	}


