package com;
import java.sql.*;
import java.io.*;
public class Person {

	public static void main(String args[])throws Exception{
		System.out.println("MySQL Connect Example.");
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "javadb";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url+dbName,userName,password);
		System.out.println("Connected to the database");
		String lastName,firstName,address,city;
		PreparedStatement ps=conn.prepareStatement("insert into persons values(?,?,?,?)");
		Statement stmt = conn.createStatement();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		do{
		//int id=Integer.parseInt(br.readLine());
		//float salary=Float.parseFloat(br.readLine());
		//ps.setFloat(2, salary);
		System.out.println("enter Last Name:");
		lastName=br.readLine();
		System.out.println("enter First Name:");
		firstName=br.readLine();
		System.out.println("Enter your address");
		address=br.readLine();
		System.out.println("Enter your City");
		city=br.readLine();
		ps.setString(1,lastName);
		ps.setString(2,firstName);
		ps.setString(3, address);
		ps.setString(4, city);
		int i=ps.executeUpdate();
		System.out.println(i+" records added");
		System.out.println("Do you want to continue: y/n");
		String s=br.readLine();
		if(s.startsWith("n")){
		break;
		}
		}while(true);
		String sql = "SELECT * from persons";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("The records are :");
		while (rs.next())
		{
			lastName = rs.getString(1);
			firstName=rs.getString(2);
			address=rs.getString(3);
			city=rs.getString(4);
			
		System.out.println(rs.getRow()+"-"+lastName+" "+firstName+ "" +address+""+city);
		} //end while
		conn.close();
		}
	}