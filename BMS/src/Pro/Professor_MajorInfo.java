package Pro;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_pro;
import DTO.DTO_pro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;


public class Professor_MajorInfo extends JPanel implements ActionListener{
	
	private JTable dtTable;
	JButton search;
	
	DefaultTableModel model; // 이름/직위/전공/전화번호 구분할  테이블
	
	String title[] = {"이 름", "직 위", "전 공", "전화번호"};
	
	String proMajor; // Professor_Page 에서 가져온 proMajor 정보 담기
	
	
	public Professor_MajorInfo(String proMajor_pp) {//professor_Page 에서 받아온 major 정보
			
		this.proMajor = proMajor_pp;
		
		setLayout(null);
		
		JPanel panel = new JPanel(); 
		panel.setBounds(0, 0, 494, 327);
		add(panel);
		panel.setLayout(null);
		
		model = new DefaultTableModel(title, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		dtTable = new JTable(model);
		JScrollPane di_scr = new JScrollPane(dtTable); // 많은 정보 출력시 scroll bar 기능
		
		di_scr.setBounds(12, 26, 444, 239);
		panel.add(di_scr);
		
		search = new JButton("조회"); //search 조회버튼 이름
		search.setBounds(380, 280, 65, 29);
		panel.add(search);
		search.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DAO_pro dao_pro = new DAO_pro();
		
		if(e.getSource()==search){
			// 
			String arr[] = new String[4];

			int cnt = model.getRowCount();
			for (int i = 0; i < cnt; i++) {
				model.removeRow(0);
			}

			ArrayList<DTO_pro> list = dao_pro.selectALL(proMajor);
			for (DTO_pro dto : list) {
				arr[0] = dto.get_proName();
				arr[1] = dto.get_proGrade();
				arr[2] = dto.get_proMajor();
				arr[3] = dto.get_proPhone();
				model = (DefaultTableModel) dtTable.getModel();
				model.addRow(arr);
			}
			
		}
		
	}
}
