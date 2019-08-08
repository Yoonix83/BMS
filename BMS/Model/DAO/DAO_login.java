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
	
	
	// -- �α��� ���� �κ�_START
	public DTO_std select_std_num(String id) { // �л� ���̵� ��Ī ��

		String sql = "SELECT * FROM student WHERE stdNum = ?";
		DTO_std dto_std = new DTO_std();

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, id); // �л� id ��Ī

			rs = pstmt.executeQuery(); // ���� Ŀ�� ��� �� ��ü (rs)

			rs.next();
			dto_std.set_stdNum(rs.getString("stdNum")); // rs.getString("") �� string �� DB�� �÷��� ��Ȯ�� �Ȱ��ƾ� �Ѵ�.
			dto_std.set_stdName(rs.getString("stdName"));
			dto_std.set_stdGrade(rs.getString("stdGrade"));
			dto_std.set_stdMajor(rs.getString("stdMajor"));
			dto_std.set_stdPw(rs.getString("stdPw"));
			dto_std.set_stdPhone(rs.getString("stdPhone"));

			System.out.println(rs.getString("stdNum"));
			System.out.println(rs);
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "���̵� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
			
		}

		return dto_std;

	}


	
}
