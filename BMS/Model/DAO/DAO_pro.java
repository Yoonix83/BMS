package DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.DTO_mark;
import DTO.DTO_pro;

import Source_Info.Connection_Info;

public class DAO_pro {
	
	Connection_Info con_info = new Connection_Info();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DTO_pro dto_pro	= new DTO_pro();

	

	public ArrayList<DTO_pro> selectALL(String proMajor) // 학과정보(MajorInfo) 이름, 직위 , 전공 , 전화번호 조회버튼
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
	

	
	public DTO_pro select_pro_info(String pNum) {  // 로그인 교수 정보 출력

		String sql = "SELECT pro.proNum, pro.proName, pro.proMajor, pro.proGrade, pro.proPw, proPhone, crs.crsName FROM professor pro, course crs WHERE pro.proNum = crs.proNum AND pro.proNum = ?";

		try {

			pstmt = con_info.con().prepareStatement(sql);

			pstmt.setString(1, pNum); 

			rs = pstmt.executeQuery(); 

			rs.next();
			dto_pro.set_proNum(rs.getString("proNum")); 
			dto_pro.set_proName(rs.getString("proName"));
			dto_pro.set_proMajor(rs.getString("proMajor"));
			dto_pro.set_proGrade(rs.getString("proGrade"));
			dto_pro.set_proPw(rs.getString("proPw"));
			dto_pro.set_proPhone(rs.getString("proPhone"));
			dto_pro.set_crsName(rs.getString("crsName"));


		} catch (Exception e) {

			e.printStackTrace();
			
		}

		return dto_pro;

	}
	
	public void update_pro_info(DTO_pro dto, String pNum_lg) // 업데이트(정보수정)
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
	
	public ArrayList<DTO_mark> select_mark(String proNum)// 성적관리(Assign_grades) 학번, 이름, 학년, 학과, 과목, 성적 조회

	{
		ArrayList<DTO_mark> list = new ArrayList<DTO_mark>();

		String sql = "SELECT std.stdNum, std.stdName, std.stdGrade, std.stdMajor, crs.crsName, tk.tkMark FROM student std, course crs, professor pro, takecourse tk "
				+ "WHERE std.stdNum = tk.tk_stdNum  AND crs.proNum = pro.proNum AND tk.tk_crsNum = crs.crsNum AND pro.proNum = ?";
				
		try {
			pstmt = con_info.con().prepareStatement(sql);
			pstmt.setString(1, proNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DTO_mark dto = new DTO_mark();

				dto.set_stdNum(rs.getString("stdNum"));
				dto.set_stdName(rs.getString("stdName"));
				dto.set_stdGrade(rs.getString("stdGrade"));
				dto.set_stdMajor(rs.getString("stdMajor"));
				dto.set_crsName(rs.getString("crsName"));
				dto.set_tkMark(rs.getString("tkMark"));
						
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
