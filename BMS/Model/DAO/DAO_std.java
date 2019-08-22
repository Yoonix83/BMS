package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.DTO_mark;
import Source_Info.Connection_Info;

public class DAO_std {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	 Connection_Info con_info = new Connection_Info();

	 
	 // 과목, 시수/학점 , 성적 , 교수
		public ArrayList<DTO_mark> select_mark(String stdNum)

		{
			ArrayList<DTO_mark> list = new ArrayList<DTO_mark>();

			String sql = "SELECT tk.tk_crsName, crs.crsCredit, tk.tkMark, crs.proName FROM course crs, professor pro, takecourse tk "
					+ "WHERE tk.tk_crsNum = crs.crsNum AND crs.proName = pro.proName AND tk_stdNum = ?";

					
			try {
				pstmt = con_info.con().prepareStatement(sql);
				pstmt.setString(1, stdNum);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					DTO_mark dto = new DTO_mark();

					dto.set_crsName(rs.getString("tk_crsName"));
					dto.set_crsCredit(rs.getString("crsCredit"));
					dto.set_tkMark(rs.getString("tkMark"));
					dto.set_proName(rs.getString("proName"));
										
					list.add(dto);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
}
