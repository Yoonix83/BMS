package Std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_std;
import DTO.DTO;




public class Student_Takecourse_chk extends JPanel implements ActionListener {

	String[] caption = { "과목번호", "과목이름", "시수/학점", "담당교수" };
	DefaultTableModel model;
	JTable table;

	JButton tckCBtn; // 취소버튼
	JButton tckSBtn; // 조회버튼
	
	String stdNum ; //로그인에서 받아오는 학생 번호
	
	Student_Takecourse_chk(String stdNum_lg) {
		this.stdNum = stdNum_lg;
		setLayout(null);
		
		model = new DefaultTableModel(caption, 0) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
			
		};

		JPanel panel = new JPanel(); // 패널 생성
		panel.setBounds(12, 10, 480, 300);
		add(panel);
		panel.setLayout(null);

		table = new JTable(model); // 테이블 생성
		JScrollPane cChkTable = new JScrollPane(table);
		cChkTable.setBounds(12, 10, 381, 217);
		panel.add(cChkTable);

		tckCBtn = new JButton("취소"); // 취소버튼 생성
		tckCBtn.setBounds(332, 237, 63, 23);
		panel.add(tckCBtn);
		tckCBtn.addActionListener(this);

		tckSBtn = new JButton("조회");
		tckSBtn.setBounds(262, 237, 63, 23);
		panel.add(tckSBtn);
		tckSBtn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		DAO_std std = new DAO_std();
		DTO	dto = new DTO();
		model = (DefaultTableModel) table.getModel();
		String arr[] = new String[4];
		
		if (e.getSource() == tckSBtn) // 조회버튼 누르면	
		{
			//String arr[] = new String[4]; // map table 의 각 컬럼에 들어갈 만큼의 배열- 4열이 잇다면 String 배열은 4칸이 필요
			
			int cnt = model.getRowCount();
			for (int i = 0; i < cnt; i++) {
				model.removeRow(0);

			}//--
			ArrayList<DTO> list  = std.select_takecourse_list(stdNum);
			
			
			for (DTO dto_tk : list) { //"과목번호", "과목이름", "시수/학점", "담당교수" 
				
				arr[0] = dto_tk.get_crsNum();
				arr[1] = dto_tk.get_crsName();
				arr[2] = dto_tk.get_tkMark();
				arr[3] = dto_tk.get_proName();
				
						
				//model = (DefaultTableModel) table.getModel();
				model.addRow(arr);
			}
		}
		if (e.getSource() == tckCBtn) // 취소버튼 누르면
		{
			try {
				
				int row = table.getSelectedRow();
				
				if (row == (-1)) {// 데이터 없을 때 취소버튼 누르면 리턴으로 나옴
					JOptionPane.showMessageDialog(null, "취소할 수강 과목을 선택해주세여");
					return;
				}
					
				
				
				std.delete_takecourse(stdNum, model.getValueAt(table.getSelectedRow(), 1).toString());
				
				//dao.deleteDB(Integer.toString(dto1.getSub_num()), s_num, Integer.toString(dto1.getP_num()));

				JOptionPane.showMessageDialog(null, "선택된 수강이 취소 되었습니다!");
				model = (DefaultTableModel) table.getModel();
				model.removeRow(row); // 선택된 행 삭제
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		}

	}
}