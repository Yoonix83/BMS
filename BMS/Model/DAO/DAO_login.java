package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.DTO_emp;
import DTO.DTO_pro;
import DTO.DTO_std;
import Source_Info.Connection_Info;

public class DAO_login {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	 Connection_Info con_info = new Connection_Info();
	 
	 DTO_std dto_std = new DTO_std();
	 DTO_pro dto_pro = new DTO_pro();
	 DTO_emp dto_emp = new DTO_emp();
	
	
	// -- �α��� ���� �κ�_START
	 // �л� ��ȣ ��¡
	public DTO_std select_std_num(String id) { // �л� ���̵� ��Ī ��

		String sql = "SELECT * FROM student WHERE stdNum = ?";

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

			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "���̵� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
			
		}

		return dto_std;

	}
	
	 // ���� ��ȣ ��¡
		public DTO_pro select_pro_num(String id) { 

			String sql = "SELECT * FROM professor WHERE proNum = ?";

			try {

				pstmt = con_info.con().prepareStatement(sql);

				pstmt.setString(1, id); 

				rs = pstmt.executeQuery(); 

				rs.next();
				dto_pro.set_proNum(rs.getString("proNum")); 
				dto_pro.set_proName(rs.getString("proName"));
				dto_pro.set_proGrade(rs.getString("proGrade"));
				dto_pro.set_proMajor(rs.getString("proMajor"));
				dto_pro.set_proPw(rs.getString("proPw"));
				dto_pro.set_proPhone(rs.getString("proPhone"));


			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "���̵� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
				
			}

			return dto_pro;

		}
		
		// ���� ��ȣ ��¡
				public DTO_emp select_emp_num(String id) { 

					String sql = "SELECT * FROM employee WHERE empNum = ?";

					try {

						pstmt = con_info.con().prepareStatement(sql);

						pstmt.setString(1, id); 

						rs = pstmt.executeQuery(); 

						rs.next();
						dto_emp.set_empNum(rs.getString("empNum")); 
						dto_emp.set_empName(rs.getString("empName"));
						dto_emp.set_empDpt(rs.getString("empDpt"));
						dto_emp.set_empMgr(rs.getString("empMgr"));
						dto_emp.set_empPw(rs.getString("empPw"));
						dto_emp.set_empPhone(rs.getString("empPhone"));


					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "���̵� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
						
					}

					return dto_emp;

				}
				// �α��� -- END
				
				//--  ��й�ȣ ã��
				public DTO_std select_std_find(String num) { // �л� ��ȣ ��Ī ��

					String sql = "SELECT stdName, stdPw FROM student WHERE stdNum = ?";

					try {

						pstmt = con_info.con().prepareStatement(sql);

						pstmt.setString(1, num); // �л� ��ȣ

						rs = pstmt.executeQuery();

						rs.next();
						dto_std.set_stdName(rs.getString("stdName"));
						dto_std.set_stdPw(rs.getString("stdPw"));
						
					} catch (Exception e) {
						
						JOptionPane.showMessageDialog(null, "[ �й� ]�� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
						
					}

					return dto_std;

				}

				public DTO_pro select_pro_find(String num) { // ���� ��ȣ ��Ī ��

					String sql = "SELECT proName, proPw FROM professor WHERE proNum = ?";
				
					try {

						pstmt = con_info.con().prepareStatement(sql);

						pstmt.setString(1, num); // ���� ��ȣ

						rs = pstmt.executeQuery();

						rs.next();
						dto_pro.set_proName(rs.getString("proName"));
						dto_pro.set_proPw(rs.getString("proPw"));
						
					} catch (Exception e) {
						
						JOptionPane.showMessageDialog(null, "[ ��ȣ ]�� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
						
					}

					return dto_pro;

				}
				// ��й�ȣ ã�� -- END

				
				// �л� ��� �κ�
			

				// ���� ��� �κ�
				


	
}
