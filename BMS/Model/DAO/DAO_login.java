package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.DTO;

import Source_Info.Connection_Info;

public class DAO_login<dto> {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	 Connection_Info con_info = new Connection_Info();
	 
	 DTO dto = new DTO();
	
	 
	 
	// �л� ����Ʈ
	 public ArrayList<DTO> select_std_info() { 
		 
			ArrayList<DTO> list = new ArrayList<DTO>();
			
			String query = "SELECT * FROM student";

			try {
				pstmt = con_info.con().prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					 DTO dto_std = new DTO();
					 
					 dto_std.set_stdNum(rs.getString("stdNum"));
					 dto_std.set_stdName(rs.getString("stdName"));
					 dto_std.set_stdGrade(rs.getString("stdName"));
					 dto_std.set_stdMajor(rs.getString("stdMajor"));
					 dto_std.set_stdPw(rs.getString("stdPw"));
					 dto_std.set_stdPhone(rs.getString("stdPhone"));

					list.add(dto_std);
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			return list;
			
		}
	 
	 // ���� ���� ����Ʈ
	 public ArrayList<DTO> select_mjr_info() { // ���� ���� ����Ʈ
		 
			ArrayList<DTO> list = new ArrayList<DTO>();
			
			String query = "SELECT * FROM major";

			try {
				pstmt = con_info.con().prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) { // dto �� ��� ���� ������ list �� ��´�
					
					DTO dto_mjr = new DTO(); // �� ����Ŭ�� �ϳ��� ������ ��� ������ while ���� dto �� �־��ش�
					
					dto_mjr.set_mjrNum(rs.getString("mjrNum"));
					dto_mjr.set_mjrName(rs.getString("mjrName"));
					
					list.add(dto_mjr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	
	// -- �α��� ���� �κ�_START
	 // �л� ��ȣ ��¡
	public DTO select_std_num(String stdNum) { // �л� ���̵� ��Ī ��

		String sql = "SELECT * FROM student WHERE stdNum = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, stdNum); // �л� id ��Ī

			rs = pstmt.executeQuery(); // ���� Ŀ�� ��� �� ��ü (rs)

			rs.next();
			dto.set_stdNum(rs.getString("stdNum")); // rs.getString("") �� string �� DB�� �÷��� ��Ȯ�� �Ȱ��ƾ� �Ѵ�.
			dto.set_stdName(rs.getString("stdName"));
			dto.set_stdGrade(rs.getString("stdGrade"));
			dto.set_stdMajor(rs.getString("stdMajor"));
			dto.set_stdPw(rs.getString("stdPw"));
			dto.set_stdPhone(rs.getString("stdPhone"));

			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "�й��� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
			 
		}

		return dto;

	}
	
	 // ���� ��ȣ ��¡
		public DTO select_pro_num(String proNum) { 

			String sql = "SELECT * FROM professor WHERE proNum = ?";

			try {

				pstmt = con_info.con().prepareStatement(sql);

				pstmt.setString(1, proNum); 

				rs = pstmt.executeQuery(); 

				rs.next();
				dto.set_proNum(rs.getString("proNum")); 
				dto.set_proName(rs.getString("proName"));
				dto.set_proGrade(rs.getString("proGrade"));
				dto.set_proMajor(rs.getString("proMajor"));
				dto.set_proPw(rs.getString("proPw"));
				dto.set_proPhone(rs.getString("proPhone"));


			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "������ȣ�� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
				
			}

			return dto;

		}
		
		// ���� ��ȣ ��¡
				public DTO select_emp_num(String empNum) { 

					String sql = "SELECT * FROM employee WHERE empNum = ?";

					try {

						pstmt = con_info.con().prepareStatement(sql);

						pstmt.setString(1, empNum); 

						rs = pstmt.executeQuery(); 

						rs.next();
						dto.set_empNum(rs.getString("empNum")); 
						dto.set_empName(rs.getString("empName"));
						dto.set_empDpt(rs.getString("empDpt"));
						dto.set_empMgr(rs.getString("empMgr"));
						dto.set_empPw(rs.getString("empPw"));
						dto.set_empPhone(rs.getString("empPhone"));


					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "������ȣ�� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
						
					}

					return dto;

				}
				// �α��� -- END
				
				
				// �л� ��� �κ�
				public void insert_std_info(String num, String name, String grade, String major, String pw, String phone) {

					String sql = "INSERT INTO student values(?,?,?,?,?,?)";
					
					try {

						pstmt = con_info.con().prepareStatement(sql);

						pstmt.setString(1, num); // �л� ��ȣ
						pstmt.setString(2, name);
						pstmt.setString(3, grade);
						pstmt.setString(4, major);
						pstmt.setString(5, pw);
						pstmt.setString(6, phone);
		
						pstmt.executeUpdate(); // Update() �Լ� �����ϴ� type �� int��. Result ��ü��
												// ���� ���� ����

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			

				// ���� ��� �κ� // ���� ���� ���� 2019.08.10
				


	
}
