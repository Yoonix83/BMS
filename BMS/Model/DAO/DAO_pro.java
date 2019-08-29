package DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.DTO;
import Source_Info.Connection_Info;

public class DAO_pro {
	
	Connection_Info con_info = new Connection_Info();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DTO dto	= new DTO();

	public ArrayList<DTO> selectALL(String proMajor) // �а�����(MajorInfo) �̸�, ���� , ���� , ��ȭ��ȣ ��ȸ��ư
	{

		ArrayList<DTO> list = new ArrayList<DTO>();
		String sql = "SELECT proName, proGrade, proMajor, proPhone FROM professor WHERE proMajor = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);
			pstmt.setString(1, proMajor);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DTO dto_pro = new DTO();

				dto_pro.set_proName(rs.getString("proName"));
				dto_pro.set_proGrade(rs.getString("proGrade"));
				dto_pro.set_proMajor(rs.getString("proMajor"));
				dto_pro.set_proPhone(rs.getString("proPhone"));
				list.add(dto_pro);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	
	public DTO select_pro_info(String pNum) {  // �α��� ���� ���� ���

		String sql = "SELECT pro.proNum, pro.proName, pro.proMajor, pro.proGrade, pro.proPw, proPhone, crs.crsName FROM professor pro, course crs "
				+ "WHERE pro.proNum = crs.proNum AND pro.proNum = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, pNum); 

			rs = pstmt.executeQuery(); 

			rs.next();
			dto.set_proNum(rs.getString("proNum")); 
			dto.set_proName(rs.getString("proName"));
			dto.set_proMajor(rs.getString("proMajor"));
			dto.set_proGrade(rs.getString("proGrade"));
			dto.set_proPw(rs.getString("proPw"));
			dto.set_proPhone(rs.getString("proPhone"));
			dto.set_crsName(rs.getString("crsName"));


		} catch (Exception e) {

			e.printStackTrace();
			
		}

		return dto;

	}
	
//	// ������Ʈ(��������)- �̿ϼ� - ���� ������ ����
//	public void update_pro_info(DTO dto_pro, String pNum_lg) 
//	{
//		String sql = "UPDATE prof SET proPw=?, proPhone=? WHERE proNum=?";
//
//		try {
//			pstmt = con_info.con().prepareStatement(sql);
//			pstmt.setString(1, dto_pro.get_proPw());
//			pstmt.setString(2, dto_pro.get_proPhone());
//		
//			pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	// ��������(Professor_About_Grade) �й�, �̸�, �г�, �а�, ����, ���� ��ȸ
	public ArrayList<DTO> select_mark(String proNum)

	{
		ArrayList<DTO> list = new ArrayList<DTO>();

		String sql = "SELECT std.stdNum, std.stdName, std.stdGrade, std.stdMajor, crs.crsName, tk.tkMark FROM student std, course crs, professor pro, takecourse tk "
				+ "WHERE std.stdNum = tk.tk_stdNum  AND crs.proNum = pro.proNum AND tk.tk_crsNum = crs.crsNum AND pro.proNum = ?";
				
		try {
			pstmt = con_info.con().prepareStatement(sql);
			pstmt.setString(1, proNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DTO dto_mark = new DTO();

				dto_mark.set_stdNum(rs.getString("stdNum"));
				dto_mark.set_stdName(rs.getString("stdName"));
				dto_mark.set_stdGrade(rs.getString("stdGrade"));
				dto_mark.set_stdMajor(rs.getString("stdMajor"));
				dto_mark.set_crsName(rs.getString("crsName"));
				dto_mark.set_tkMark(rs.getString("tkMark"));
						
				list.add(dto_mark);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// �������(Professor_About_Grade) �л� ���� UPDATE
	public DTO update_grade(DTO dto_mark) 
	{

		String sql = "UPDATE takecourse SET tkMark = ? WHERE tk_crsName = ? AND tk_stdNum = ?";
		try {
			
			pstmt = con_info.con().prepareStatement(sql);
			
			pstmt.setString(1, dto_mark.get_tkMark());
			pstmt.setString(2, dto_mark.get_crsName());
			pstmt.setString(3, dto_mark.get_stdNum());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
}
