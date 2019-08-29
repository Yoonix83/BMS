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
	
	 
	 
	// 학생 리스트
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
	 
	 // 전공 과목 리스트
	 public ArrayList<DTO> select_mjr_info() { // 전공 과목 리스트
		 
			ArrayList<DTO> list = new ArrayList<DTO>();
			
			String query = "SELECT * FROM major";

			try {
				pstmt = con_info.con().prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) { // dto 에 담긴 전공 정보를 list 에 담는다
					
					DTO dto_mjr = new DTO(); // 한 싸이클당 하나의 정보를 담기 때문에 while 문에 dto 를 넣어준다
					
					dto_mjr.set_mjrNum(rs.getString("mjrNum"));
					dto_mjr.set_mjrName(rs.getString("mjrName"));
					
					list.add(dto_mjr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	
	// -- 로그인 정보 부분_START
	 // 학생 번호 매징
	public DTO select_std_num(String stdNum) { // 학생 아이디 매칭 문

		String sql = "SELECT * FROM student WHERE stdNum = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, stdNum); // 학생 id 매칭

			rs = pstmt.executeQuery(); // 실행 커리 결과 값 객체 (rs)

			rs.next();
			dto.set_stdNum(rs.getString("stdNum")); // rs.getString("") 의 string 이 DB의 컬럼과 정확히 똑같아야 한다.
			dto.set_stdName(rs.getString("stdName"));
			dto.set_stdGrade(rs.getString("stdGrade"));
			dto.set_stdMajor(rs.getString("stdMajor"));
			dto.set_stdPw(rs.getString("stdPw"));
			dto.set_stdPhone(rs.getString("stdPhone"));

			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "학번을 다시 확인하여 주십시요.^-^");
			 
		}

		return dto;

	}
	
	 // 교수 번호 매징
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

				JOptionPane.showMessageDialog(null, "교수번호를 다시 확인하여 주십시요.^-^");
				
			}

			return dto;

		}
		
		// 직원 번호 매징
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

						JOptionPane.showMessageDialog(null, "직원번호를 다시 확인하여 주십시요.^-^");
						
					}

					return dto;

				}
				// 로그인 -- END
				
				
				// 학생 등록 부분
				public void insert_std_info(String num, String name, String grade, String major, String pw, String phone) {

					String sql = "INSERT INTO student values(?,?,?,?,?,?)";
					
					try {

						pstmt = con_info.con().prepareStatement(sql);

						pstmt.setString(1, num); // 학생 번호
						pstmt.setString(2, name);
						pstmt.setString(3, grade);
						pstmt.setString(4, major);
						pstmt.setString(5, pw);
						pstmt.setString(6, phone);
		
						pstmt.executeUpdate(); // Update() 함수 저장하는 type 은 int형. Result 객체로
												// 담을 수는 없다

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			

				// 교수 등록 부분 // 차후 구현 예정 2019.08.10
				


	
}
