package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {
	private static Connection con;
	
	public static Connection getConnection() throws Exception{
		con= DriverManager.getConnection(DBPropertyUtil.getPropertyString());
		return con;
	}
	
}
