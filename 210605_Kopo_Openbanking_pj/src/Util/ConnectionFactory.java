package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@inputyourIP:inputyourSID";
			String user = "userID";
			String pw = "userPW";
			conn = DriverManager.getConnection(url,user,pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
