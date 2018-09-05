package cinemaTheater;

import java.sql.*;

public class Database {

	String driver;
	String url;
	Connection conn;
	Statement stm;
	
	Database() 
	{
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://10.10.12.238/Te3140db";
		conn = null;
		stm = null;
		
	}
	
	@SuppressWarnings("finally")
	Statement connect()
	{
		try
		{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,"Te3140","Te3140");
			stm = conn.createStatement();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			return stm;
		}
	}
	
	void close() throws Exception
	{
		conn.close();
	}

}