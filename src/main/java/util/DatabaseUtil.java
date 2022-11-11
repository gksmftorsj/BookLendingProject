package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnection() {
		try{
			String dbURL="jdbc:oracle:thin:@localhost:1521:orcl"; //접속 DB정보
			String dbID ="scott"; //접속 아이디
			String dbPassword ="tiger"; //접속 아이디의 비밀번호
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
