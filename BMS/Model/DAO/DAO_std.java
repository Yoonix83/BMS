package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.DTO;

import Source_Info.Connection_Info;

public class DAO_std {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	 Connection_Info con_info = new Connection_Info();

	 
	 // ����, �ü�/���� , ���� , ����
		public ArrayList<DTO> select_mark(String stdNum)

		{
			ArrayList<DTO> list = new ArrayList<DTO>();

			String sql = "SELECT tk.tk_crsName, crs.crsCredit, tk.tkMark, crs.proName FROM course crs, professor pro, takecourse tk "
					+ "WHERE tk.tk_crsNum = crs.crsNum AND crs.proName = pro.proName AND tk_stdNum = ?";

					
			try {
				pstmt = con_info.con().prepareStatement(sql);
				pstmt.setString(1, stdNum);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					DTO dto_mark = new DTO();

					dto_mark.set_crsName(rs.getString("tk_crsName"));
					dto_mark.set_crsCredit(rs.getString("crsCredit"));
					dto_mark.set_tkMark(rs.getString("tkMark"));
					dto_mark.set_proName(rs.getString("proName"));
										
					list.add(dto_mark);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		
	// ���� 
		public ArrayList<DTO> select_course_info() { // ���� ����Ʈ
			
			ArrayList<DTO> list = new ArrayList<DTO>();
			
			
			String sql = "SELECT * FROM course";

			try {
				pstmt = con_info.con().prepareStatement(sql);
				rs = pstmt.executeQuery();

				rs.next();
				
				while (rs.next()) {
					
					DTO dto_course = new DTO();
	
					dto_course.set_crsNum(rs.getString("crsNum")); 
					dto_course.set_crsName(rs.getString("crsName"));
					dto_course.set_crsCredit(rs.getString("crsCredit"));
					dto_course.set_crsMajor(rs.getString("crsMajor"));
					dto_course.set_proNum(rs.getString("proNum"));
					dto_course.set_proName(rs.getString("proName"));
					
					list.add(dto_course);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
			
		}
		
		// Ư�� ������ ����
		public DTO select_course_list(String crsName) { 
			
			DTO dto_course = new DTO();
				
			String sql = "SELECT * FROM course WHERE crsName = ?";

			try {
				pstmt = con_info.con().prepareStatement(sql);
				pstmt.setString(1, crsName);
				rs = pstmt.executeQuery();

				rs.next();
		
				dto_course.set_crsNum(rs.getString("crsNum")); 
				dto_course.set_crsName(rs.getString("crsName"));
				dto_course.set_crsCredit(rs.getString("crsCredit"));
				dto_course.set_crsMajor(rs.getString("crsMajor"));
				dto_course.set_proNum(rs.getString("proNum"));
				dto_course.set_proName(rs.getString("proName"));
				
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dto_course;
			
		}
		
		// Ư�� �й��� ������û ����
		public ArrayList<DTO> select_takecourse_list(String stdNum) { // "�����ȣ", "�����̸�", "�ü�/����", "��米��" 
			
			ArrayList<DTO> list = new ArrayList<DTO>();
			
			
			String sql = "SELECT tk.tk_crsName, tk.tk_crsNum, tk.tkMark, crs.proName " + 
					"FROM takecourse tk, course crs WHERE tk.tk_crsNum = crs.crsNum AND tk.tk_stdNum = ? ORDER BY tk.tk_crsNum ASC";

			try {
				pstmt = con_info.con().prepareStatement(sql);
				pstmt.setString(1, stdNum);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					DTO dto_takecourse = new DTO();
	
					dto_takecourse.set_crsNum(rs.getString("tk_crsNum")); 
					dto_takecourse.set_crsName(rs.getString("tk_crsName"));
					dto_takecourse.set_tkMark(rs.getString("tkMark"));
					dto_takecourse.set_proName(rs.getString("proName"));				
					
					list.add(dto_takecourse);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
			
		}
		
	//  ���� ��û Data ���� 
		public void insert_course_add(String stdNum, String crsNum, String crsName){
			String sql = "INSERT INTO `takecourse`(`tk_stdNum`,`tk_crsNum`,`tk_crsName`) VALUES(?,?,?)";
			
			try{
				pstmt = con_info.con().prepareStatement(sql);
			
				pstmt.setString(1, stdNum);
				pstmt.setString(2, crsNum);
				pstmt.setString(3, crsName);
				
				pstmt.executeUpdate();
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
	}
	//  ���� ��û Data ���� --
		
		// ���� ���
		public void delete_takecourse(String stdNum, String crsName) { // ������Ʈ(��������)

			String query = "DELETE FROM takecourse WHERE tk_stdNum = ? AND tk_crsName = ?";

			try {
				pstmt = con_info.con().prepareStatement(query); // query�� ����
				pstmt.setString(1, stdNum);
				pstmt.setString(2, crsName);
			
				pstmt.executeUpdate();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}
}
