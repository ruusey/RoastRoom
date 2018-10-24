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

@WebServlet("/GetVoteCount")
public class GetVoteCount extends HttpServlet {

    Logger logger = Logger.getLogger(GetVoteCount.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Used to update the vote count after a vote is made
		PrintWriter out = response.getWriter();
		//Get id of content and calculate the new votes
		int id = Integer.parseInt(request.getParameter("idValue"));
		int updateVotes = getCount(id);
		//Print out the votes so we can grab them for our update
		out.println(updateVotes);
	}
	
	private int getCount(int id) {

		int votes = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}
		String url = "jdbc:mysql://localhost:3306/forum";
		String USER = "root";
		String PASS = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(url, USER, PASS);
			Statement st = conn.createStatement();
			//Big ol SQL
			ResultSet res = st
					.executeQuery("SELECT sum(v.vote) sv FROM votes v WHERE v.content_id="
							+ id + ";");
			//Get the sum of the votes
			if (res.next()) {
				votes = res.getInt("sv");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		logger.info("Succesfully retrieved vote count for post '"+id+"'");
		return votes;
	}
}
