package com.spring.springTest.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.spring.springTest.db.DBconnection;
import com.spring.springTest.model.Emp;

public class Employee implements EmployeeDao {
	
    private static DBconnection conn = DBconnection.getInstance();
	public  int insertEmployee(Emp emp) {
		PreparedStatement statement= null;  //SQL statement template is created& sent to the database. Certain values are left unspecified, called parameters (labeled "?").- preparestmt
		
		try{
			
			String sql = "insert into employee(firstname,lastname,username,password,email,roleid) values(?,?,?,?,?,?)";
			
			Connection con = conn.getConnection();
			statement =con.prepareStatement(sql);
			statement.setString(1,emp.getFirstname());
			statement.setString(2,emp.getLastname());
			statement.setString(3,emp.getUsername());
			statement.setString(4,getMD5EncryptedValue( emp.getPassword()));	
			statement.setString(5,emp.getEmail());
		   statement.setString(6, emp.getRoleid());
			System.out.println("inserted successfully");
			 int counter = statement.executeUpdate();
	
			con.close();
			return counter;
		}catch(SQLException e) {
			e.printStackTrace(); //The printStackTrace() method in Java is a tool used to handle exceptions and errors.
				
		}
		return 0;

	}
	public int UpdateEmployee(Emp emp) {
		String sql = "Update employee set firstname = ?,lastname = ?,username = ?,password = ?,email = ?,roleid = ? where empid = ?";
	
	try
	{
		Connection con = conn.getConnection();
		PreparedStatement statement =con.prepareStatement(sql);
		statement.setString(1,emp.getFirstname());
		statement.setString(2,emp.getLastname());
		statement.setString(3,emp.getUsername());
		statement.setString(4,emp.getPassword());
		statement.setString(5,emp.getEmail());
		statement.setString(6,emp.getRoleid());
		statement.setString(7,emp.getEmpid());
	int counter =	statement.executeUpdate();
		System.out.println("counter"+counter);
	  return counter;
	//System.out.println("connected to database");
		}catch(SQLException e) {
		e.printStackTrace();
	}
	return 0;
	}
	
	public int deleteEmployee(String empid) {
		String sql = "delete from employee where empid=?";
		try
		{
			Connection con = conn.getConnection();
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setString(1,empid);		
			int counter=statement.executeUpdate();
			System.out.println("a row has been deleted"+counter);
			con.close();
			return counter;
		
			}catch(SQLException e) {
			e.printStackTrace();
		return 0;
			}
	}
	

	public List<Emp> listEmployee() {
	    List<Emp> list=new ArrayList<Emp>();  //List is an interface and ArrayList is a class.
   // public  list<Employee>  listEmployee() {
    String sql = "select employee.empid, firstname,lastname,username,password,email,role.role from employee"
   	+" INNER JOIN role ON  role.id=employee.roleid";  //creates a new table by combining rows have matching values in two or more tables. used to retrieve data  in both tables.
    	
    		try
		{
			Connection con = conn.getConnection();
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql); //used to update data.
			while(rs.next()) {
				Emp emp = new Emp();	
			emp.setEmpid(rs.getString("empid"));
			emp.setFirstname(rs.getString("firstname"));
			emp.setLastname(rs.getString("lastname"));
			emp.setUsername(rs.getString("username"));
			emp.setPassword(rs.getString("password"));
			emp.setEmail(rs.getString("email"));
			emp.setRole(rs.getString("role"));
			
			list.add(emp);
			
			}		
			
			}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public Emp userById(String empid) { //for webpage update method
        Emp use = new Emp();
        String sql = "select firstname,lastname,username,password,email,roleid from employee where empid=?";
        try {
            Connection con = conn.getConnection();
            PreparedStatement preparedstmt = con.prepareStatement(sql);
            preparedstmt.setString(1, empid);
            
            ResultSet rs = preparedstmt.executeQuery();
            if (rs.next()) {
            	use.setFirstname(rs.getString("Firstname"));
            	use.setLastname(rs.getString("Lastname"));
            	use.setUsername(rs.getString("Username"));
            	use.setPassword(rs.getString("Password"));
            	use.setEmail(rs.getString("Email"));
            	use.setRole(rs.getString("roleid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return use;
    }


public JSONObject fetch(String  empid) {    //json-JavaScript Object Notation --sending some data from the server to the client, so it can be displayed on a web page
	JSONObject jsonObject = new JSONObject();
		try {
		String sql = "select firstname,lastname,username,password,email,roleid from employee where roleid=?";
	
		Connection con = conn.getConnection();
		PreparedStatement statement =con.prepareStatement(sql);
		statement.setString(1, empid);
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
		
		jsonObject.put("firstname",rs.getString("firstname"));
		jsonObject.put("lastname",rs.getString("lastname"));
		jsonObject.put("username",rs.getString("username"));
		jsonObject.put("password",rs.getString("password"));
		jsonObject.put("email",rs.getString("email"));
		jsonObject.put("role", rs.getString("roleid"));
		}		
		}catch(SQLException e) {
		e.printStackTrace();
	}
	return jsonObject;
}



public JSONObject fetchrole() {
	JSONObject jsonObject = new JSONObject();
	JSONArray jsonArr = new JSONArray();
	try {
		String sql = "select id,role from role ORDER BY role";
	
		Connection con = conn.getConnection();
		PreparedStatement statement =con.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
		JSONObject json = new JSONObject();
		json.put("id",rs.getString("id"));
		json.put("role",rs.getString("role"));
		jsonArr.put(json);
		}	
		jsonObject.put("rolelist", jsonArr);
		}catch(SQLException e) {
		e.printStackTrace();
	}
	return jsonObject;
}

public JSONObject search(String  username) {
	JSONObject jsonObject = new JSONObject();
	JSONArray jsonarr = new JSONArray();
		try {
		String sql = "select employee.empid, firstname,lastname,username,email,role.role from employee INNER JOIN role ON  role.id=employee.roleid where username LIKE ? ";
	//String sql = 	"select firstname,lastname,username,email,roleid from employee where username LIKE ?";
		Connection con = conn.getConnection();
		PreparedStatement statement =con.prepareStatement(sql);
		System.out.println("id"+username);
		statement.setString(1, "%"+username+"%");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("empid",rs.getString("empid"));
		jsonobj.put("firstname",rs.getString("firstname"));
		jsonobj.put("lastname",rs.getString("lastname"));
		jsonobj.put("username",rs.getString("username"));
		//jsonobj.put("password",rs.getString("password"));
		jsonobj.put("email",rs.getString("email"));
		jsonobj.put("role", rs.getString("role"));
		jsonarr.put(jsonobj);
		}		
		System.out.println("details fetched successfully");
		jsonObject.put("searchlist", jsonarr);
		}catch(SQLException e) {
		e.printStackTrace();
	}
	return jsonObject;
}

public JSONObject login(JSONObject json) {
	JSONObject jsonobj = new JSONObject();
	try {
		String sql = "select username,password FROM employee WHERE username=? AND password=?";
		Connection con = conn.getConnection();
		PreparedStatement statement =con.prepareStatement(sql);
		statement.setString(1,json.getString("username"));
		statement.setString(2,getMD5EncryptedValue( json.getString("password")));
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			jsonobj.put("msg", "login successful");
		}
		else {
			jsonobj.put("msg", "login failed");
		}
		}catch(SQLException e) {
		e.printStackTrace();
	}
	return jsonobj;
	}

public JSONObject fileupload(String filename,String encodedString) {
	JSONObject jsonobj = new JSONObject();
	try{
		
		String sql = "insert into fileupload(filedata,filename) values(?,?)";
		Connection con = conn.getConnection();
		PreparedStatement statement =con.prepareStatement(sql);
		statement.setString(1,encodedString);
		statement.setString(2, filename);
		int counter= statement.executeUpdate();
	    con.close();
	    if(counter>0){
	    	jsonobj.put("msg", "upload successful");
	    }
	    else {
	    	jsonobj.put("msg", "upload failed");
	    }
		}catch(SQLException e) {
		e.printStackTrace(); 
			
	}
	return jsonobj;
	
}

private static String getMD5EncryptedValue(String password) {
    final byte[] defaultBytes = password.getBytes();
    try {
        final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
        md5MsgDigest.reset();
        md5MsgDigest.update(defaultBytes);
        final byte messageDigest[] = md5MsgDigest.digest();
        final StringBuffer hexString = new StringBuffer();
        for (final byte element : messageDigest) {
            final String hex = Integer.toHexString(0xFF & element);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        password = hexString + "";
    } catch (final NoSuchAlgorithmException nsae) {
        nsae.printStackTrace();
    }
    return password;
}}
