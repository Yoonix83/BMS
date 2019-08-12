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
	
	private JTable diTable;
	JButton search0;
	
	DefaultTableModel model;
	
	String title[] = {"이 름", "직 위", "전 공", "전화번호"};
	
	String proMajor_pp;//professor_Page 에서 받아온 major 정보
	
	private JTable table;
	public Professor_MajorInfo(String proMajor) {
			
		this.proMajor_pp = proMajor;
		
		setLayout(null);
		
		JPanel chang = new JPanel(); //chang deptinfo 창
		chang.setBounds(0, 0, 494, 327);
		add(chang);
		chang.setLayout(null);
		
		model = new DefaultTableModel(title, 0);
		diTable = new JTable(model);
		JScrollPane di_scr = new JScrollPane(diTable);
		
		di_scr.setBounds(12, 26, 444, 239);
		chang.add(di_scr);
		
		search0 = new JButton("조회"); //search 조회버튼 이름
		search0.setBounds(380, 280, 65, 29);
		chang.add(search0);
		search0.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DAO_pro dao_pro = new DAO_pro();
		DTO_pro dto_pro = new DTO_pro();
		if(e.getSource()==search0){
			// deptinfo 이름 직위 과목 나오는 버튼 설정 search
			String arr[] = new String[4];

			int cnt = model.getRowCount();
			for (int i = 0; i < cnt; i++) {
				model.removeRow(0);
			}

			ArrayList<DTO_pro> list = dao_pro.selectALL(proMajor_pp);
			for (DTO_pro dto : list) {
				arr[0] = dto.get_proName();
				arr[1] = dto.get_proGrade();
				arr[2] = dto.get_proMajor();
				arr[3] = dto.get_proPhone();
				model = (DefaultTableModel) diTable.getModel();
				model.addRow(arr);
			}
			
		}
	}
}
