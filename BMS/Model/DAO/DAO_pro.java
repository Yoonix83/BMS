package DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.DTO_pro;
import Source_Info.Connection_Info;

public class DAO_pro {
	
	Connection_Info con_info = new Connection_Info();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DTO_pro dto_pro	= new DTO_pro();

	

	public ArrayList<DTO_pro> selectALL(String proMajor) // 학과정보(MajorInfo) 이름, 직위 , 과목 , 전화번호 조회버튼
	{

		ArrayList<DTO_pro> list = new ArrayList<DTO_pro>();
		String sql = "SELECT proName, proGrade, proMajor, proPhone FROM professor WHERE proMajor = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);
			pstmt.setString(1, proMajor);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DTO_pro dto_pro = new DTO_pro();

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
	

	
	public DTO_pro select_pro_num(String pNum) {  // 로그인 교수 정보 출력

		String sql = "SELECT * FROM professor WHERE proNum = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, pNum); 

			rs = pstmt.executeQuery(); 

			rs.next();
			dto_pro.set_proNum(rs.getString("proNum")); 
			dto_pro.set_proName(rs.getString("proName"));
			dto_pro.set_proGrade(rs.getString("proGrade"));
			dto_pro.set_proMajor(rs.getString("proMajor"));
			dto_pro.set_proPw(rs.getString("proPw"));
			dto_pro.set_proPhone(rs.getString("proPhone"));


		} catch (Exception e) {

			e.printStackTrace();
			
		}

		return dto_pro;

	}
	
	public void updateDB(DTO_pro dto, String pNum_lg) // 업데이트(정보수정)
	{
		String sql = "UPDATE prof SET proPw=?, proPhone=? WHERE proNum=?";

		try {
			pstmt = con_info.con().prepareStatement(sql);
			pstmt.setString(1, dto.get_proPw());
			pstmt.setString(2, dto.get_proPhone());
		
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
