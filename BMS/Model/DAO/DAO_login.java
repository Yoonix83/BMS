package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.DTO_std;
import Source_Info.Connection_Info;

public class DAO_login {
//	String driver = "com.mysql.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/testdb";
//	String userid = "root";
//	String passwd = "1324";
//
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//
//	public Dao_login() {
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	 Connection_Info con_info = new Connection_Info();
	
	
	// -- 로그인 정보 부분_START
	public DTO_std select_std_num(String id) { // 학생 아이디 매칭 문

		String sql = "SELECT * FROM student WHERE stdNum = ?";
		DTO_std dto_std = new DTO_std();

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, id); // 학생 id 매칭

			rs = pstmt.executeQuery(); // 실행 커리 결과 값 객체 (rs)

			rs.next();
			dto_std.set_stdNum(rs.getString("stdNum")); // rs.getString("") 의 string 이 DB의 컬럼과 정확히 똑같아야 한다.
			dto_std.set_stdName(rs.getString("stdName"));
			dto_std.set_stdGrade(rs.getString("stdGrade"));
			dto_std.set_stdMajor(rs.getString("stdMajor"));
			dto_std.set_stdPw(rs.getString("stdPw"));
			dto_std.set_stdPhone(rs.getString("stdPhone"));

			System.out.println(rs.getString("stdNum"));
			System.out.println(rs);
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "아이디를 다시 확인하여 주십시요.^-^");
			
		}

		return dto_std;

	}


	
}
