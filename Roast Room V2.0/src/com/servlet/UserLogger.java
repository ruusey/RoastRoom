package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserLogger {
	
 
	public void logAccess(String user, String sip, int success, String info){
		
		
	    try {
			   Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			}
		String url= "jdbc:mysql://localhost:3306/authority";
		String USER = "root";
		String PASS = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, USER, PASS);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String sql = "INSERT INTO user_log (user_name, sip, success, info)" +
			            "values (?, ?, ?, ?)";
			
			PreparedStatement user_log = conn.prepareStatement(sql);
			user_log.setString(1, user);
			user_log.setString(2, sip);
			user_log.setInt(3, success);
			user_log.setString(4, info);
			 
			
			user_log.execute();
		
			
			
		} catch (SQLException e) {
			System.out.println("SQL EXCEPTION");
		} catch (InstantiationException e) {
			System.out.println("INSTANTIATION EXCEPTION");
		} catch (IllegalAccessException e) {
			System.out.println("ILLEGAL ACCESS EXCEPTION");
		} catch (ClassNotFoundException e) {
			System.out.println("CLASS NOT FOUND EXCEPTION EXCEPTION");
		}  
		
			finally{
			
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	    
		
		
	}
    
}


