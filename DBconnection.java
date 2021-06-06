package com.spring.springTest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	
	private static DBconnection instance;

	private String url= "jdbc:mysql://localhost:3306/spring";
	private String  username= "root";
	private String password="password";
	
	
	
	public Connection getConnection() {
		Connection con=null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con=DriverManager.getConnection(url, username,password);
	           
	        } catch (ClassNotFoundException e) {
	        	e.printStackTrace();
	        }catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return con;
	        
	         
	}
    
    public static DBconnection getInstance() {
        if (instance == null) {
            instance = new DBconnection();
        } else 
        	try{
        		
        	if(instance.getConnection().isClosed()) {
            instance = new DBconnection();
        	}
        
        	}catch (SQLException e) {
        		e.printStackTrace();
        
        	}
        return instance;
    }

	
	}

	
	


