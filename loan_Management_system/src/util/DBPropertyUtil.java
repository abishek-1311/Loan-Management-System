package util;

public class DBPropertyUtil {
	private static final String url = "jdbc:mysql://localhost:3306/loanmanagementsystem";
	private static final String userId = "root";
	private static final String pass = "Abishek@2003";
	
	public static String getPropertyString() {
		
		return url+"?user="+userId+"&password="+pass ;
	}
}
