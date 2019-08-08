package Source_Info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_Info {
	
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/bms?serverTimezone=UTC";
	String userid = "pj_bms";
	String passwd = "1324";

	Connection con = null;


	public Connection_Info() { // 드라이버 로드
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection con() throws SQLException { // Connection
		this.con = DriverManager.getConnection(url, userid, passwd);
		return con;
	}
	
//	public static void main(String[] args) throws SQLException {
//		Connection_Info c_info = new Connection_Info();
//		System.out.println(c_info.con());
//		
//	}

}
