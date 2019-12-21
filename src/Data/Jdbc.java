package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	public static ResultSet rs;
	
	public static void dbConnect(String dbID, String dbPW, String databaseName, String tableName) {
		driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 검색 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("error: " + e);
		}
		
		url = "jdbc:odbc:" + databaseName;
		conn = null;
		stmt = null;
		rs = null;
		String url = "jdbc:mysql://localhost/" + databaseName + "?useUnicode=yes&characterEncoding=UTF8";
		String sql = "Select * From " + tableName;
		
		
		try {
			conn = DriverManager.getConnection(url, dbID, dbPW);
			stmt = conn.createStatement( );
			rs = stmt.executeQuery(sql);
			System.out.println("데이터베이스 연결 완료");
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 실패");
		}
	}
	
	
	public static void dbDisconnect() {
		try {
			if(conn != null) {
				conn.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			System.out.println("데이터베이스 연결 해제");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void query(String command, String sqlCode) throws SQLException {
		System.out.println("Jdbc.query 호출");
		if(command == "select") {
			rs = stmt.executeQuery(sqlCode);
		} else {
			stmt.executeUpdate(sqlCode);
		}
	}
}
