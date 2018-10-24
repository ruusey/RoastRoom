package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetVoteCount")
public class GetVoteCount extends HttpServlet {

    static Logger logger = Logger.getLogger(GetVoteCount.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		try {
		    int id = Integer.parseInt(request.getParameter("idValue"));
			String updateVotes = getCount(id,session);
			//Print out the votes so we can grab them for our update
			out.println(updateVotes);
		}catch(Exception e) {
		   if (request.getParameter("idValue").equals("*")){
		       ArrayList<Integer> ids = getVoteIds();
		       String[] stringArray = ids.toArray(new String[0]);
		       out.println(stringArray);
		   }
		}
		
	}
	static public int getCountTotal(int id) {

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
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		//logger.info("Succesfully retrieved vote count for post '"+id+"'");
		return votes;
	}
	static public ArrayList<Integer> getVoteIds() {

		ArrayList<Integer> ids = new ArrayList<Integer>();
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
					.executeQuery("SELECT content_id FROM votes;");
//			System.out.println("SELECT vote FROM votes v WHERE v.user_id='"+ uuid +"' AND v.content_id="
//				+ id + ";");
			//Get the sum of the votes
			if (res.next()) {
			   int votes = res.getInt("content_id");
			   ids.add(votes);
			}
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		//logger.info("Succesfully retrieved vote count for post '"+id+"'");
		return ids;
	}
	static public int getVoteStatus(String uuid,int id) {

		int voted=0;
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
					.executeQuery("SELECT vote FROM votes v WHERE v.user_id='"+ uuid +"' AND v.content_id="
							+ id + ";");
//			System.out.println("SELECT vote FROM votes v WHERE v.user_id='"+ uuid +"' AND v.content_id="
//				+ id + ";");
			//Get the sum of the votes
			if (res.next()) {
			   int votes = res.getInt("vote");
			   return votes;
			}
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		//logger.info("Succesfully retrieved vote count for post '"+id+"'");
		return voted;
	}
	static public String getCount(int id,String session) {

		String votes = "";
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
				votes += getCountTotal(id)+",";
				votes += getCountDown(id)+",";
				votes += getCountUp(id)+",";
				votes += getVoteStatus(session,id);
			}
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		//logger.info("Succesfully retrieved vote count for post '"+id+"'");
		return votes;
	}
	static public int getCountDown(int id) {

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
							+ id + " AND v.vote=-1;");
			//Get the sum of the votes
			if (res.next()) {
				votes = res.getInt("sv");
			}
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		//logger.info("Succesfully retrieved vote count for post '"+id+"'");
		return votes;
	}
	static public int getCountUp(int id) {

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
							+ id + " AND v.vote=1;");
			//Get the sum of the votes
			if (res.next()) {
				votes = res.getInt("sv");
			}
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		//logger.info("Succesfully retrieved vote count for post '"+id+"'");
		return votes;
	}
}
