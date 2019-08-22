package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.DTO_emp;
import DTO.DTO_mjr;
import DTO.DTO_pro;
import DTO.DTO_std;
import Source_Info.Connection_Info;

public class DAO_login<dto> {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	 Connection_Info con_info = new Connection_Info();
	 
	 DTO_std dto_std = new DTO_std();
	 DTO_pro dto_pro = new DTO_pro();
	 DTO_emp dto_emp = new DTO_emp();
	 
	 
	// 학생 리스트
	 public ArrayList<DTO_std> select_std_info() { 
		 
			ArrayList<DTO_std> list = new ArrayList<DTO_std>();
			
			String query = "SELECT * FROM student";

			try {
				pstmt = con_info.con().prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					 DTO_std dto_std = new DTO_std();
					 
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
	 public ArrayList<DTO_mjr> select_mjr_info() { // 전공 과목 리스트
		 
			ArrayList<DTO_mjr> list = new ArrayList<DTO_mjr>();
			
			String query = "SELECT * FROM major";

			try {
				pstmt = con_info.con().prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) { // dto 에 담긴 전공 정보를 list 에 담는다
					
					DTO_mjr dto_mjr = new DTO_mjr(); // 한 싸이클당 하나의 정보를 담기 때문에 while 문에 dto 를 넣어준다
					
					dto_mjr.set_mjNum(rs.getString("mjrNum"));
					dto_mjr.set_mjName(rs.getString("mjrName"));
					
					list.add(dto_mjr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	
	// -- 로그인 정보 부분_START
	 // 학생 번호 매징
	public DTO_std select_std_num(String stdNum) { // 학생 아이디 매칭 문

		String sql = "SELECT * FROM student WHERE stdNum = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, stdNum); // 학생 id 매칭

			rs = pstmt.executeQuery(); // 실행 커리 결과 값 객체 (rs)

			rs.next();
			dto_std.set_stdNum(rs.getString("stdNum")); // rs.getString("") 의 string 이 DB의 컬럼과 정확히 똑같아야 한다.
			dto_std.set_stdName(rs.getString("stdName"));
			dto_std.set_stdGrade(rs.getString("stdGrade"));
			dto_std.set_stdMajor(rs.getString("stdMajor"));
			dto_std.set_stdPw(rs.getString("stdPw"));
			dto_std.set_stdPhone(rs.getString("stdPhone"));

			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "학번을 다시 확인하여 주십시요.^-^");
			
		}

		return dto_std;

	}
	
	 // 교수 번호 매징
		public DTO_pro select_pro_num(String proNum) { 

			String sql = "SELECT * FROM professor WHERE proNum = ?";

			try {

				pstmt = con_info.con().prepareStatement(sql);

				pstmt.setString(1, proNum); 

				rs = pstmt.executeQuery(); 

				rs.next();
				dto_pro.set_proNum(rs.getString("proNum")); 
				dto_pro.set_proName(rs.getString("proName"));
				dto_pro.set_proGrade(rs.getString("proGrade"));
				dto_pro.set_proMajor(rs.getString("proMajor"));
				dto_pro.set_proPw(rs.getString("proPw"));
				dto_pro.set_proPhone(rs.getString("proPhone"));


			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "교수번호를 다시 확인하여 주십시요.^-^");
				
			}

			return dto_pro;

		}
		
		// 직원 번호 매징
				public DTO_emp select_emp_num(String empNum) { 

					String sql = "SELECT * FROM employee WHERE empNum = ?";

					try {

						pstmt = con_info.con().prepareStatement(sql);

						pstmt.setString(1, empNum); 

						rs = pstmt.executeQuery(); 

						rs.next();
						dto_emp.set_empNum(rs.getString("empNum")); 
						dto_emp.set_empName(rs.getString("empName"));
						dto_emp.set_empDpt(rs.getString("empDpt"));
						dto_emp.set_empMgr(rs.getString("empMgr"));
						dto_emp.set_empPw(rs.getString("empPw"));
						dto_emp.set_empPhone(rs.getString("empPhone"));


					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "직원번호를 다시 확인하여 주십시요.^-^");
						
					}

					return dto_emp;

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
