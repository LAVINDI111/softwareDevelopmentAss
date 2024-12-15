package Uni;
import java.sql.*; //import sql package into our class

public class ConnectionManager {

	public static Connection getConnection(){
		//register the driver
		
		Connection conn = null;//initialize the connection Object
		try{
			// Define the database connection Parameters
			String URL = "jdbc:mariadb://localhost:3306/unidatabase";
			String User = "root";
			String Password = "sltuser";
			
			//load and register the mariaDB Driver 
			//DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			
			//create database connection
			conn = DriverManager.getConnection(URL, User, Password);
			System.out.println("Database connection successful!"); // Debug message
			return conn;
			
		}//end of try
		catch(SQLException sq){
			sq.printStackTrace();
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}//end of getConnection method

}//end of ConnectionManager Class

